import { FuncionarioService } from './../../services/funcionario.service';
import { Component, OnInit } from '@angular/core';
import { Form, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-pdv',
  templateUrl: './pdv.component.html',
  styleUrls: ['./pdv.component.css'],
})
export class PdvComponent implements OnInit {
  cpf: FormControl = new FormControl();
  idCliente: FormControl = new FormControl();
  nomeCliente: FormControl = new FormControl();
  idFuncionario: FormControl = new FormControl();
  nomeFuncionario: FormControl = new FormControl();

  constructor(
    private clienteService: ClienteService,
    private funcionarioService: FuncionarioService,
    private toast: ToastrService
  ) {}

  ngOnInit(): void {}

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

  findFuncionario(): void {
    this.funcionarioService.findAll().subscribe((resposta) => {
      var x = 0;
      resposta.forEach(() => {
        resposta.forEach((element) => {
          if (element.cpf == this.cpf.value) {
            x++;
            this.idFuncionario.setValue(element.id);
            this.nomeFuncionario.setValue(element.nome);
          }
        });
      });
      if (x == 0) {
        this.toast.error('Funcionário não encontrado!');
      }
    });
  }
}
