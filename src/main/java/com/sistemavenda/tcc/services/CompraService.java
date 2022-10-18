package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Compra;
import com.sistemavenda.tcc.domain.Fornecedor;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.ItemCompra;
import com.sistemavenda.tcc.domain.Produto;
import com.sistemavenda.tcc.domain.dtos.CompraDTO;
import com.sistemavenda.tcc.domain.enums.StatusCompra;
import com.sistemavenda.tcc.repositories.CompraRepository;
import com.sistemavenda.tcc.repositories.FornecedorRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.repositories.ItemCompraRepository;
import com.sistemavenda.tcc.repositories.ProdutoRepository;
import com.sistemavenda.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class CompraService {
    @Autowired
    private CompraRepository repository;
    @Autowired
    private ItemCompraRepository itemCompraRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    // Busca por ID
    public Compra findById(Integer id) {
        Optional<Compra> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException("Compra não encontrada! ID: " + id));
    }

    // Lista todos
    public List<Compra> findAll() {
        List<Compra> listDB = repository.findAll();
        List<Compra> list = new ArrayList<>();
        for (Compra c : listDB) {
            list.add(c);
        }
        return list;
    }

    // Cadastrar venda
    public Compra create(@Valid CompraDTO cDTO) {
        // Tratando Fornecedor
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(cDTO.getFornecedor());
        Fornecedor forn = new Fornecedor(fornecedor.get().getId(), fornecedor.get().getNome());

        // Tratando Funcionario
        Optional<Funcionario> funcionario = funcionarioRepository.findById(cDTO.getFuncionario());
        Funcionario func = new Funcionario(funcionario.get().getId(), funcionario.get().getNome());

        // Tratando lista de produtos
        List<ItemCompra> listTemp = cDTO.getItens();
        List<ItemCompra> list = new ArrayList<>();
        Produto p = new Produto();
        for (ItemCompra itemCompra : listTemp) {
            Optional<Produto> objTemp = produtoRepository.findById(itemCompra.getIdProduto());
            double est = objTemp.get().getQteEstoque() + itemCompra.getQuant();
            objTemp.get().setQteEstoque(est);
            if (itemCompra.getCodBarras().equals(objTemp.get().getCodBarras())) {
                list.add(itemCompra);
            } else {
                itemCompra.setCodBarras(objTemp.get().getCodBarras());
                list.add(itemCompra);
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

        itemCompraRepository.saveAll(list);

        // Finalizando venda
        Compra c = new Compra(cDTO);
        c.setFornecedor(forn);
        c.setFuncionario(func);
        c.setItens(list);
        c.setValorTotal(list);
        c.setNumeroCompra(cDTO.getId());
        c.setStatus(StatusCompra.FINALIZADO);
        return repository.save(c);
    }

    public void devolucaoCompra(Integer id) {
        Compra c = findById(id);
        Produto p = new Produto();
        for (ItemCompra itemCompra : c.getItens()) {
            Optional<Produto> objTemp = produtoRepository.findById(itemCompra.getIdProduto());
            double est = objTemp.get().getQteEstoque() - itemCompra.getQuant();

            p.setId(objTemp.get().getId());
            p.setCodBarras(objTemp.get().getCodBarras());
            p.setDescricao(objTemp.get().getDescricao());
            p.setPrecoAtacado(objTemp.get().getPrecoAtacado());
            p.setPrecoVarejo(objTemp.get().getPrecoVarejo());
            p.setQteEstoque(est);
            p.setQteMax(objTemp.get().getQteMax());
            p.setQteMin(objTemp.get().getQteMin());

            produtoRepository.save(p);
            itemCompra.getQuant();
        }
        c.setStatus(StatusCompra.DEVOLUCAO);
        repository.save(c);
    }

}