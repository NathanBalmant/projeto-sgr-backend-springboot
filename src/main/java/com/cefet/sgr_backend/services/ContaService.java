package com.cefet.sgr_backend.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.sgr_backend.dto.ContaDTO;
import com.cefet.sgr_backend.entities.Conta;
import com.cefet.sgr_backend.entities.Morador;
import com.cefet.sgr_backend.entities.TipoConta;
import com.cefet.sgr_backend.enums.SituacaoConta;
import com.cefet.sgr_backend.repositories.ContaRepository;
import com.cefet.sgr_backend.repositories.MoradorRepository;
import com.cefet.sgr_backend.repositories.TipoContaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private TipoContaRepository tipoContaRepository;

    @Autowired
    private HistoricoService historicoService;

    public List<ContaDTO> findAll() {
        List<Conta> lista = contaRepository.findAll();
        return lista.stream().map(ContaDTO::new).toList();
    }

    public ContaDTO findById(Long id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada com ID: " + id));
        return new ContaDTO(conta);
    }

    public ContaDTO insert(ContaDTO dto) {
        Conta conta = new Conta();
        conta.setValor(dto.getValor());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setSituacao(SituacaoConta.PENDENTE); // aqui começa com pendente pq toda conta cadastrada é uma nova
                                                   // conta. Na teoria, não está paga
        conta.setObservacao(dto.getObservacao());

        Morador morador = moradorRepository.findById(dto.getIdMorador())
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + dto.getIdMorador()));
        conta.setMorador(morador);

        TipoConta tipoConta = tipoContaRepository.findById(dto.getIdTipoConta())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de conta não encontrado com ID: " + dto.getIdTipoConta()));
        conta.setTipoConta(tipoConta);

        Conta salva = contaRepository.save(conta);
        return new ContaDTO(salva);
    }

    public ContaDTO update(Long id, ContaDTO dto) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada com ID: " + id));
        // aqui é uma condicional onde nega atualizar dados de uma conta que foi
        // quitada/cancelada(rf-004)
        if (conta.getSituacao() == SituacaoConta.QUITADA || conta.getSituacao() == SituacaoConta.CANCELADA) {
            System.out.println("Tentativa de editar conta " + id + " com situação finalizada: " + conta.getSituacao());
            throw new IllegalStateException("Contas quitadas ou canceladas não podem ser alteradas.");
        }
        conta.setValor(dto.getValor());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setSituacao(dto.getSituacao());
        conta.setObservacao(dto.getObservacao());

        Morador morador = moradorRepository.findById(dto.getIdMorador())
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + dto.getIdMorador()));
        conta.setMorador(morador);

        TipoConta tipoConta = tipoContaRepository.findById(dto.getIdTipoConta())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Tipo de conta não encontrado com ID: " + dto.getIdTipoConta()));
        conta.setTipoConta(tipoConta);

        Conta atualizada = contaRepository.save(conta);
        return new ContaDTO(atualizada);
    }

    public void delete(Long id) {
        if (!contaRepository.existsById(id)) {
            throw new EntityNotFoundException("Conta não encontrada com ID: " + id);
        }
        contaRepository.deleteById(id);
    }

    public List<ContaDTO> findByMorador(Long moradorId) {
        List<Conta> contas = contaRepository.findByMoradorId(moradorId);
        return contas.stream().map(ContaDTO::new).toList();
    }

    public ContaDTO quitarConta(Long idConta, Long idMorador) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        if (conta.getSituacao() == SituacaoConta.QUITADA) {
            throw new IllegalStateException("Conta já está quitada.");
        }

        conta.setSituacao(SituacaoConta.QUITADA);
        contaRepository.save(conta);

        Morador morador = moradorRepository.findById(idMorador)
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado"));

        historicoService.registrarAlteracaoSituacao(conta, morador, SituacaoConta.QUITADA);
        return new ContaDTO(conta);
    }

    public ContaDTO cancelarConta(Long idConta, Long idMorador) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        if (conta.getSituacao() == SituacaoConta.CANCELADA) {
            throw new IllegalStateException("Conta já está cancelada.");
        }

        conta.setSituacao(SituacaoConta.CANCELADA);
        contaRepository.save(conta);

        Morador morador = moradorRepository.findById(idMorador)
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado"));

        historicoService.registrarAlteracaoSituacao(conta, morador, SituacaoConta.CANCELADA);
        return new ContaDTO(conta);
    }

    public ContaDTO reabrirConta(Long idConta, Long idMorador) {
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        if (conta.getSituacao() == SituacaoConta.PENDENTE) {
            throw new IllegalStateException("Conta já está pendente.");
        }

        conta.setSituacao(SituacaoConta.PENDENTE);
        contaRepository.save(conta);

        Morador morador = moradorRepository.findById(idMorador)
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado"));

        historicoService.registrarAlteracaoSituacao(conta, morador, SituacaoConta.PENDENTE);
        return new ContaDTO(conta);
    }

    public ContaDTO replicar(Long idConta) {
        Conta contaOriginal = contaRepository.findById(idConta)
                .orElseThrow(() -> new EntityNotFoundException("Conta original não encontrada"));

        Conta novaConta = new Conta();
        novaConta.setValor(contaOriginal.getValor());
        novaConta.setDataVencimento(contaOriginal.getDataVencimento());
        novaConta.setSituacao(SituacaoConta.PENDENTE); 
        novaConta.setObservacao(contaOriginal.getObservacao() + " (Cópia)");
        novaConta.setMorador(contaOriginal.getMorador());
        novaConta.setTipoConta(contaOriginal.getTipoConta());

        Conta salva = contaRepository.save(novaConta);
        return new ContaDTO(salva);
    }

    public List<ContaDTO> buscarExtratoPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
    List<Conta> contas = contaRepository.findByDataVencimentoBetween(dataInicial, dataFinal);
    return contas.stream().map(ContaDTO::new).toList();
    }

}
