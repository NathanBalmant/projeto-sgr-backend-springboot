package com.cefet.sgr_backend.dto;

import java.time.LocalDateTime;


import com.cefet.sgr_backend.entities.Historico;

public class HistoricoDTO {
    private Long id;
    private Long idConta;
    private Long idMorador;
    private LocalDateTime data;
    private Byte situacao;

   public HistoricoDTO(){

   }

     public HistoricoDTO(Historico historico) {
        this.id = historico.getId();
        this.idConta = historico.getConta().getId();
        this.idMorador = historico.getMorador().getId();
        this.data = historico.getData();
        this.situacao = historico.getSituacao();
    }

     public Long getId() {
         return id;
     }

     public Long getIdConta() {
         return idConta;
     }

     public Long getIdMorador() {
         return idMorador;
     }

     public LocalDateTime getData() {
         return data;
     }

     public Byte getSituacao() {
         return situacao;
     }

    



}
