package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeRepository cidadeRepository;


    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cidadeRepository.buscar(cidadeId);

        if (cidade != null) {
            return ResponseEntity.ok(cidade);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidadeService.salvar(cidade);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId,
                                            @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

        if (cidadeAtual != null) {
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);
            return ResponseEntity.ok(cidadeAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cidadeAId}")
    public ResponseEntity<?> remover(@PathVariable Long cidadeAId) {
        try {
            cadastroCidadeService.excluir(cidadeAId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }




}
