package com.cefet.sgr_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sgr_backend.entities.Historico;

public interface HistoricoRepository extends JpaRepository <Historico,Long> {

    List<Historico> findByContaId(Long idConta);
    List<Historico> findByMoradorId(Long idMorador);
}
