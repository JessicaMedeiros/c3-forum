package com.alura.forum.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosListagemTopico (
    Long id,
    String titulo,
    String mensagem,
    String curso,
    LocalDateTime dataCriacao
)
{
    public DadosListagemTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getCurso(), topico.getDataCriacao());
    }
}