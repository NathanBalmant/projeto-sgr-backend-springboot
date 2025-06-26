package com.cefet.sgr_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.sgr_backend.dto.RateioDto;
import com.cefet.sgr_backend.services.RateioService;

@RestController
@RequestMapping("/rateios")
public class RateioController {

    @Autowired
    private RateioService rateioService;

    @GetMapping
    public ResponseEntity<List<RateioDto>> findAll() {
        List<RateioDto> lista = rateioService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateioDto> findById(@PathVariable Long id) {
        RateioDto dto = rateioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RateioDto> insert(@RequestBody RateioDto dto) {
        RateioDto novo = rateioService.insert(dto);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RateioDto> update(@PathVariable Long id, @RequestBody RateioDto dto) {
        RateioDto atualizado = rateioService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rateioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conta/{idConta}")
    public ResponseEntity<List<RateioDto>> findByConta(@PathVariable Long idConta) {
    List<RateioDto> lista = rateioService.findByContaId(idConta);
    return ResponseEntity.ok(lista);
    }

    @GetMapping("/morador/{idMorador}")
    public ResponseEntity<List<RateioDto>> findByMorador(@PathVariable Long idMorador) {
    List<RateioDto> lista = rateioService.findByMoradorId(idMorador);
    return ResponseEntity.ok(lista);
    }

}
