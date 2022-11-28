package com.sistemavenda.tcc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistemavenda.tcc.domain.FormaPagamento;
import com.sistemavenda.tcc.domain.dtos.FormaPagamentoDTO;
import com.sistemavenda.tcc.services.FormaPagamentoService;

@RestController
@RequestMapping(value = "/formapagamentos")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamentoService service;

    // Procura por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<FormaPagamentoDTO> findById(@PathVariable Integer id) {
        FormaPagamento obj = service.findById(id);
        return ResponseEntity.ok().body(new FormaPagamentoDTO(obj));
    }

    // Lista todos
    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> findAll() {
        List<FormaPagamento> list = service.findAll();
        List<FormaPagamentoDTO> listDTO = list.stream().map(obj -> new FormaPagamentoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // Cadastrar cliente
    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> create(@Valid @RequestBody FormaPagamentoDTO cDTO) {
        FormaPagamento c = service.create(cDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Atualizar cliente
    @PutMapping(value = "/{id}")
    public ResponseEntity<FormaPagamentoDTO> update(@PathVariable Integer id, @RequestBody FormaPagamentoDTO cDTO) {
        FormaPagamento c = service.update(id, cDTO);
        return ResponseEntity.ok().body(new FormaPagamentoDTO(c));
    }

    // Deletar cliente
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FormaPagamentoDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}