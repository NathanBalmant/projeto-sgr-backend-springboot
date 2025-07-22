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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.sgr_backend.dto.RateioDTO;
import com.cefet.sgr_backend.services.RateioService;

@RestController
@RequestMapping("/rateios")
public class RateioController {

    @Autowired
    private RateioService rateioService;

    @GetMapping
    public ResponseEntity<List<RateioDTO>> findAll() {
        List<RateioDTO> lista = rateioService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RateioDTO> findById(@PathVariable Long id) {
        RateioDTO dto = rateioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RateioDTO> insert(@RequestBody RateioDTO dto) {
        RateioDTO novo = rateioService.insert(dto);
        return ResponseEntity.status(201).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RateioDTO> update(@PathVariable Long id, @RequestBody RateioDTO dto) {
        RateioDTO atualizado = rateioService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rateioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conta/{idConta}")
    public ResponseEntity<List<RateioDTO>> findByConta(@PathVariable Long idConta) {
    List<RateioDTO> lista = rateioService.findByContaId(idConta);
    return ResponseEntity.ok(lista);
    }

    @GetMapping("/morador/{idMorador}")
    public ResponseEntity<List<RateioDTO>> findByMorador(@PathVariable Long idMorador) {
    List<RateioDTO> lista = rateioService.findByMoradorId(idMorador);
    return ResponseEntity.ok(lista);
    }

    @GetMapping("/morador/{idMorador}/saldo")
    public ResponseEntity<Double> calcularSaldo(@PathVariable Long idMorador) {
    Double saldo = rateioService.calcularSaldoMorador(idMorador);
    return ResponseEntity.ok(saldo);
    }

    @PutMapping("/{id}/quitar")
    public ResponseEntity<RateioDTO> quitarRateio(@PathVariable Long id, @RequestParam Long moradorId) {
        RateioDTO dto = rateioService.quitarRateio(id, moradorId);
        return ResponseEntity.ok(dto);
    }

}
