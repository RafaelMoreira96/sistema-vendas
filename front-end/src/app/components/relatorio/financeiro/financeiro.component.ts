import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
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
    private toast: ToastrService,
    private vendaService: VendaService,
    private compraService: CompraService
  ) {}

  ngOnInit(): void {}

  calcularTotal(transacoes: any[]): number {
    return transacoes.reduce(
      (total, transacao) => total + transacao.valorVenda,
      0
    );
  }

  buscarVendasEntreDatas(dataInicial: string, dataFinal: string) {
    this.vendaService
      .getVendasEntreDatas(dataInicial, dataFinal)
      .subscribe((data) => {
        this.venda = data;
        this.totalVenda = this.calcularTotal(this.venda);
      });
  }

  filtrarPorData(): void {
    this.venda = [];
    if (!this.dataInicial || !this.dataFinal) {
      return; // Verificar se as datas foram fornecidas
    }

    const dataInicialTimestamp = new Date(this.dataInicial)
      .toISOString()
      .split('T')[0];
    const dataFinalTimestamp = new Date(this.dataFinal)
      .toISOString()
      .split('T')[0];

    // Filtrar vendas por data
    this.vendaService
      .getVendasEntreDatas(dataInicialTimestamp, dataFinalTimestamp)
      .subscribe(
        (resposta) => {
          this.venda = resposta;
          this.totalVenda = this.calcularTotal(this.venda);
          this.toast.success('Vendas encontradas com sucesso.');
        },
        (ex) => {
          this.venda = [];
          this.totalVenda = 0;
          this.toast.error(
            'Nenhuma venda encontrada para o período informado.'
          );
        }
      );

    // Filtrar compras por data
    this.compraService
      .getComprasEntreDatas(dataInicialTimestamp, dataFinalTimestamp)
      .subscribe(
        (resposta) => {
          this.compra = resposta;
          this.totalCompra = this.calcularTotal(this.compra);
          this.toast.success('Compras encontradas com sucesso.');
        },
        (ex) => {
          this.compra = [];
          this.totalCompra = 0;
          this.toast.error(
            'Nenhuma compra encontrada para o período informado.'
          );
        }
      );
  }
}
