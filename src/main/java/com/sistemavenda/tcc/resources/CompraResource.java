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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistemavenda.tcc.domain.Compra;
import com.sistemavenda.tcc.domain.dtos.CompraDTO;
import com.sistemavenda.tcc.services.CompraService;

@RestController
@RequestMapping(value = "/compras")
public class CompraResource {
    @Autowired
    private CompraService service;

    // Procura por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<CompraDTO> findById(@PathVariable Integer id) {
        Compra obj = service.findById(id);
        return ResponseEntity.ok().body(new CompraDTO(obj));
    }

    // Lista todos
    @GetMapping
    public ResponseEntity<List<CompraDTO>> findAll() {
        List<Compra> list = service.findAll();
        List<CompraDTO> listDTO = list.stream().map(obj -> new CompraDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // Cadastrar cliente
    @PostMapping
    public ResponseEntity<CompraDTO> create(@Valid @RequestBody CompraDTO cDTO) {
        Compra v = service.create(cDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(v.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Cancelar venda
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CompraDTO> devolucaoCompra(@PathVariable Integer id) {
        service.devolucaoCompra(id);
        return ResponseEntity.noContent().build();
    }

}