package com.cefet.sgr_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.sgr_backend.entities.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Long> {
    Optional<Morador> findByLogin(String login);

    Optional<Morador> findByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByLogin(String login);
}
