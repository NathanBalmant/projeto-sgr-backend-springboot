package com.cefet.sgr_backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cefet.sgr_backend.dto.MoradorDTO;
import com.cefet.sgr_backend.entities.Morador;
import com.cefet.sgr_backend.repositories.MoradorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MoradorService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MoradorRepository moradorRepository;

    public List<MoradorDTO> findAll() {
        List<Morador> lista = moradorRepository.findAll();
        return lista.stream().map(MoradorDTO::new).toList();
    }

    public MoradorDTO findById(Long id) {
        Morador morador = moradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + id));
        return new MoradorDTO(morador);
    }

    public MoradorDTO insert(MoradorDTO moradorDTO) {
        Morador morador = new Morador();
        morador.setNome(moradorDTO.getNome());
        morador.setCpf(moradorDTO.getCpf());
        morador.setDataNascimento(LocalDate.parse(moradorDTO.getDataNascimento())); // cuidado aqui!
        morador.setCelular(moradorDTO.getCelular());
        morador.setEmail(moradorDTO.getEmail());
        morador.setContatoFamilia(moradorDTO.getContatoFamilia());
        morador.setLogin(moradorDTO.getLogin());
        morador.setFoto(moradorDTO.getFoto());

        if (moradorRepository.existsByCpf(morador.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        if (moradorRepository.existsByLogin(morador.getLogin())) {
            throw new IllegalArgumentException("Login já cadastrado.");
        }

        morador.setSenha(passwordEncoder.encode(moradorDTO.getSenha()));

        Morador salvo = moradorRepository.save(morador);
        return new MoradorDTO(salvo);
    }

    public MoradorDTO update(Long id, MoradorDTO novoMoradorDTO) {
        Morador morador = moradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Morador não encontrado com ID: " + id));

        morador.setNome(novoMoradorDTO.getNome());
        morador.setCpf(novoMoradorDTO.getCpf());
        morador.setDataNascimento(LocalDate.parse(novoMoradorDTO.getDataNascimento())); // cuidado aqui!
        morador.setCelular(novoMoradorDTO.getCelular());
        morador.setEmail(novoMoradorDTO.getEmail());
        morador.setContatoFamilia(novoMoradorDTO.getContatoFamilia());
        morador.setLogin(novoMoradorDTO.getLogin());
        morador.setFoto(novoMoradorDTO.getFoto());

        morador.setSenha(passwordEncoder.encode(novoMoradorDTO.getSenha()));
    
        Morador atualizado = moradorRepository.save(morador);
        return new MoradorDTO(atualizado);
    }

    public void delete(Long id) {
        if (!moradorRepository.existsById(id)) {
            throw new EntityNotFoundException("Morador não encontrado com ID: " + id);
        }
        moradorRepository.deleteById(id);
    }

    public Optional<Morador> findByLogin(String login) {
        return moradorRepository.findByLogin(login);
    }
}