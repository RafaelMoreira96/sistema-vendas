package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.Fornecedor;
import com.sistemavenda.tcc.domain.dtos.FornecedorDTO;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;
import com.sistemavenda.tcc.repositories.FornecedorRepository;
import com.sistemavenda.tcc.services.exceptions.DataIntegrityViolationException;
import com.sistemavenda.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ContatoRepository contatoRepository;

    // Busca por ID
    public Fornecedor findById(Integer id) {
        Optional<Fornecedor> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException("Fornecedor não encontrado! ID: " + id));
    }

    // Lista todos
    public List<Fornecedor> findAll() {
        List<Fornecedor> listDB = repository.findAll();
        List<Fornecedor> list = new ArrayList<>();
        for (Fornecedor f : listDB) {
            if (f.getStatus() == true) {
                list.add(f);
            }
        }
        return list;
    }

    // Cadastrar fornecedor
    public Fornecedor create(@Valid FornecedorDTO fDTO) {
        validaCnpj(fDTO);

        // Persistencia do objeto Endereco
        Endereco e = new Endereco();
        e.setCep(fDTO.getEndereco().getCep());
        e.setNumero(fDTO.getEndereco().getNumero());
        e.setComplemento(fDTO.getEndereco().getComplemento());
        e.setBairro(fDTO.getEndereco().getBairro());
        e.setCidade(fDTO.getEndereco().getCidade());
        e.setEstado(fDTO.getEndereco().getEstado());
        e.setLogradouro(fDTO.getEndereco().getLogradouro());
        enderecoRepository.save(e);

        // Persistencia do objeto Contato
        List<Contato> contatos = new ArrayList<>();
        for (Contato contato : fDTO.getContatos()) {
            contato.setId(null);
            contatos.add(contato);
        }
        fDTO.setContatos(contatos);
        contatoRepository.saveAll(contatos);

        // Persistencia do Objeto Funcionario
        Fornecedor f = new Fornecedor();
        f.setNome(fDTO.getNome()); // aqui é razao social
        f.setNomeFantasia(fDTO.getNomeFantasia());
        f.setInscricaoEstadual(fDTO.getInscricaoEstadual());
        f.setCnpj(fDTO.getCnpj());
        
        f.setEndereco(e);
        f.setContatos(contatos);

        return repository.save(f);
    }

    // Atualizar fornecedor
    public Fornecedor update(Integer id, @Valid FornecedorDTO fDTO) {
        fDTO.setId(id);
        Fornecedor fornDatabase = findById(id);
        if (fDTO.getCnpj().equals(fornDatabase.getCnpj())) {
            fDTO.getEndereco().setId(fornDatabase.getEndereco().getId());
        } else {
            validaCnpj(fDTO);
            fDTO.getEndereco().setId(fornDatabase.getEndereco().getId());
        }

        // Persistencia do objeto Endereco
        Endereco e = new Endereco();
        e.setId(fDTO.getEndereco().getId());
        e.setCep(fDTO.getEndereco().getCep());
        e.setNumero(fDTO.getEndereco().getNumero());
        e.setComplemento(fDTO.getEndereco().getComplemento());
        e.setBairro(fDTO.getEndereco().getBairro());
        e.setCidade(fDTO.getEndereco().getCidade());
        e.setEstado(fDTO.getEndereco().getEstado());
        e.setLogradouro(fDTO.getEndereco().getLogradouro());
        enderecoRepository.save(e);

        // Preparando objeto Contato e persistindo
        Fornecedor temp = findById(fDTO.getId());
        List<Contato> contatos = new ArrayList<>();
        for (Contato contato : fDTO.getContatos()) {
            contato.setId(null);
            contatos.add(contato);
        }
        fDTO.setContatos(contatos);
        for (int i = 0; i < temp.getContatos().size(); i++) {
            Contato contato = temp.getContatos().get(i);
            contatos.get(i).setId(contato.getId());
        }
        contatoRepository.saveAll(contatos);

        // Persistencia do Objeto Funcionario
        Fornecedor f = new Fornecedor();
        f.setId(id);
        f.setNome(fDTO.getNome());
        f.setCnpj(fDTO.getCnpj());
        f.setEndereco(e);
        f.setContatos(contatos);

        return repository.save(f);
    }

    // Remover fornecedor
    public void delete(Integer id) {
        Fornecedor f = findById(id);
        f.setStatus(false);
        repository.save(f);
    }

    // Validações
    public Fornecedor valida(FornecedorDTO fDTO) {
        Endereco e = new Endereco();
        List<Contato> contatos = new ArrayList<>();
        Fornecedor f = new Fornecedor();

        e.setId(fDTO.getEndereco().getId());
        e.setCep(fDTO.getEndereco().getCep());
        e.setNumero(fDTO.getEndereco().getNumero());
        e.setComplemento(fDTO.getEndereco().getComplemento());
        e.setBairro(fDTO.getEndereco().getBairro());
        e.setCidade(fDTO.getEndereco().getCidade());
        e.setEstado(fDTO.getEndereco().getEstado());
        e.setLogradouro(fDTO.getEndereco().getLogradouro());

        for (Contato contato : fDTO.getContatos()) {
            contatos.add(contato);
        }
        enderecoRepository.save(e);
        contatoRepository.saveAll(contatos);

        if (fDTO.getId() != null) {
            f.setId(fDTO.getId());
        }

        f.setContatos(contatos);
        f.setEndereco(e);
        f.setCnpj(fDTO.getCnpj());
        f.setNome(fDTO.getNome());
        f.setNomeFantasia(fDTO.getNomeFantasia());
        f.setInscricaoEstadual(fDTO.getInscricaoEstadual());

        return f;
    }

    public void validaCnpj(FornecedorDTO f) {
        Optional<Fornecedor> obj = repository.findByCnpj(f.getCnpj());
        if (obj.isPresent() && obj.get().getCpf() != f.getCnpj()) {
            throw new DataIntegrityViolationException("CNPJ já cadastrado!");
        }
    }
}