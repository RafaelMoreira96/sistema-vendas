import { FornecedorService } from '../../../services/fornecedor.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Fornecedor } from 'src/app/models/fornecedor';

@Component({
  selector: 'app-fornecedor-list',
  templateUrl: './fornecedor-list.component.html',
  styleUrls: ['./fornecedor-list.component.css'],
})
export class FornecedorListComponent implements OnInit {
  ELEMENT_DATA: Fornecedor[] = [];

  constructor(private service: FornecedorService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe((resp) => {
      this.ELEMENT_DATA = resp;
    });
  }
}
