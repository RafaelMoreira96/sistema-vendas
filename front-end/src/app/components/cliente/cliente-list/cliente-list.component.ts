import { FormControl } from '@angular/forms';
import { ClienteService } from './../../../services/cliente.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { Contato } from 'src/app/models/contato';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css'],
})
export class ClienteListComponent implements OnInit {
  ELEMENT_DATA: Cliente[] = [];

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'contato', 'acoes'];

  constructor(private service: ClienteService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe((resp) => {
      this.ELEMENT_DATA = resp;
      console.log(this.ELEMENT_DATA);
    });
  }
}
