package com.alura.forum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.text.DateFormat;
import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        @NotBlank
        @Column(unique=true)
        String titulo,
        @NotBlank
        @Column(unique=true)
        String mensagem,
        @NotBlank
        String curso
) {
}
