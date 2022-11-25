import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from './../../../services/cliente.service';
import { Contato } from './../../../models/contato';
import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';

@Component({
  selector: 'app-cliente-read',
  templateUrl: './cliente-read.component.html',
  styleUrls: ['./cliente-read.component.css'],
})
export class ClienteReadComponent implements OnInit {
  contato: Contato = {
    id: '',
    numero: '',
    tipo: '',
  };

  listContato: Contato[] = [this.contato];

  cliente: Cliente = {
    id: '',
    nome: '',
    cpf: '',
    endereco: {
      id: '',
      cep: '',
      logradouro: '',
      numero: '',
      complemento: '',
      bairro: '',
      cidade: '',
      estado: '',
    },
    contatos: this.listContato,
    dataCadastro: '',
  };

  constructor(
    private service: ClienteService,
    toastService: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.cliente.id = this.route.snapshot.paramMap.get('id');
    this.findById();
  }

  findById(): void {
    this.service.findById(this.cliente.id).subscribe(
      (resposta) => {
        this.cliente = resposta;
        this.contato = this.cliente.contatos[0];
      },
      (ex) => {
        console.log(ex);
      }
    );
  }
}
