package com.sistemavenda.tcc.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.dtos.FuncionarioDTO;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.services.exceptions.DataIntegrityViolationException;
import com.sistemavenda.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ContatoRepository contatoRepository;

    // Busca por ID
    public Funcionario findById(Integer id) {
        Optional<Funcionario> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException("Funcionário não encontrado! ID: " + id));
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
        validaCpf(fDTO);

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
        Funcionario f = new Funcionario();
        f.setNome(fDTO.getNome());
        f.setCpf(fDTO.getCpf());
        f.setEndereco(e);
        f.setContatos(contatos);
        f.setNomeUsuario(fDTO.getNomeUsuario());
        f.setSenha(encoder.encode(fDTO.getSenha()));

        return repository.save(f);
    }

    // Atualizar funcionario
    public Funcionario update(Integer id, @Valid FuncionarioDTO fDTO) {
        fDTO.setId(id);
        Funcionario funDatabase = findById(id);
        if (fDTO.getCpf().equals(funDatabase.getCpf())) {
            fDTO.getEndereco().setId(funDatabase.getEndereco().getId());
        } else {
            validaCpf(fDTO);
            fDTO.getEndereco().setId(funDatabase.getEndereco().getId());
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
        Funcionario temp = findById(fDTO.getId());
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
        Funcionario f = new Funcionario();
        f.setId(id);
        f.setNome(fDTO.getNome());
        f.setCpf(fDTO.getCpf());
        f.setEndereco(e);
        f.setContatos(contatos);
        f.setNomeUsuario(fDTO.getNomeUsuario());
        f.setSenha(encoder.encode(fDTO.getSenha()));
        f.setDataDemissao(fDTO.getDataDemissao());

        return repository.save(f);
    }

    // Remover funcionario: o mesmo caso do remover cliente
    public void delete(Integer id) {
        Funcionario f = findById(id);
        f.setDataDemissao(LocalDate.now());
        repository.save(f);
    }

    public void validaCpf(FuncionarioDTO f) {
        Optional<Funcionario> obj = repository.findByCpf(f.getCpf());
        if (obj.isPresent() && obj.get().getCpf() != f.getCpf()) {
            throw new DataIntegrityViolationException("CPF já cadastrado");
        }
    }
}