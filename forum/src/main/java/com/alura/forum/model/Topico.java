package com.alura.forum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name="topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    private LocalDateTime dataCriacao;

    private String status;

    private String autor;

    private String curso;

//
//    @Embedded
//    private Resposta resposta;


    public Topico(TopicoDTO topicoDTO){
        this.titulo = topicoDTO.titulo();
        this.mensagem = topicoDTO.mensagem();
        this.curso = topicoDTO.curso();
        this.autor = topicoDTO.autor();
        this.dataCriacao = LocalDateTime.now();
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }

    }
}
