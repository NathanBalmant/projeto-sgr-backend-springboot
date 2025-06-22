package com.cefet.sgr_backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cefet.sgr_backend.dto.MoradorDTO;
import com.cefet.sgr_backend.services.MoradorService;

@RestController
@RequestMapping("/moradores")
public class MoradorController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MoradorService moradorService;

    // Buscar todos os moradores
    @GetMapping
    public ResponseEntity<List<MoradorDTO>> findAll() {
        List<MoradorDTO> lista = moradorService.findAll();
        return ResponseEntity.ok(lista);
    }

    // Buscar morador por ID
    @GetMapping("/{id}")
    public ResponseEntity<MoradorDTO> findById(@PathVariable Long id) {
        MoradorDTO dto = moradorService.findById(id);
        return ResponseEntity.ok(dto);
    }

    // Inserir novo morador
    @PostMapping
    public ResponseEntity<MoradorDTO> insert(@RequestBody MoradorDTO moradorDTO) {
        MoradorDTO dto = moradorService.insert(moradorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // Atualizar morador
    @PutMapping("/{id}")
    public ResponseEntity<MoradorDTO> update(@PathVariable Long id, @RequestBody MoradorDTO moradorDTO) {
        MoradorDTO dto = moradorService.update(id, moradorDTO);
        return ResponseEntity.ok(dto);
    }

    // Deletar morador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        moradorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint de login simples
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String login = body.get("login");
        String senha = body.get("senha");

        if (login == null || senha == null) {
            return ResponseEntity.badRequest().body("Login e senha são obrigatórios.");
        }

        return moradorService.findByLogin(login)
                .map(morador -> {
                    boolean senhaCorreta = passwordEncoder.matches(senha, morador.getSenha());
                    if (senhaCorreta) {
                        return ResponseEntity.ok("Login bem-sucedido.");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta.");
                    }
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login não encontrado."));
    }
}
