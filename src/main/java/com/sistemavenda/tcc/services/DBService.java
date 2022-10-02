package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemavenda.tcc.domain.Cliente;
import com.sistemavenda.tcc.domain.Contato;
import com.sistemavenda.tcc.domain.Endereco;
import com.sistemavenda.tcc.domain.FormaPagamento;
import com.sistemavenda.tcc.domain.Fornecedor;
import com.sistemavenda.tcc.domain.Funcionario;
import com.sistemavenda.tcc.domain.ItemVenda;
import com.sistemavenda.tcc.domain.Produto;
import com.sistemavenda.tcc.domain.Venda;
import com.sistemavenda.tcc.domain.enums.StatusVenda;
import com.sistemavenda.tcc.repositories.ClienteRepository;
import com.sistemavenda.tcc.repositories.ContatoRepository;
import com.sistemavenda.tcc.repositories.EnderecoRepository;
import com.sistemavenda.tcc.repositories.FormaPagamentoRepository;
import com.sistemavenda.tcc.repositories.FornecedorRepository;
import com.sistemavenda.tcc.repositories.FuncionarioRepository;
import com.sistemavenda.tcc.repositories.ItemVendaRepository;
import com.sistemavenda.tcc.repositories.ProdutoRepository;
import com.sistemavenda.tcc.repositories.VendaRepository;

@Service
public class DBService {

        @Autowired
        private ClienteRepository clienteRepository;
        @Autowired
        private EnderecoRepository enderecoRepository;
        @Autowired
        private ContatoRepository contatoRepository;
        @Autowired
        private FuncionarioRepository funcionarioRepository;
        @Autowired
        private FornecedorRepository fornecedorRepository;
        @Autowired
        private ProdutoRepository produtoRepository;
        @Autowired
        private VendaRepository vendaRepository;
        @Autowired
        private FormaPagamentoRepository formaPagamentoRepository;
        @Autowired
        private ItemVendaRepository itemVendaRepository;

        public void instanciaDB() {

                // Cliente
                Endereco e = new Endereco(null, "79640-450", "Rua dos patetas", "1234", null, "Aquele lá",
                                "Três Lagoas",
                                "Mato Grosso do Sul");

                Contato c = new Contato(null, "67991784885", "Celular");
                List<Contato> contato = new ArrayList<>();
                contato.add(c);
                c = new Contato(null, "67991784884", "Residencial");
                contato.add(c);
                Cliente cli = new Cliente(null, null, "828.178.770-80", "Rafael M", e, contato);

                enderecoRepository.save(e);
                contatoRepository.saveAll(contato);
                clienteRepository.save(cli);

                contato.removeAll(contato);

                // Funcionario
                e = new Endereco(null, "79640-451", "Rua dos donalds", "1234", null, "Aquele lá", "Três Lagoas",
                                "Mato Grosso do Sul");
                c = new Contato(null, "18985963274", "Celular");
                contato.add(c);
                c = new Contato(null, "189859635123", "Residential");
                contato.add(c);
                Funcionario f = new Funcionario(null, null, "778.008.780-82", "Xablau", e, contato, "xablau", "1234");

                enderecoRepository.save(e);
                contatoRepository.saveAll(contato);
                funcionarioRepository.save(f);

                contato.removeAll(contato);

                // Funcionario
                e = new Endereco(null, "79554-451", "Rua dos mickye", "1234", null, "Aquele lá", "Três Lagoas",
                                "Mato Grosso do Norte");
                c = new Contato(null, "11955554444", "Celular");
                contato.add(c);
                c = new Contato(null, "11988776655", "Residential");
                contato.add(c);
                Fornecedor fornecedor = new Fornecedor(null, "31.975.549/0001-25", null, "Marguerita LTDA", e, contato,
                                "Cervejaria Marguerita", null);
                enderecoRepository.save(e);
                contatoRepository.saveAll(contato);
                fornecedorRepository.save(fornecedor);

                // Produto
                Produto produto1 = new Produto(null, "TV 32 LG", "789445545487", 599.99, 1999.95, 5, 2, 5);
                Produto produto2 = new Produto(null, "Notebook Gamer Legion 5i", "789445623484", 2800.00, 6500, 5, 2,
                                5);
                Produto produto3 = new Produto(null, "Kit Teclado Mouse Sem Fio", "79813521498", 15.55, 40, 5, 2, 5);
                Produto produto4 = new Produto(null, "Controle 8bitDo Wired", "10563402", 100, 245.99, 10, 2, 15);
                produtoRepository.save(produto1);
                produtoRepository.save(produto2);
                produtoRepository.save(produto3);
                produtoRepository.save(produto4);

                // Venda
                FormaPagamento formaPagamento = new FormaPagamento(null, "Cartão Crédito");
                formaPagamentoRepository.save(formaPagamento);
                formaPagamento = new FormaPagamento(null, "Cartão Débito");
                formaPagamentoRepository.save(formaPagamento);
                formaPagamento = new FormaPagamento(null, "Cartão Dinheiro");

                formaPagamento = formaPagamentoRepository.getOne(2);

                ItemVenda itemVenda = new ItemVenda(null, produto1.getDescricao(), produto1.getCodBarras(),
                                produto1.getPrecoVarejo(), 3);
                List<ItemVenda> itens = new ArrayList<>();
                itens.add(itemVenda);
                itemVenda = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens.add(itemVenda);
                itemVenda = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens.add(itemVenda);
                itemVenda = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens.add(itemVenda);
                itemVenda = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens.add(itemVenda);
                itemVendaRepository.saveAll(itens);

                ItemVenda itemVenda2 = new ItemVenda(null, produto1.getDescricao(), produto1.getCodBarras(),
                                produto1.getPrecoVarejo(), 3);
                List<ItemVenda> itens2 = new ArrayList<>();
                itens2.add(itemVenda2);
                itemVenda2 = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens2.add(itemVenda2);
                itemVenda2 = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens2.add(itemVenda2);
                itemVenda2 = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens2.add(itemVenda2);
                itemVenda2 = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens2.add(itemVenda2);
                itemVendaRepository.saveAll(itens2);

                Venda venda = new Venda(null, 1, StatusVenda.ANDAMENTO, cli, f, itens, formaPagamento);
                vendaRepository.save(venda);

                Venda venda2 = new Venda(null, 2, StatusVenda.ANDAMENTO, cli, f, itens2, formaPagamento);
                vendaRepository.save(venda2);
        }
}