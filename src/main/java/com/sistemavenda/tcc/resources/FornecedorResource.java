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

import com.sistemavenda.tcc.domain.Fornecedor;
import com.sistemavenda.tcc.domain.dtos.FornecedorDTO;
import com.sistemavenda.tcc.services.FornecedorService;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorResource {
    @Autowired
    private FornecedorService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FornecedorDTO> findById(@PathVariable Integer id) {
        Fornecedor obj = service.findById(id);
        return ResponseEntity.ok().body(new FornecedorDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> findAll(){
        List<Fornecedor> list = service.findAll();
        List<FornecedorDTO> listDTO = list.stream().map(obj -> new FornecedorDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> create(@Valid @RequestBody FornecedorDTO fDTO){
        Fornecedor f = service.create(fDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(f.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

     @PutMapping(value = "/{id}")
     public ResponseEntity<FornecedorDTO> update(@PathVariable Integer id, @RequestBody FornecedorDTO fDTO){
         Fornecedor f = service.update(id, fDTO);
         return ResponseEntity.ok().body(new FornecedorDTO(f));
     }
 
     @DeleteMapping(value = "/{id}")
     public ResponseEntity<FornecedorDTO> delete(@PathVariable Integer id){
         service.delete(id);
         return ResponseEntity.noContent().build();
     }
    
}