package com.alura.forum.model;

import com.alura.forum.model.usuario.DadosUsuarios;
import com.alura.forum.model.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(String titulo,
                                      String mensagem,
                                      DadosUsuarios autor,
                                      String curso,
                                      LocalDateTime dataCriacao) {
    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensagem(), new DadosUsuarios(topico.getAutor()) , topico.getCurso(), topico.getDataCriacao());
    }
}
