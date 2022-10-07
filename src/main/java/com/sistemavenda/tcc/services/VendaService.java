package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.ItemVenda;
import com.sistemavenda.tcc.domain.Venda;
import com.sistemavenda.tcc.domain.dtos.VendaDTO;
import com.sistemavenda.tcc.domain.enums.StatusVenda;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.repositories.ItemVendaRepository;
import com.sistemavenda.tcc.repositories.VendaRepository;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private ItemVendaRepository itemVendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

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
        // Tratando Cliente
        Optional<Cliente> cli = clienteRepository.findById(vDTO.getCliente());
        Cliente c = new Cliente(cli.get().getId(), cli.get().getNome());

        // Tratando Funcionario
        Optional<Funcionario> fun = funcionarioRepository.findById(vDTO.getFuncionario());
        Funcionario f = new Funcionario(fun.get().getId(), fun.get().getNome());

        // Tratando lista de produtos
        List<ItemVenda> itens = vDTO.getItens();
        itemVendaRepository.saveAll(itens);

        // Finalizando venda
        Venda v = new Venda(vDTO);
        v.setCliente(c);
        v.setFuncionario(f);
        v.setListaProdutos(itens);
        v.setValorVenda(itens);
        v.setNumeroVenda(vDTO.getNumeroVenda());
        v.setStatus(StatusVenda.FINALIZADO);
        return repository.save(v);
    }

    public void cancelVenda(Integer id) {
        Venda v = findById(id);
        v.setStatus(StatusVenda.CANCELADO);
        repository.save(v);
    }

}