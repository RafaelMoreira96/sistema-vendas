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

@Component({
  selector: 'app-pdv',
  templateUrl: './pdv.component.html',
  styleUrls: ['./pdv.component.css'],
})
export class PdvComponent implements OnInit {
  // Variáveis da sessão Lista de Produtos
  itemVenda: ItemVenda = {
    idProduto: undefined,
    descricao: '',
    codBarras: '',
    precoVendido: 0,
    quant: 0
  }

  formaPagamento: FormaPagamento = {
    id: undefined,
    descricao: ''
  }
  venda: Venda = {
    id: undefined,
    numeroVenda: 0,
    idCliente: 0,
    nomeCliente: '',
    idFuncionario: 0,
    nomeFuncionario: '',
    itens: [],
    formaPagamento: this.formaPagamento,
    status: undefined,
    dataVenda: ''
  }

  // Selects da sessão Info. da Venda
  listaFormaPagto: FormaPagamento[] = [];
  funcionarios: Funcionario[] = [];

  // Informações da Venda
  cpf: FormControl = new FormControl();
  idCliente: FormControl = new FormControl();
  nomeCliente: FormControl = new FormControl();
  idFuncionario: FormControl = new FormControl();
  nomeFuncionario: FormControl = new FormControl();
  formaPagto: FormControl = new FormControl();

  // Item da Venda
  idProduto: FormControl = new FormControl();
  descricao: FormControl = new FormControl();
  valorVendido: FormControl = new FormControl();
  quant: FormControl = new FormControl();

  // Lista de produtos
  codBarras: FormControl = new FormControl();

  constructor(
    private clienteService: ClienteService,
    private funcionarioService: FuncionarioService,
    private formaPagamentoService: FormaPagamentoService,
    private produtoService: ProdutoService,
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
            this.idCliente.setValue(element.id);
            this.nomeCliente.setValue(element.nome);
          }
        });
      });
      if (x == 0) {
        this.toast.error('Cliente não encontrado!');
      }
    });
  }

  finalizarVenda(): void{

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

  findProduto(): void {
    this.produtoService.findByCodBarras(this.codBarras.value).subscribe((resposta) => {
      this.idProduto.setValue(resposta.id);
      this.descricao.setValue(resposta.descricao);
      this.valorVendido.setValue(resposta.precoVarejo);
    })
  }
}
