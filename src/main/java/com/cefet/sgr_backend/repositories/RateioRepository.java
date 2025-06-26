package com.cefet.sgr_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sgr_backend.entities.Rateio;

public interface RateioRepository extends JpaRepository<Rateio, Long> {
    List<Rateio> findByContaId(Long idConta);
    List<Rateio> findByMoradorId(Long idMorador);


}
