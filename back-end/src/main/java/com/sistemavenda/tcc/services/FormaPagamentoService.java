package com.sistemavenda.tcc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.FormaPagamento;
import com.sistemavenda.tcc.domain.dtos.FormaPagamentoDTO;
import com.sistemavenda.tcc.repositories.FormaPagamentoRepository;
import com.sistemavenda.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository repository;

    // Busca por ID
    public FormaPagamento findById(Integer id) {
        Optional<FormaPagamento> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException("FormaPagamento não encontrado! ID: " + id));
    }

    // Lista todos
    public List<FormaPagamento> findAll() {
        return repository.findAll();
    }

    // Cadastrar cliente
    public FormaPagamento create(@Valid FormaPagamentoDTO fpDTO) {
        FormaPagamento fp = new FormaPagamento();
        fp.setId(null);
        fp.setDescricao(fpDTO.getDescricao());
        return repository.save(fp);
    }

    // Atualizar cliente
    public FormaPagamento update(Integer id, @Valid FormaPagamentoDTO fpDTO) {
        FormaPagamento fp = new FormaPagamento();
        fp.setId(id);
        fp.setDescricao(fpDTO.getDescricao());
        return repository.save(fp);
    }

    /*
     * "Remover" cliente: aqui não pode ser deletado um cliente, ele deve ser
     * "desativado", através do atributo
     */
    public void delete(Integer id) {
        FormaPagamento fp = findById(id);
        // c.setStatus(false);
        // repository.save(c);
        repository.delete(fp);
    }
}