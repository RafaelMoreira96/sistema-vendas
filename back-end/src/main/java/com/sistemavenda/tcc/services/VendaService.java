package com.sistemavenda.tcc.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    // Listar por período específico
    public List<Venda> findByDate(LocalDate firstDate, LocalDate secondDate) {
        List<Venda> vendas = repository.findByDataVendaBetween(firstDate, secondDate);
        if (vendas.isEmpty()) {
            throw new ObjectNotFoundException("Nenhuma venda encontrada entre " + firstDate + " e " + secondDate);
        }
        return vendas;
    }

    // Lista todos
    public List<Venda> findAll() {
        List<Venda> listDB = repository.findAll();
        List<Venda> list = new ArrayList<>();
        for (Venda venda : listDB) {
            if (venda.getStatus().equals(StatusVenda.FINALIZADO) || venda.getStatus().equals(StatusVenda.ANDAMENTO)) {
                list.add(venda);
            }
        }
        return list;
    }

    // Cadastrar venda
    public Venda create(@Valid VendaDTO vendaDTO) {
        // Tratando Cliente
        Optional<Cliente> clienteOptional = clienteRepository.findById(vendaDTO.getCliente());
        Cliente c = clienteOptional.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));

        // Tratando Funcionario
        Optional<Funcionario> funcionario = funcionarioRepository.findById(vendaDTO.getFuncionario());
        Funcionario f = new Funcionario(funcionario.get().getId(), funcionario.get().getNome());

        // Tratando lista de produtos
        List<ItemVenda> listTemp = vendaDTO.getItens();
        List<ItemVenda> list = new ArrayList<>();
        Produto produto = new Produto();
        for (ItemVenda itemVenda : listTemp) {
            Optional<Produto> produtoTemporario = produtoRepository.findById(itemVenda.getIdProduto());
            double quantidadeEstoque = produtoTemporario.get().getQteEstoque() - itemVenda.getQuant();
            produtoTemporario.get().setQteEstoque(quantidadeEstoque);
            if (itemVenda.getCodBarras().equals(produtoTemporario.get().getCodBarras())) {
                list.add(itemVenda);
            } else {
                itemVenda.setCodBarras(produtoTemporario.get().getCodBarras());
                list.add(itemVenda);
            }

            produto.setId(produtoTemporario.get().getId());
            produto.setCodBarras(produtoTemporario.get().getCodBarras());
            produto.setDescricao(produtoTemporario.get().getDescricao());
            produto.setPrecoAtacado(produtoTemporario.get().getPrecoAtacado());
            produto.setPrecoVarejo(produtoTemporario.get().getPrecoVarejo());
            produto.setQteEstoque(quantidadeEstoque);
            produto.setQteMax(produtoTemporario.get().getQteMax());
            produto.setQteMin(produtoTemporario.get().getQteMin());

            produtoRepository.save(produto);
        }

        itemVendaRepository.saveAll(list);

        // Finalizando venda
        Venda v = new Venda(vendaDTO);
        v.setCliente(c);
        v.setFuncionario(f);
        v.setListaProdutos(list);
        v.setValorVenda(list);
        v.setNumeroVenda(vendaDTO.getNumeroVenda());
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
