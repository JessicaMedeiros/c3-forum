package com.alura.forum.controller;

import com.alura.forum.model.*;
import com.alura.forum.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    public TopicoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico> >listarTopicos(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid TopicoDTO json, UriComponentsBuilder uriBuilder){
        var topico = new Topico(json);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        System.out.println(json);
        return ResponseEntity.created(uri).body(new DadosListagemTopico(topico));
    }


    @GetMapping("/{id}")
    public ResponseEntity  buscarTopico (@PathVariable Long id) {
        var topicoEncontrado = repository.findById(id);

        return ResponseEntity.ok(topicoEncontrado);

    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dadosAtualizacaoTopico){
        var topicoEncontrado = repository.findById(id);

        if(topicoEncontrado.isPresent()) {
            Topico topico = topicoEncontrado.get();
            topico.atualizarInformacoes(dadosAtualizacaoTopico);
            return ResponseEntity.ok(new DadosListagemTopico(topico));
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var topicoEncontrado = repository.findById(id);

        if(topicoEncontrado.isPresent()) {
            repository.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }


}
