package com.sistemavenda.tcc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepository repository;

    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Optional<Funcionario> user = repository.findByNomeUsuario(nomeUsuario);
        if (user.isPresent()) {
            if(user.get().getDataDemissao() == null){
                return new UserSS(user.get().getId(), user.get().getNomeUsuario(), user.get().getSenha(),
                user.get().getNivelAuth());
            }
        }
        throw new UsernameNotFoundException(nomeUsuario);
    }
}