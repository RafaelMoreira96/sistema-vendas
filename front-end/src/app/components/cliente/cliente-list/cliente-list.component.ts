import { ClienteService } from './../../../services/cliente.service';
import { Component, OnInit, PipeTransform, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Cliente } from 'src/app/models/cliente';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css'],
})

export class ClienteListComponent implements OnInit {
  ELEMENT_DATA: Cliente[] = [];
  formModal: any;

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'contato', 'acoes'];
  dataSource = new MatTableDataSource<Cliente>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private service: ClienteService) {}

  ngOnInit(): void {
    this.formModal = new window.bootstrap.Modal(
      document.getElementById('myModal')
    );
    this.findAll();
  }

  openFormModal() {
    this.formModal.show();
  }
  saveSomeThing() {
    // confirm or save something
    this.formModal.hide();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  findAll() {
    this.service.findAll().subscribe((resp) => {
      this.ELEMENT_DATA = resp;
      this.dataSource = new MatTableDataSource<Cliente>(resp);
      this.dataSource.paginator = this.paginator;
      console.log(this.ELEMENT_DATA);
    });
  }
}
