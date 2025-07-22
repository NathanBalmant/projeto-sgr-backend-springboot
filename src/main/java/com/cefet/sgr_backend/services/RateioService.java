package com.cefet.sgr_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.sgr_backend.dto.RateioDTO;
import com.cefet.sgr_backend.entities.Conta;
import com.cefet.sgr_backend.entities.Morador;
import com.cefet.sgr_backend.entities.Rateio;
import com.cefet.sgr_backend.enums.SituacaoConta;
import com.cefet.sgr_backend.enums.SituacaoRateio;
import com.cefet.sgr_backend.repositories.ContaRepository;
import com.cefet.sgr_backend.repositories.MoradorRepository;
import com.cefet.sgr_backend.repositories.RateioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RateioService {

    @Autowired
    private RateioRepository rateioRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<RateioDTO> findAll() {
        return rateioRepository.findAll().stream().map(RateioDTO::new).toList();
    }

    public RateioDTO findById(Long id) {
        Rateio rateio = rateioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rateio não encontrado com ID: " + id));
        return new RateioDTO(rateio);
    }

    public RateioDTO insert(RateioDTO dto) {
    Conta conta = contaRepository.findById(dto.getIdConta())
        .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

    //aqui é uma regra de negocio para a soma do rateio não exceder o valor da conta
    double somaRateios = rateioRepository.findByContaId(dto.getIdConta())
        .stream()
        .mapToDouble(Rateio::getValor)
        .sum();

    double novaSoma = somaRateios + dto.getValor();
    if (novaSoma > conta.getValor()) {
        throw new IllegalArgumentException("A soma dos rateios excede o valor da conta.");
    }

    Rateio rateio = new Rateio();
    rateio.setValor(dto.getValor());
    rateio.setSituacao(dto.getSituacao());

    Morador morador = moradorRepository.findById(dto.getIdMorador())
        .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado"));

    rateio.setMorador(morador);
    rateio.setConta(conta);

    Rateio salvo = rateioRepository.save(rateio);
    return new RateioDTO(salvo);
}


    public RateioDTO update(Long id, RateioDTO dto) {
    Rateio rateio = rateioRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Rateio não encontrado"));

        //aqui é uma regra de negocio da rf-004, para não atualizar um rateio onde a conta dela está quitada/cancelada
    Conta conta = rateio.getConta();
    if (conta.getSituacao() == SituacaoConta.QUITADA || conta.getSituacao() == SituacaoConta.CANCELADA) {
        throw new IllegalStateException("Rateios de contas finalizadas não podem ser alterados.");
    }


    double somaRateios = rateioRepository.findByContaId(conta.getId())
        .stream()
        .filter(r -> !r.getId().equals(id))  
        .mapToDouble(Rateio::getValor)
        .sum();

    double novaSoma = somaRateios + dto.getValor();
    if (novaSoma > conta.getValor()) {
        throw new IllegalArgumentException("A soma dos rateios excede o valor da conta.");
    }

    rateio.setValor(dto.getValor());
    rateio.setSituacao(dto.getSituacao());

    return new RateioDTO(rateioRepository.save(rateio));
}


    public void delete(Long id) {
        if (!rateioRepository.existsById(id)) {
            throw new EntityNotFoundException("Rateio não encontrado com ID: " + id);
        }
        rateioRepository.deleteById(id);
    }

    public List<RateioDTO> findByContaId(Long idConta) {
    List<Rateio> lista = rateioRepository.findByContaId(idConta);
    return lista.stream().map(RateioDTO::new).toList();
    }

    public List<RateioDTO> findByMoradorId(Long idMorador) {
    List<Rateio> lista = rateioRepository.findByMoradorId(idMorador);
    return lista.stream().map(RateioDTO::new).toList();
    }

    public Double calcularSaldoMorador(Long moradorId) {
    Double saldo = rateioRepository.calcularSaldoMorador(moradorId);
    return saldo != null ? saldo : 0.0;
    }

    public RateioDTO quitarRateio(Long idRateio, Long idMoradorPagador) {
    Rateio rateio = rateioRepository.findById(idRateio)
            .orElseThrow(() -> new EntityNotFoundException("Rateio não encontrado com ID: " + idRateio));
        if (rateio.getSituacao() == SituacaoRateio.PAGO) {
            throw new IllegalStateException("Rateio já está pago.");
    }
    Conta contaAssociada = rateio.getConta();
         if (contaAssociada.getSituacao() == SituacaoConta.QUITADA || contaAssociada.getSituacao() == SituacaoConta.CANCELADA) {
         throw new IllegalStateException("Não é possível quitar rateio de uma conta que está finalizada (quitada ou cancelada).");
    }

    rateio.setSituacao(SituacaoRateio.PAGO);
    Rateio salvo = rateioRepository.save(rateio);
    return new RateioDTO(salvo);
    }
}
