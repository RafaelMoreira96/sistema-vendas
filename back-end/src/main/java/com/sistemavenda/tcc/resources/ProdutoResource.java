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

import com.sistemavenda.tcc.domain.Produto;
import com.sistemavenda.tcc.domain.dtos.ProdutoDTO;
import com.sistemavenda.tcc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    // Procura por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Integer id) {
        Produto p = service.findById(id);
        return ResponseEntity.ok().body(new ProdutoDTO(p));
    }

    // Procura por codBarras
    @GetMapping(value = "/cod/{codBarras}")
    public ResponseEntity<ProdutoDTO> findByCodBarras(@PathVariable String codBarras) {
        Produto p = service.findByCodBarras(codBarras);
        return ResponseEntity.ok().body(new ProdutoDTO(p));
    }

    // Lista todos
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<Produto> list = service.findAll();
        List<ProdutoDTO> listDTO = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // Cadastrar
    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO pDTO) {
        Produto p = service.create(pDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Atualizar produto
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO pDTO) {
        Produto p = service.update(id, pDTO);
        return ResponseEntity.ok().body(new ProdutoDTO(p));
    }

    // Deletar produto
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}