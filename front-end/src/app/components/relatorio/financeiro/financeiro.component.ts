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
      resposta.forEach((element) => {
        this.totalVenda += element.valorVenda;
      });
    });
  }

  findAllCompras(): void {
    this.compraService.findAll().subscribe((resposta) => {
      this.compra = resposta;
      resposta.forEach((element) => {
        this.totalCompra += element.valorVenda;
      });
    });
  }
}
