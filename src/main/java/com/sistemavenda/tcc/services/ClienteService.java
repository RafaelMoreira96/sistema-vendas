package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.dtos.ClienteDTO;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;

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
        return o.orElseThrow(() -> new ObjectNotFoundException(id, "Cliente não encontrado!"));
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
        cDTO.setId(null);
        validaCpf(cDTO);
        Cliente c = valida(cDTO);
        return repository.save(c);
    }

    // Atualizar cliente
    public Cliente update(Integer id, @Valid ClienteDTO cDTO) {
        cDTO.setId(id);
        Cliente c = findById(id);
        if (cDTO.getCpf().equals(c.getCpf())) {
            cDTO.getEndereco().setId(c.getEndereco().getId());
            c = valida(cDTO);
        } else {
            validaCpf(cDTO);
            cDTO.getEndereco().setId(c.getEndereco().getId());
            c = valida(cDTO);
        }

        return repository.save(c);
    }

    /*
     * "Remover" cliente: aqui não pode ser deletado um cliente, ele deve ser
     * "desativado", através do atributo
     */
    public void delete(Integer id) {
        Cliente c = findById(id);
        c.setStatus(false);
        repository.save(c);
    }

    // Validações
    public Cliente valida(ClienteDTO cDTO) {
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

        Cliente c = new Cliente();

        if (cDTO.getId() != null) {
            c.setId(cDTO.getId());
        }
        c.setCpf(cDTO.getCpf());
        c.setEndereco(e);
        c.setContatos(contatos);
        c.setNome(cDTO.getNome());
        return c;
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