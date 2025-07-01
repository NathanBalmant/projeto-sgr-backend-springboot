package com.cefet.sgr_backend.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.sgr_backend.dto.HistoricoDTO;
import com.cefet.sgr_backend.entities.Conta;
import com.cefet.sgr_backend.entities.Historico;
import com.cefet.sgr_backend.entities.Morador;
import com.cefet.sgr_backend.enums.SituacaoConta;
import com.cefet.sgr_backend.repositories.ContaRepository;
import com.cefet.sgr_backend.repositories.HistoricoRepository;
import com.cefet.sgr_backend.repositories.MoradorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private MoradorRepository moradorRepository;

    public List<HistoricoDTO> findAll() {
        List<Historico> lista = historicoRepository.findAll();
        return lista.stream().map(HistoricoDTO::new).toList();
    }

    public HistoricoDTO findById(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Histórico não encontrado com ID: " + id));
        return new HistoricoDTO(historico);
    }

    public HistoricoDTO insert(HistoricoDTO dto) {
        Conta conta = contaRepository.findById(dto.getIdConta())
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        Morador morador = moradorRepository.findById(dto.getIdMorador())
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado"));

        Historico historico = new Historico();
        historico.setConta(conta);
        historico.setMorador(morador);
        historico.setSituacao(dto.getSituacao());
        historico.setData(LocalDateTime.now()); 

        Historico salvo = historicoRepository.save(historico);
        return new HistoricoDTO(salvo);
    }

    public void registrarAlteracaoSituacao(Conta conta, Morador morador, SituacaoConta novaSituacao) {
        Historico historico = new Historico();
        historico.setConta(conta);
        historico.setMorador(morador);
        historico.setSituacao(novaSituacao);
        historico.setData(LocalDateTime.now());
        historicoRepository.save(historico);
    }
}