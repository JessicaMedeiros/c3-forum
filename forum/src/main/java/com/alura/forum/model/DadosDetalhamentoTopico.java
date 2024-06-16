package com.alura.forum.model;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(String titulo,
                                      String mensagem,
                                      String autor,
                                      String curso,
                                      LocalDateTime dataCriacao) {
    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso(), topico.getDataCriacao());
    }
}
