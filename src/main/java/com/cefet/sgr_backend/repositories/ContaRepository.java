package com.cefet.sgr_backend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sgr_backend.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

    List<Conta> findByMoradorId(Long moradorId);
    List<Conta> findByDataVencimentoBetween(LocalDate dataInicial, LocalDate dataFinal);

}
