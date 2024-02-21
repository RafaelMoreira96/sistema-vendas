import { CompraService } from './../../services/compra.service';
import { Funcionario } from '../../models/funcionario';
import { FuncionarioService } from '../../services/funcionario.service';
import { Component, OnInit } from '@angular/core';
import { Form, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { FormaPagamentoService } from 'src/app/services/formapagamento.service';
import { ProdutoService } from 'src/app/services/produto.service';
import { ItemCompra } from 'src/app/models/itemcompra';
import { Compra } from 'src/app/models/compra';
import { FornecedorService } from 'src/app/services/fornecedor.service';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css'],
})
export class CompraComponent implements OnInit {
  showModal: boolean = false;
  listItem: ItemCompra[] = [];

  // Variáveis da sessão Lista de Produtos
  itemCompra: ItemCompra = {
    idItemCompra: 0,
    idProduto: undefined,
    descricao: '',
    codBarras: '',
    precoCompra: 0,
    quant: 0,
  };

  compra: Compra = {
    id: '',
    fornecedor: 0,
    nomeFornecedor: '',
    funcionario: 0,
    nomeFuncionario: '',
    itens: [],
    status: '',
    valorVenda: 0,
    dataVenda: '',
  };

  // Selects da sessão Info. da Venda
  funcionarios: Funcionario[] = [];

  // Informações da Venda
  cnpj: FormControl = new FormControl();
  fornecedor: FormControl = new FormControl();
  nomeFornecedor: FormControl = new FormControl();
  funcionario: FormControl = new FormControl();
  nomeFuncionario: FormControl = new FormControl();

  // Item da Compra
  idProduto: FormControl = new FormControl();
  descricao: FormControl = new FormControl();
  valorCompra: FormControl = new FormControl();
  quant: FormControl = new FormControl();
  codigoBarrasItem?: any;

  // Lista de produtos
  codBarras: FormControl = new FormControl();

  // Valores
  totalGeral = 0.0;

  constructor(
    private fornecedorService: FornecedorService,
    private funcionarioService: FuncionarioService,
    private formaPagamentoService: FormaPagamentoService,
    private produtoService: ProdutoService,
    private compraService: CompraService,
    private toast: ToastrService
  ) {}

  ngOnInit(): void {
    this.findFuncionario();
  }

  //Funções dos botões
  findFornecedor(): void {
    this.fornecedorService.findAll().subscribe((resposta) => {
      var x = 0;
      resposta.forEach(() => {
        resposta.forEach((element) => {
          if (element.cnpj == this.cnpj.value) {
            x++;
            this.fornecedor.setValue(element.id);
            this.nomeFornecedor.setValue(element.nome);
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
        this.valorCompra.setValue(resposta.precoAtacado);
        this.quant.setValue(1);
        this.codigoBarrasItem = resposta.codBarras;
      },
      (ex) => {
        this.toast.error('Produto não encontrado.');
      }
    );
    this.codBarras.setValue('');
  }

  finalizarCompra(): void {
    this.compra.fornecedor = this.fornecedor.value;
    this.compra.nomeFornecedor = this.nomeFornecedor.value;
    this.funcionarioService
      .findById(this.funcionario.value)
      .subscribe((resposta) => {
        this.compra.funcionario = resposta.id;
        this.compra.nomeFuncionario = resposta.nome;
      });

    this.compra.status = 1;

    this.compraService.create(this.compra).subscribe(
      (resposta) => {
        this.toast.info('Entrada no estoque realizada com sucesso!');
        this.limpaCampos();
      },
      (ex) => {
        this.toast.error(ex);
      }
    );
    console.log(this.compra);
  }

  addProduto(): void {
    if (
      this.idProduto.value == undefined ||
      this.idProduto.value == null ||
      this.idProduto.value == 0
    ) {
      this.toast.error('Pesquise um produto antes');
      return;
    } else {
      this.itemCompra.idItemCompra = this.itemCompra.idItemCompra + 1;
      this.itemCompra.idProduto = this.idProduto.value;
      this.itemCompra.descricao = this.descricao.value;
      this.itemCompra.precoCompra = this.valorCompra.value;
      this.itemCompra.quant = this.quant.value;
      this.itemCompra.codBarras = this.codigoBarrasItem;

      this.compra.itens.push({ ...this.itemCompra });

      this.totalGeral =
        this.totalGeral + this.itemCompra.precoCompra * this.itemCompra.quant;
    }

    this.idProduto.setValue('');
    this.descricao.setValue('');
    this.valorCompra.setValue('');
    this.quant.setValue('');
    this.codigoBarrasItem = '';
  }

  // Funções que inicia automaticamente
  findFuncionario(): void {
    this.funcionarioService.findAll().subscribe((resposta) => {
      this.funcionarios = resposta;
    });
  }

  limpaCampos(): void {
    // Informações da Venda
    this.cnpj.setValue('');
    this.fornecedor.setValue('');
    this.nomeFornecedor.setValue('');
    this.funcionario.setValue('');
    this.nomeFuncionario.setValue('');

    // Item da Venda
    this.idProduto.setValue('');
    this.descricao.setValue('');
    this.valorCompra.setValue('');
    this.quant.setValue('');

    // Lista de produtos
    this.codBarras.setValue('');

    // Valores
    this.totalGeral = 0.0;

    // Limpando o objeto Venda
    this.compra.fornecedor = 0;
    this.compra.nomeFornecedor = '';
    this.compra.funcionario = 0;
    this.compra.nomeFuncionario = '';
    this.compra.itens = [];
    this.compra.status = 0;
    this.compra.dataVenda = '';
  }

  checkQuantity(preco: number, quantidade: number): number {
    return parseFloat((preco * quantidade).toFixed(2));
  }

  removerItemCompra(item: ItemCompra): void {
    const index = this.compra.itens.findIndex(
      (i) => i.idItemCompra === item.idItemCompra
    );
    if (index !== -1) {
      this.totalGeral -= this.checkQuantity(item.precoCompra, item.quant);
      this.compra.itens.splice(index, 1);
    }
  }

  abrirTelaConfirmacao() {
    if (this.compra.itens.length == 0) {
      this.toast.error('Adicione um produto antes de finalizar a compra!');
      return;
    }
    if (this.fornecedor.value == null){
      this.toast.error('Selecione um fornecedor!');
      return;
    }
    if (this.funcionario.value == null){
      this.toast.error('Selecione um funcionário!');
      return;
    }

    this.showModal = true;
  }

  fecharTelaConfirmacao() {
    this.showModal = false;
  }

  reloadPDV() {
    location.reload();
  }
}
