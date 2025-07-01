package com.cefet.sgr_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cefet.sgr_backend.dto.HistoricoDTO;
import com.cefet.sgr_backend.services.HistoricoService;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;


    @GetMapping
    public ResponseEntity<List<HistoricoDTO>> findAll() {
        List<HistoricoDTO> lista = historicoService.findAll();
        return ResponseEntity.ok(lista);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<HistoricoDTO> findById(@PathVariable Long id) {
        HistoricoDTO dto = historicoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<HistoricoDTO> insert(@RequestBody HistoricoDTO dto) {
        HistoricoDTO novo = historicoService.insert(dto);
        return ResponseEntity.status(201).body(novo);
    }


}