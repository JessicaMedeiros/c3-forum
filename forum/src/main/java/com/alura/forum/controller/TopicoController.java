package com.alura.forum.controller;

import com.alura.forum.infra.security.SecurityFilter;
import com.alura.forum.model.*;
import com.alura.forum.repository.TopicoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    public TopicoRepository repository;

    @Autowired
    public SecurityFilter filter;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico> >listarTopicos(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid TopicoDTO json, UriComponentsBuilder uriBuilder, HttpServletRequest request){
        var topico = new Topico(json);

        var autor = filter.resgatarUsuario(request);

        topico.setAutor(autor);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        System.out.println(json);
        return ResponseEntity.created(uri).body(new DadosListagemTopico(topico));
    }


    @GetMapping("/{id}")
    public ResponseEntity  buscarTopico (@PathVariable Long id) {
        var topicoEncontrado = repository.findById(id);

        if(topicoEncontrado.isPresent()){
            Topico topico = topicoEncontrado.get();
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
        }

        return ResponseEntity.noContent().build();

    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dadosAtualizacaoTopico, HttpServletRequest request){
        var topicoEncontrado = repository.findById(id);

        if(topicoEncontrado.isPresent()) {
            Topico topico = topicoEncontrado.get();

            if(verificarSeAutor(topico.getAutor().getId(), request)){
                topico.atualizarInformacoes(dadosAtualizacaoTopico);
                return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id, HttpServletRequest request){
        var topicoEncontrado = repository.findById(id);

        if(topicoEncontrado.isPresent()) {
            Topico topico = topicoEncontrado.get();

            if(!verificarSeAutor(topico.getAutor().getId(), request)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            repository.deleteById(id);

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.noContent().build();
    }

    private Boolean verificarSeAutor(Long idAutorTopico, HttpServletRequest request){
       var requisitor = filter.resgatarUsuario(request);

       return Objects.equals(requisitor.getId(), idAutorTopico);
    }

}
