import { Component, OnInit } from '@angular/core';
import { Compra } from 'src/app/models/compra';
import { Venda } from 'src/app/models/venda';
import { CompraService } from 'src/app/services/compra.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-financeiro',
  templateUrl: './financeiro.component.html',
  styleUrls: ['./financeiro.component.css'],
})
export class FinanceiroComponent implements OnInit {
  venda: Venda[] = [];
  compra: Compra[] = [];
  public totalVenda: number = 0.0;
  public totalCompra: number = 0.0;
  public dataInicial: string = '';
  public dataFinal: string = '';

  constructor(
    private vendaService: VendaService,
    private compraService: CompraService
  ) {}

  ngOnInit(): void {
    this.findAllCompras();
    this.findAllVendas();
  }

  findAllVendas(): void {
    this.vendaService.findAll().subscribe((resposta) => {
      this.venda = resposta;
      this.totalVenda = this.calcularTotal(this.venda);
    });
  }

  findAllCompras(): void {
    this.compraService.findAll().subscribe((resposta) => {
      this.compra = resposta;
      this.totalCompra = this.calcularTotal(this.compra);
    });
  }

  calcularTotal(transacoes: any[]): number {
    return transacoes.reduce((total, transacao) => total + transacao.valorVenda, 0);
  }

  filtrarPorData(): void {
    if (!this.dataInicial || !this.dataFinal) {
      return; // Verificar se as datas foram fornecidas
    }

    const dataInicialTimestamp = new Date(this.dataInicial).toISOString().split('T')[0];
    const dataFinalTimestamp = new Date(this.dataFinal).toISOString().split('T')[0];

    // Filtrar vendas por data
    this.vendaService.findAll().subscribe((resposta) => {
      this.venda = resposta.filter((venda) => {
        const dataVendaTimestamp = new Date(venda.dataVenda).toISOString().split('T')[0];
        return dataVendaTimestamp >= dataInicialTimestamp && dataVendaTimestamp <= dataFinalTimestamp;
      });
      this.totalVenda = this.calcularTotal(this.venda);
    });

    // Filtrar compras por data
    this.compraService.findAll().subscribe((resposta) => {
      this.compra = resposta.filter((compra) => {
        const dataVendaTimestamp = new Date(compra.dataVenda).toISOString().split('T')[0];
        return dataVendaTimestamp >= dataInicialTimestamp && dataVendaTimestamp <= dataFinalTimestamp;
      });
      this.totalCompra = this.calcularTotal(this.compra);
    });
  }
}
