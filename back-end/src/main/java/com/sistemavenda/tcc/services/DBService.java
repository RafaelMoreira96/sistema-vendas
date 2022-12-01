package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Compra;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.FormaPagamento;
import com.sistemavenda.tcc.domain.Fornecedor;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.ItemCompra;
import com.sistemavenda.tcc.domain.ItemVenda;
import com.sistemavenda.tcc.domain.Produto;
import com.sistemavenda.tcc.domain.Venda;
import com.sistemavenda.tcc.domain.enums.NivelAuth;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.CompraRepository;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;
import com.sistemavenda.tcc.repositories.FormaPagamentoRepository;
import com.sistemavenda.tcc.repositories.FornecedorRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.repositories.ItemCompraRepository;
import com.sistemavenda.tcc.repositories.ItemVendaRepository;
import com.sistemavenda.tcc.repositories.ProdutoRepository;
import com.sistemavenda.tcc.repositories.VendaRepository;

@Service
public class DBService {

        @Autowired
        private BCryptPasswordEncoder encoder;
        @Autowired
        private EnderecoRepository enderecoRepository;
        @Autowired
        private ContatoRepository contatoRepository;
        @Autowired
        private FuncionarioRepository funcionarioRepository;


        public void instanciaDB() {

                // Funcionario
                Endereco e = new Endereco(null, "00000-000", "Admin", "0000", "Admin", "Admin", "Admin", "Admin");
                Contato c = new Contato(null, "67900000000", "Celular");
                List<Contato> contato = new ArrayList<Contato>();
                contato.add(c);
                Funcionario f = new Funcionario(null, null, "778.008.780-82", "ADMIN", e, contato, "admin",
                                encoder.encode("1234"));
                f.setAuth(NivelAuth.ADMIN);
                enderecoRepository.save(e);
                contatoRepository.saveAll(contato);
                funcionarioRepository.save(f);
                contato.removeAll(contato);
        }
}