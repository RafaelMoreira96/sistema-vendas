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

import com.sistemavenda.tcc.domain.Venda;
import com.sistemavenda.tcc.domain.dtos.VendaDTO;
import com.sistemavenda.tcc.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaResource {
    @Autowired
    private VendaService service;

    // Procura por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Integer id) {
        Venda obj = service.findById(id);
        return ResponseEntity.ok().body(new VendaDTO(obj));
    }

    // Lista todos
    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll() {
        List<Venda> list = service.findAll();
        List<VendaDTO> listDTO = list.stream().map(obj -> new VendaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // Cadastrar cliente
    @PostMapping
    public ResponseEntity<VendaDTO> create(@Valid @RequestBody VendaDTO vDTO) {
        Venda v = service.create(vDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(v.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Cancelar venda
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> cancel(@PathVariable Integer id) {
        service.cancelVenda(id);
        return ResponseEntity.noContent().build();
    }

}