package com.cefet.sgr_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cefet.sgr_backend.entities.TipoConta;
import com.cefet.sgr_backend.repositories.TipoContaRepository;

@RestController
@RequestMapping("/tipos")
public class TipoContaController {

    @Autowired
    private TipoContaRepository tipoContaRepository;

    @GetMapping
    public ResponseEntity<List<TipoConta>> findAll() {
        List<TipoConta> lista = tipoContaRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoConta> findById(@PathVariable Long id) {
        return tipoContaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoConta> insert(@RequestBody TipoConta tipoConta) {
        TipoConta novo = tipoContaRepository.save(tipoConta);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoConta> update(@PathVariable Long id, @RequestBody TipoConta dto) {
        return tipoContaRepository.findById(id)
            .map(tipo -> {
                tipo.setNome(dto.getNome());
                tipoContaRepository.save(tipo);
                return ResponseEntity.ok(tipo);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!tipoContaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tipoContaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}