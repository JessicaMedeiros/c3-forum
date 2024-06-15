package com.alura.forum.controller;

import com.alura.forum.model.DadosAtualizacaoTopico;
import com.alura.forum.model.DadosListagemTopico;
import com.alura.forum.model.Topico;
import com.alura.forum.model.TopicoDTO;
import com.alura.forum.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    public TopicoRepository repository;

    @GetMapping

    public Page<DadosListagemTopico> listarTopicos(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemTopico::new);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid TopicoDTO json ){
        repository.save(new Topico(json));
        System.out.println(json);
    }


    @GetMapping("/{id}")
    public Optional<Topico> buscarTopico (@PathVariable Long id){
        Optional<Topico> topicoEncontrado = repository.findById(id);

        return topicoEncontrado;
    }


    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dadosAtualizacaoTopico){
        var topicoEncontrado = repository.findById(id);

        if(topicoEncontrado.isPresent()) {
            Topico topico = topicoEncontrado.get();
            topico.atualizarInformacoes(dadosAtualizacaoTopico);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id){
        var topicoEncontrado = repository.findById(id);

        if(topicoEncontrado.isPresent()) {
            repository.deleteById(id);
        }
    }
}
