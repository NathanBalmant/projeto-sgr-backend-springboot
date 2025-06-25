package com.cefet.sgr_backend.services;

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
        conta.setSituacao(SituacaoConta.PENDENTE); // aqui começa com pendente pq toda conta cadastrada é uma nova conta. Na teoria, não está paga
        conta.setObservacao(dto.getObservacao());

        Morador morador = moradorRepository.findById(dto.getIdMorador())
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + dto.getIdMorador()));
        conta.setMorador(morador);

        TipoConta tipoConta = tipoContaRepository.findById(dto.getIdTipoConta())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de conta não encontrado com ID: " + dto.getIdTipoConta()));
        conta.setTipoConta(tipoConta);

        Conta salva = contaRepository.save(conta);
        return new ContaDTO(salva);
    }

    public ContaDTO update(Long id, ContaDTO dto) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada com ID: " + id));

        conta.setValor(dto.getValor());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setSituacao(dto.getSituacao());
        conta.setObservacao(dto.getObservacao());

        Morador morador = moradorRepository.findById(dto.getIdMorador())
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + dto.getIdMorador()));
        conta.setMorador(morador);

        TipoConta tipoConta = tipoContaRepository.findById(dto.getIdTipoConta())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de conta não encontrado com ID: " + dto.getIdTipoConta()));
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
    
}

