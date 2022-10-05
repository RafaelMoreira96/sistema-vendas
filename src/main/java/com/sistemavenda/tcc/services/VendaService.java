package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Venda;
import com.sistemavenda.tcc.domain.dtos.VendaDTO;
import com.sistemavenda.tcc.repositories.VendaRepository;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;

    // Busca por ID
    public Venda findById(Integer id) {
        Optional<Venda> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException(id, "Cliente não encontrado!"));
    }

    // Lista todos
    public List<Venda> findAll() {
        List<Venda> listDB = repository.findAll();
        List<Venda> list = new ArrayList<>();
        for (Venda v : listDB) {
            list.add(v);
        }
        return list;
    }

    // Cadastrar venda
    public Venda create(@Valid VendaDTO vDTO) {
        vDTO.setId(null);
        Venda v = new Venda(vDTO);
        return repository.save(v);
    }

    /*
     * // Atualizar cliente
     * public Cliente update(Integer id, @Valid ClienteDTO cDTO) {
     * cDTO.setId(id);
     * Cliente c = findById(id);
     * c = valida(cDTO);
     * return repository.save(c);
     * }
     * 
     * /*
     * "Remover" cliente: aqui não pode ser deletado um cliente, ele deve ser
     * "desativado", através do atributo
     *
     * public void delete(Integer id) {
     * Cliente c = findById(id);
     * c.setStatus(false);
     * repository.save(c);
     * }
     */
}