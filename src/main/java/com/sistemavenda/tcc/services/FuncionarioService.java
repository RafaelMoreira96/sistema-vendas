package com.sistemavenda.tcc.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.dtos.FuncionarioDTO;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ContatoRepository contatoRepository;

    // Busca por ID
    public Funcionario findById(Integer id) {
        Optional<Funcionario> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException(id, "Funcionário não encontrado!"));
    }

    // Lista todos
    public List<Funcionario> findAll() {
        List<Funcionario> listDB = repository.findAll();
        List<Funcionario> list = new ArrayList<>();
        for (Funcionario c : listDB) {
            if (c.getDataDemissao() == null) {
                list.add(c);
            }
        }
        return list;
    }

    // Cadastrar funcionario
    public Funcionario create(@Valid FuncionarioDTO fDTO) {
        fDTO.setId(null);
        validaCpf(fDTO);
        Funcionario f = valida(fDTO);
        return repository.save(f);
    }

    // Atualizar funcionario
    public Funcionario update(Integer id, @Valid FuncionarioDTO fDTO) {
        fDTO.setId(id);
        Funcionario f = findById(id);
        f = valida(fDTO);
        return repository.save(f);
    }

    // Remover funcionario: o mesmo caso do remover cliente
    public void delete(Integer id){
        Funcionario f = findById(id);
        f.setDataDemissao(LocalDate.now());
        repository.save(f);
    }

    // Validações
    public Funcionario valida(FuncionarioDTO fDTO) {
        Endereco e = new Endereco();
        List<Contato> contatos = new ArrayList<>();
        Funcionario f = new Funcionario(fDTO);
        Set<Integer> na = fDTO.getNivelAuth();
        
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
        f.setCpf(fDTO.getCpf());
        f.setEndereco(e);
        f.setNome(fDTO.getNome());
        f.setNomeUsuario(fDTO.getNomeUsuario());
        f.setDataDemissao(fDTO.getDataDemissao());
        f.setSenha(fDTO.getSenha());
        f.setAuth(na);

        return f;
    }

    public void validaCpf(FuncionarioDTO f) {
        Optional<Funcionario> obj = repository.findByCpf(f.getCpf());
        if (obj.isPresent() && obj.get().getCpf() != f.getCpf()) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }
    }
}