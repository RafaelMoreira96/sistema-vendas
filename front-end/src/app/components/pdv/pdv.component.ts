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
  // Variáveis da sessão Lista de Produtos
  itemVenda: ItemVenda = {
    idProduto: 0,
    descricao: '',
    codBarras: '',
    precoVendido: 0,
    quant: 0,
  };

  listItemVenda: ItemVenda[] = [];

  formaPagamento: FormaPagamento = {
    id: 0,
    descricao: '',
  };
  venda: Venda = {
    id: '',
    cliente: 0,
    nomeCliente: '',
    funcionario: 0,
    nomeFuncionario: '',
    itens: [],
    formaPagamento: this.formaPagamento.id,
    status: undefined,
    dataVenda: '',
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
    private router: Router,
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
        this.router.navigate(['venda']);
      }
    );
    this.codBarras.setValue('');
  }

  finalizarVenda(): void {
    this.venda.cliente = this.cliente.value;
    this.venda.funcionario = parseInt(this.funcionario.value);
    this.venda.nomeCliente = this.nomeCliente.value;
    this.venda.nomeFuncionario = this.nomeFuncionario.value;
    this.venda.itens = this.listItemVenda;
    this.venda.formaPagamento = this.formaPagto.value;

    this.vendaService.create(this.venda).subscribe(
      (resposta) => {
        this.toast.info('Venda realizada com sucesso!');
        this.router.navigate(['vendas']);
      },
      (ex) => {
        this.toast.error(ex);
      }
    );
    console.log(this.venda);
  }

  addProduto(): void {
    if (this.idProduto.value == 0 || this.descricao.value == null) {
      this.toast.error('Pesquise um produto antes');
    } else {
      this.itemVenda.idProduto = this.idProduto.value;
      this.itemVenda.descricao = this.descricao.value;
      this.itemVenda.precoVendido = this.valorVendido.value;
      this.itemVenda.quant = this.quant.value;
      this.itemVenda.codBarras = this.codigoBarrasItem;

      this.listItemVenda.push(this.itemVenda);
      this.listItemVenda.values;
      this.totalGeral =
        this.totalGeral + this.itemVenda.precoVendido * this.itemVenda.quant;

      this.idProduto.setValue('');
      this.descricao.setValue('');
      this.valorVendido.setValue('');
      this.quant.setValue('');
      console.log(this.listItemVenda);
      console.log(this.totalGeral);
    }
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
}
