import { Component, OnInit } from '@angular/core';
import { Venda } from 'src/app/models/venda';
import { ClienteService } from 'src/app/services/cliente.service';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  quantVendas: any;
  quantClientes: any;
  valorFaturado: any;
  valorInvestido: any;

  constructor(
    private vendaService: VendaService,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    this.findAllVendas();
    this.findAllCliente();
  }

  findAllVendas(): void {
    this.vendaService.findAll().subscribe((resposta) => {
      this.quantVendas = resposta.length;
    });
  }

  findAllCliente(): void {
    this.clienteService.findAll().subscribe((resposta) => {
      this.quantClientes = resposta.length;
    });
  }
}
