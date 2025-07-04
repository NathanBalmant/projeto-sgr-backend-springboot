package com.cefet.sgr_backend.controllers;

import java.time.LocalDate;
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

import com.cefet.sgr_backend.dto.ContaDTO;
import com.cefet.sgr_backend.services.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/moradores/{moradorId}")
    public ResponseEntity<List<ContaDTO>> findByMorador(@PathVariable Long moradorId) {
        List<ContaDTO> lista = contaService.findByMorador(moradorId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        List<ContaDTO> lista = contaService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable Long id) {
        ContaDTO dto = contaService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ContaDTO> insert(@RequestBody ContaDTO dto) {
        ContaDTO nova = contaService.insert(dto);
        return ResponseEntity.status(201).body(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> update(@PathVariable Long id, @RequestBody ContaDTO dto) {
        ContaDTO atualizada = contaService.update(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/quitar")
    public ResponseEntity<ContaDTO> quitarConta(@PathVariable Long id, @RequestParam Long moradorId) {
        ContaDTO dto = contaService.quitarConta(id, moradorId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ContaDTO> cancelarConta(@PathVariable Long id, @RequestParam Long moradorId) {
        ContaDTO dto = contaService.cancelarConta(id, moradorId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/reabrir")
    public ResponseEntity<ContaDTO> reabrirConta(@PathVariable Long id, @RequestParam Long moradorId) {
        ContaDTO dto = contaService.reabrirConta(id, moradorId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/replicar")
    public ResponseEntity<ContaDTO> copiarConta(@PathVariable Long id) {
        ContaDTO nova = contaService.replicar(id);
        return ResponseEntity.status(201).body(nova);
    }

    @GetMapping("/extrato")
    public ResponseEntity<List<ContaDTO>> getExtratoPorPeriodo(
        @RequestParam String dataInicial,
        @RequestParam String dataFinal) {

    LocalDate inicio = LocalDate.parse(dataInicial);
    LocalDate fim = LocalDate.parse(dataFinal);

    List<ContaDTO> extrato = contaService.buscarExtratoPorPeriodo(inicio, fim);
    return ResponseEntity.ok(extrato);
    }
}
