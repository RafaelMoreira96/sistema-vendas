package com.sistemavenda.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.FormaPagamento;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.Produto;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.FormaPagamentoRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.repositories.ProdutoRepository;

@Service
public class DBService {

        @Autowired
        private BCryptPasswordEncoder encoder;
        @Autowired
        private FuncionarioRepository funcionarioRepository;
        @Autowired
        private FormaPagamentoRepository formaPagamentoRepository;
        @Autowired
        private ClienteRepository clienteRepository;
        @Autowired
        private ProdutoRepository produtoRepository;

        public void instanciaDB() {
                // Funcionario
                Funcionario funcionario = new Funcionario();
                funcionario.setNome("administrator");
                funcionario.setNomeUsuario("administrator");
                funcionario.setSenha(encoder.encode("1234"));
                funcionarioRepository.save(funcionario);

                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setDescricao("Cartão de Débito");
                formaPagamentoRepository.save(formaPagamento);

                Cliente cliente = new Cliente();
                cliente.setNome("Marcos");
                cliente.setCpf("225.475.190-54");
                clienteRepository.save(cliente);

                Produto produto = new Produto();
                produto.setCodBarras("123");
                produto.setDescricao("8bitdo");
                produto.setPrecoAtacado(125.5);
                produto.setPrecoVarejo(250);
                produtoRepository.save(produto);
        }
}