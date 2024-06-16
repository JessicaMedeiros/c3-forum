package com.alura.forum.model.usuario;

public record DadosUsuarios (String email, Long id) {

    public DadosUsuarios(Usuario dados) {
        this(dados.getEmail(), dados.getId());
    }
}

