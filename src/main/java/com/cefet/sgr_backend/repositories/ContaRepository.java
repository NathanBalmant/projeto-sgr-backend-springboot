package com.cefet.sgr_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sgr_backend.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

}
