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

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.dtos.ClienteDTO;
import com.sistemavenda.tcc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    // Procura por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    // Lista todos
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    // Cadastrar cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO cDTO) {
        Cliente c = service.create(cDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // Atualizar cliente
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO cDTO) {
        Cliente c = service.update(id, cDTO);
        return ResponseEntity.ok().body(new ClienteDTO(c));
    }

    // Deletar cliente
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}