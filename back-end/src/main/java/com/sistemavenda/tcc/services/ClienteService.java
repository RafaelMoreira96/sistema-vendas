package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.dtos.ClienteDTO;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;
import com.sistemavenda.tcc.services.exceptions.DataIntegrityViolationException;
import com.sistemavenda.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ContatoRepository contatoRepository;

    // Busca por ID
    public Cliente findById(Integer id) {
        Optional<Cliente> o = repository.findById(id);
        return o.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));
    }

    // Busca por CPF
    public Cliente findByCpf(String cpf) {
        Optional<Cliente> o = repository.findByCpf(cpf);
        return o.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
    }

    // Lista todos
    public List<Cliente> findAll() {
        List<Cliente> listDB = repository.findAll();
        List<Cliente> list = new ArrayList<>();
        for (Cliente c : listDB) {
            if (c.getStatus() == true) {
                list.add(c);
            }
        }
        return list;
    }

    // Cadastrar cliente
    public Cliente create(@Valid ClienteDTO cDTO) {
        validaCpf(cDTO);

        // Preparando objeto Endereço e persistindo
        Endereco e = new Endereco();
        e.setCep(cDTO.getEndereco().getCep());
        e.setNumero(cDTO.getEndereco().getNumero());
        e.setComplemento(cDTO.getEndereco().getComplemento());
        e.setBairro(cDTO.getEndereco().getBairro());
        e.setCidade(cDTO.getEndereco().getCidade());
        e.setEstado(cDTO.getEndereco().getEstado());
        e.setLogradouro(cDTO.getEndereco().getLogradouro());

        enderecoRepository.save(e);

        // Preparando objeto Contato e persistindo
        List<Contato> contatos = new ArrayList<>();
        for (Contato contato : cDTO.getContatos()) {
            contato.setId(null);
            contatos.add(contato);
        }
        cDTO.setContatos(contatos);

        contatoRepository.saveAll(contatos);

        // Preparando objeto Cliente para persistencia
        Cliente c = new Cliente();
        c.setId(null);
        c.setCpf(cDTO.getCpf());
        c.setEndereco(e);
        c.setContatos(contatos);
        c.setNome(cDTO.getNome());

        return repository.save(c);
    }

    // Atualizar cliente
    public Cliente update(Integer id, @Valid ClienteDTO cDTO) {
        cDTO.setId(id);
        Cliente cliDatabase = findById(id);
        if (cDTO.getCpf().equals(cliDatabase.getCpf())) {
            cDTO.getEndereco().setId(cliDatabase.getEndereco().getId());
        } else {
            validaCpf(cDTO);
            cDTO.getEndereco().setId(cliDatabase.getEndereco().getId());
        }
        // Preparando objeto Endereço e persistindo
        Endereco e = new Endereco();

        e.setId(cDTO.getEndereco().getId());
        e.setCep(cDTO.getEndereco().getCep());
        e.setNumero(cDTO.getEndereco().getNumero());
        e.setComplemento(cDTO.getEndereco().getComplemento());
        e.setBairro(cDTO.getEndereco().getBairro());
        e.setCidade(cDTO.getEndereco().getCidade());
        e.setEstado(cDTO.getEndereco().getEstado());
        e.setLogradouro(cDTO.getEndereco().getLogradouro());

        enderecoRepository.save(e);

        // Preparando objeto Contato e persistindo
        Cliente temp = findById(cDTO.getId());
        List<Contato> contatos = new ArrayList<>();
        for (Contato contato : cDTO.getContatos()) {
            contato.setId(null);
            contatos.add(contato);
        }
        cDTO.setContatos(contatos);
        for (int i = 0; i < temp.getContatos().size(); i++) {
            Contato contato = temp.getContatos().get(i);
            contatos.get(i).setId(contato.getId());
        }
        contatoRepository.saveAll(contatos);

        // Preparando objeto Cliente para persistencia
        Cliente c = new Cliente();
        c.setId(id);
        c.setCpf(cDTO.getCpf());
        c.setEndereco(e);
        c.setContatos(contatos);
        c.setNome(cDTO.getNome());
        return repository.save(c);
    }

    /*
     * "Remover" cliente: aqui não pode ser deletado um cliente, ele deve ser
     * "desativado", através do atributo
     */
    public void delete(Integer id) {
        Cliente c = findById(id);
        // c.setStatus(false);
        // repository.save(c);
        repository.delete(c);
    }

    public void validaCpf(ClienteDTO c) {
        // Verificação de todos os objetos que contém o mesmo CPF (que é pra encontrar
        // apenas um)
        Optional<Cliente> obj = repository.findByCpf(c.getCpf());
        if (obj.isPresent() && obj.get().getCpf() != c.getCpf()) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }
    }
}