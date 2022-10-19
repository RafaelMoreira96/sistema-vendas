package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.ItemVenda;
import com.sistemavenda.tcc.domain.Produto;
import com.sistemavenda.tcc.domain.Venda;
import com.sistemavenda.tcc.domain.dtos.VendaDTO;
import com.sistemavenda.tcc.domain.enums.StatusVenda;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.repositories.ItemVendaRepository;
import com.sistemavenda.tcc.repositories.ProdutoRepository;
import com.sistemavenda.tcc.repositories.VendaRepository;
import com.sistemavenda.tcc.services.exceptions.ObjectNotFoundException;

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
    @Autowired
    private ProdutoRepository produtoRepository;

    // Busca por ID
    public Venda findById(Integer id) {
        Optional<Venda> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException("Venda não encontrada! ID: " + id));
    }

    // Lista todos
    public List<Venda> findAll() {
        List<Venda> listDB = repository.findAll();
        List<Venda> list = new ArrayList<>();
        for (Venda v : listDB) {
            if (v.getStatus().equals(StatusVenda.FINALIZADO) || v.getStatus().equals(StatusVenda.ANDAMENTO)) {
                list.add(v);
            }
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
        List<ItemVenda> listTemp = vDTO.getItens();
        List<ItemVenda> list = new ArrayList<>();
        Produto p = new Produto();
        for (ItemVenda itemVenda : listTemp) {
            Optional<Produto> objTemp = produtoRepository.findById(itemVenda.getIdProduto());
            double est = objTemp.get().getQteEstoque() - itemVenda.getQuant();
            objTemp.get().setQteEstoque(est);
            if (itemVenda.getCodBarras().equals(objTemp.get().getCodBarras())) {
                list.add(itemVenda);
            } else {
                itemVenda.setCodBarras(objTemp.get().getCodBarras());
                list.add(itemVenda);
            }

            p.setId(objTemp.get().getId());
            p.setCodBarras(objTemp.get().getCodBarras());
            p.setDescricao(objTemp.get().getDescricao());
            p.setPrecoAtacado(objTemp.get().getPrecoAtacado());
            p.setPrecoVarejo(objTemp.get().getPrecoVarejo());
            p.setQteEstoque(est);
            p.setQteMax(objTemp.get().getQteMax());
            p.setQteMin(objTemp.get().getQteMin());

            produtoRepository.save(p);
        }

        itemVendaRepository.saveAll(list);

        // Finalizando venda
        Venda v = new Venda(vDTO);
        v.setCliente(c);
        v.setFuncionario(f);
        v.setListaProdutos(list);
        v.setValorVenda(list);
        v.setNumeroVenda(vDTO.getNumeroVenda());
        v.setStatus(StatusVenda.FINALIZADO);
        return repository.save(v);
    }

    public void cancelVenda(Integer id) {
        Venda v = findById(id);
        Produto p = new Produto();
        for (ItemVenda itemVenda : v.getItens()) {
            Optional<Produto> objTemp = produtoRepository.findById(itemVenda.getIdProduto());
            double est = objTemp.get().getQteEstoque() + itemVenda.getQuant();

            p.setId(objTemp.get().getId());
            p.setCodBarras(objTemp.get().getCodBarras());
            p.setDescricao(objTemp.get().getDescricao());
            p.setPrecoAtacado(objTemp.get().getPrecoAtacado());
            p.setPrecoVarejo(objTemp.get().getPrecoVarejo());
            p.setQteEstoque(est);
            p.setQteMax(objTemp.get().getQteMax());
            p.setQteMin(objTemp.get().getQteMin());

            produtoRepository.save(p);
            itemVenda.getQuant();
        }
        v.setStatus(StatusVenda.CANCELADO);
        repository.save(v);
    }

}

