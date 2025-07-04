package com.cefet.sgr_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cefet.sgr_backend.entities.Rateio;

public interface RateioRepository extends JpaRepository<Rateio, Long> {
    List<Rateio> findByContaId(Long idConta);
    List<Rateio> findByMoradorId(Long idMorador);
    @Query("SELECT SUM(r.valor) FROM Rateio r WHERE r.morador.id = :moradorId AND r.situacao = 'EM_ABERTO'")
    Double calcularSaldoMorador(Long moradorId);
}
