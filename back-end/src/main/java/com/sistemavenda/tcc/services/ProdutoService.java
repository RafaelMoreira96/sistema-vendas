package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Produto;
import com.sistemavenda.tcc.domain.dtos.ProdutoDTO;
import com.sistemavenda.tcc.repositories.ProdutoRepository;
import com.sistemavenda.tcc.services.exceptions.DataIntegrityViolationException;
import com.sistemavenda.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    // Procura do BD por ID
    public Produto findById(Integer id) {
        Optional<Produto> o = repository.findById(id);
        return o.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado. ID: " + id));
    }

    // Procura por codBarras
    public Produto findByCodBarras(String codBarras) {
        Optional<Produto> o = repository.findByCodBarras(codBarras);
        return o.orElseThrow(
                () -> new ObjectNotFoundException("Objeto não encontrado. ID: " + codBarras));
    }
    // Lista todos
    public List<Produto> findAll() {
        List<Produto> listDB = repository.findAll();
        List<Produto> list = new ArrayList<>();
        for (Produto p : listDB) {
            if (p.getStatus() == true) {
                list.add(p);
            }
        }
        return list;
    }

    // Cadastrar produtos
    public Produto create(@Valid ProdutoDTO o) {
        o.setId(null);
        validaPorCodBarras(o);
        return repository.save(valida(o));
    }

    // Atualizar produto
    public Produto update(Integer id, @Valid ProdutoDTO pDTO) {
        pDTO.setId(id);
        Produto p = findById(id);
        if (pDTO.getCodBarras().equals(p.getCodBarras())) {
            p = valida(pDTO);
        } else {
            validaPorCodBarras(pDTO);
            p = valida(pDTO);
        }
        return repository.save(p);
    }

    /*
     * Valida por codigo de barras, para evitar duplicidade de dados
     */
    private void validaPorCodBarras(ProdutoDTO pDTO) {
        Optional<Produto> obj = repository.findByCodBarras(pDTO.getCodBarras());
        if (obj.isPresent() && obj.get().getCodBarras() != pDTO.getCodBarras()) {
            throw new DataIntegrityViolationException("Código de Barras existente!");
        }
    }

    /*
     * Deletar produto
     * Aqui, o produto não deve ser apagado. Ao invés disso, ele deve ser
     * desativado, através do atributo status, setando true ou false.
     */
    public void delete(Integer id) {
        Produto p = findById(id);
        p.setStatus(false);
        repository.save(p);
    }

    /*
     * Metodo pra validar se o objeto está vazio ou não
     * Este método será usado tanto nos métodos update e create
     */
    public Produto valida(ProdutoDTO pDTO) {
        Produto p = new Produto();

        if (pDTO.getId() != null) {
            p.setId(pDTO.getId());
        }

        p.setCodBarras(pDTO.getCodBarras());
        p.setDescricao(pDTO.getDescricao());
        p.setPrecoAtacado(pDTO.getPrecoAtacado());
        p.setPrecoVarejo(pDTO.getPrecoVarejo());
        p.setQteEstoque(pDTO.getQteEstoque());
        p.setQteMax(pDTO.getQteMax());
        p.setQteMin(pDTO.getQteMin());

        return p;
    }
}
