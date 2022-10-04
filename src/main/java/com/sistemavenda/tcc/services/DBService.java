package com.sistemavenda.tcc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        @Autowired
        private ItemCompraRepository itemCompraRepository;
        @Autowired
        private CompraRepository compraRepository;

        public void instanciaDB() {

                // Cliente 1
                Endereco e = new Endereco(null, "79640-450", "Rua dos patetas", "1234", null, "Aquele lá",
                                "Três Lagoas",
                                "Mato Grosso do Sul");

                Contato c = new Contato(null, "67991784885", "Celular");
                List<Contato> contato = new ArrayList<>();
                contato.add(c);
                c = new Contato(null, "67991784884", "Residencial");
                contato.add(c);
                Cliente cli = new Cliente(null, null, "828.178.770-80", "Rafael Moreira Simão", e, contato);

                enderecoRepository.save(e);
                contatoRepository.saveAll(contato);
                clienteRepository.save(cli);

                contato.removeAll(contato);

                // Cliente 2
                e = new Endereco(null, "45682-450", "Rua Atílio Jorge Salum", "1896", "De frente ao parquinho",
                                "Bairro dos Fincos",
                                "São Bernardo do Campo",
                                "São Paulo");

                c = new Contato(null, "1141019148", "Telefone");
                contato.add(c);
                c = new Contato(null, "1143545758", "Telefone");
                contato.add(c);
                c = new Contato(null, "11984773029", "Telefone");
                contato.add(c);
                cli = new Cliente(null, null, "307.378.640-60", "Marcelo Moraes Motta", e, contato);

                enderecoRepository.save(e);
                contatoRepository.saveAll(contato);
                clienteRepository.save(cli);

                contato.removeAll(contato);
                // Cliente 3
                e = new Endereco(null, "45682-450", "Rua Remelenta", "124", "Ao lado da escola Baptista",
                                "Jd. Amélia",
                                "Sumaré",
                                "São Paulo");

                c = new Contato(null, "1941019148", "Telefone");
                contato.add(c);
                c = new Contato(null, "1943545758", "Telefone");
                contato.add(c);
                c = new Contato(null, "19984773029", "Celular");
                contato.add(c);
                cli = new Cliente(null, null, "712.179.650-33", "Vania Pascchin", e, contato);

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
                f.setAuth(NivelAuth.ADMIN);
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
                formaPagamentoRepository.save(formaPagamento);

                formaPagamento = formaPagamentoRepository.getOne(2);

                // Item da Venda 1
                ItemVenda itemVenda = new ItemVenda(null, produto1.getDescricao(), produto1.getCodBarras(),
                                produto1.getPrecoVarejo(), 2);
                List<ItemVenda> itens = new ArrayList<>();
                itens.add(itemVenda);
                itemVenda = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 1);
                itens.add(itemVenda);
                itemVenda = new ItemVenda(null, produto3.getDescricao(), produto3.getCodBarras(),
                                produto3.getPrecoVarejo(), 1);
                itens.add(itemVenda);
                itemVenda = new ItemVenda(null, produto4.getDescricao(), produto4.getCodBarras(),
                                produto4.getPrecoVarejo(), 1);
                itens.add(itemVenda);
                itemVendaRepository.saveAll(itens);

                // Venda 1 - Resultado
                Venda venda = new Venda(null, 1, cli, f, itens, formaPagamento);
                venda.setValorVenda(itens);
                vendaRepository.save(venda);

                // Itens da venda 2
                ItemVenda itemVenda2 = new ItemVenda(null, produto1.getDescricao(), produto1.getCodBarras(),
                                produto1.getPrecoVarejo(), 3);
                List<ItemVenda> itens2 = new ArrayList<>();
                itens2.add(itemVenda2);
                itemVenda2 = new ItemVenda(null, produto2.getDescricao(), produto2.getCodBarras(),
                                produto2.getPrecoVarejo(), 2);
                itens2.add(itemVenda2);
                itemVendaRepository.saveAll(itens2);

                // Venda 2 - Resultado
                Venda venda2 = new Venda(null, 2, cli, f, itens2, formaPagamento);
                venda2.setValorVenda(itens2);
                vendaRepository.save(venda2);

                // Itens da compra
                List<ItemCompra> itemList = new ArrayList<>();
                double valor = produto4.getPrecoAtacado();
                ItemCompra itemCompra = new ItemCompra(null, produto4.getDescricao(), produto4.getCodBarras(), valor,
                                3);
                itemList.add(itemCompra);

                valor = produto3.getPrecoAtacado();
                itemCompra = new ItemCompra(null, produto3.getDescricao(), produto3.getCodBarras(), valor, 3);
                itemList.add(itemCompra);
                itemCompraRepository.saveAll(itemList);

                // Compra 1 - Resultado
                Compra compra = new Compra(null, fornecedor, f, valor, itemList);
                compra.setValorTotal(itemList);
                compraRepository.save(compra);
        }
}