import { FormControl } from '@angular/forms';
import { FuncionarioService } from '../../../services/funcionario.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Funcionario } from 'src/app/models/funcionario';
import { Contato } from 'src/app/models/contato';

@Component({
  selector: 'app-funcionario-list',
  templateUrl: './funcionario-list.component.html',
  styleUrls: ['./funcionario-list.component.css'],
})
export class FuncionarioListComponent implements OnInit {
  ELEMENT_DATA: Funcionario[] = [];

  constructor(private service: FuncionarioService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe((resp) => {
      resp.forEach(element => {
        if (element.cpf != null) {
          this.ELEMENT_DATA.push(element)
        }
      });
    });
  }
}
