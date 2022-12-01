import { Router } from '@angular/router';
import { Funcionario } from './../../models/funcionario';
import { FormaPagamento } from './../../models/formapagamento';
import { FuncionarioService } from './../../services/funcionario.service';
import { Component, OnInit } from '@angular/core';
import { Form, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from 'src/app/services/cliente.service';
import { FormaPagamentoService } from 'src/app/services/formapagamento.service';
import { Venda } from 'src/app/models/venda';
import { ProdutoService } from 'src/app/services/produto.service';
import { ItemVenda } from 'src/app/models/itemvenda';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-pdv',
  templateUrl: './pdv.component.html',
  styleUrls: ['./pdv.component.css'],
})
export class PdvComponent implements OnInit {
  listItem: ItemVenda[] = [];

  // Variáveis da sessão Lista de Produtos
  itemVenda: ItemVenda = {
    idProduto: undefined,
    descricao: '',
    codBarras: '',
    precoVendido: 0,
    quant: 0,
  };

  venda: Venda = {
    id: '',
    cliente: 0,
    nomeCliente: '',
    funcionario: 0,
    nomeFuncionario: '',
    itens: [],
    formaPagamento: {
      id: '',
      descricao: '',
    },
    status: '',
    dataVenda: '',
    valorVenda: 0.0
  };

  // Selects da sessão Info. da Venda
  listaFormaPagto: FormaPagamento[] = [];
  funcionarios: Funcionario[] = [];

  // Informações da Venda
  cpf: FormControl = new FormControl();
  cliente: FormControl = new FormControl();
  nomeCliente: FormControl = new FormControl();
  funcionario: FormControl = new FormControl();
  nomeFuncionario: FormControl = new FormControl();
  formaPagto: FormControl = new FormControl();

  // Item da Venda
  idProduto: FormControl = new FormControl();
  descricao: FormControl = new FormControl();
  valorVendido: FormControl = new FormControl();
  quant: FormControl = new FormControl();
  codigoBarrasItem?: any;

  // Lista de produtos
  codBarras: FormControl = new FormControl();

  // Valores
  totalGeral = 0.0;

  constructor(
    private clienteService: ClienteService,
    private funcionarioService: FuncionarioService,
    private formaPagamentoService: FormaPagamentoService,
    private produtoService: ProdutoService,
    private vendaService: VendaService,
    private toast: ToastrService
  ) {}

  ngOnInit(): void {
    this.findFormaPagamento();
    this.findFuncionario();
  }

  //Funções dos botões
  findCliente(): void {
    this.clienteService.findAll().subscribe((resposta) => {
      var x = 0;
      resposta.forEach(() => {
        resposta.forEach((element) => {
          if (element.cpf == this.cpf.value) {
            x++;
            this.cliente.setValue(element.id);
            this.nomeCliente.setValue(element.nome);
          }
        });
      });
      if (x == 0) {
        this.toast.error('Cliente não encontrado!');
      }
    });
  }

  findProduto(): void {
    this.produtoService.findByCodBarras(this.codBarras.value).subscribe(
      (resposta) => {
        this.idProduto.setValue(resposta.id);
        this.descricao.setValue(resposta.descricao);
        this.valorVendido.setValue(resposta.precoVarejo);
        this.quant.setValue(1);
        this.codigoBarrasItem = resposta.codBarras;
      },
      (ex) => {
        this.toast.error('Produto não encontrado.');
      }
    );
    this.codBarras.setValue('');
  }

  finalizarVenda(): void {
    this.venda.cliente = this.cliente.value;
    this.venda.nomeCliente = this.nomeCliente.value;
    this.funcionarioService
      .findById(this.funcionario.value)
      .subscribe((resposta) => {
        this.venda.funcionario = resposta.id;
        this.venda.nomeFuncionario = resposta.nome;
      });

    //    this.venda.itens = this.listItemVenda;
    this.formaPagamentoService
      .findById(this.formaPagto.value)
      .subscribe((resposta) => {
        this.venda.formaPagamento = resposta;
      });
    this.venda.status = 1;

    this.vendaService.create(this.venda).subscribe(
      (resposta) => {
        this.toast.info('Venda realizada com sucesso!');
        this.limpaCampos();
      },
      (ex) => {
        this.toast.error(ex);
      }
    );
    console.log(this.venda);
  }

  addProduto(): void {
    if (this.idProduto.value == 0) {
      this.toast.error('Pesquise um produto antes');
    } else {
      this.itemVenda.idProduto = this.idProduto.value;
      this.itemVenda.descricao = this.descricao.value;
      this.itemVenda.precoVendido = this.valorVendido.value;
      this.itemVenda.quant = this.quant.value;
      this.itemVenda.codBarras = this.codigoBarrasItem;

      this.venda.itens.push({ ...this.itemVenda });

      this.totalGeral =
        this.totalGeral + this.itemVenda.precoVendido * this.itemVenda.quant;
    }

    this.idProduto.setValue('');
    this.descricao.setValue('');
    this.valorVendido.setValue('');
    this.quant.setValue('');
    this.codigoBarrasItem = '';
  }

  // Funções que inicia automaticamente
  findFormaPagamento(): void {
    this.formaPagamentoService.findAll().subscribe((resposta) => {
      this.listaFormaPagto = resposta;
    });
  }

  findFuncionario(): void {
    this.funcionarioService.findAll().subscribe((resposta) => {
      this.funcionarios = resposta;
    });
  }

  limpaCampos(): void {
    // Informações da Venda
    this.cpf.setValue('');
    this.cliente.setValue('');
    this.nomeCliente.setValue('');
    this.funcionario.setValue('');
    this.nomeFuncionario.setValue('');
    this.formaPagto.setValue('');

    // Item da Venda
    this.idProduto.setValue('');
    this.descricao.setValue('');
    this.valorVendido.setValue('');
    this.quant.setValue('');

    // Lista de produtos
    this.codBarras.setValue('');

    // Valores
    this.totalGeral = 0.0;

    // Limpando o objeto Venda
    this.venda.cliente = 0;
    this.venda.nomeCliente = '';
    this.venda.funcionario = 0;
    this.venda.nomeFuncionario = '';
    for (let index = 0; index < this.venda.itens.length + 1; index++) {
      this.venda.itens.shift();
    }
    this.venda.formaPagamento.id = 0;
    this.venda.formaPagamento.descricao = '';
    this.venda.status = 0;
    this.venda.dataVenda = '';
  }
}
