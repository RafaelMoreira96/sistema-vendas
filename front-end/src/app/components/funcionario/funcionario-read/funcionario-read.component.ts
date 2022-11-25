import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FuncionarioService } from '../../../services/funcionario.service';
import { Contato } from '../../../models/contato';
import { Component, OnInit } from '@angular/core';
import { Funcionario } from 'src/app/models/funcionario';

@Component({
  selector: 'app-funcionario-read',
  templateUrl: './funcionario-read.component.html',
  styleUrls: ['./funcionario-read.component.css'],
})
export class FuncionarioReadComponent implements OnInit {
  contato: Contato = {
    id: '',
    numero: '',
    tipo: '',
  };

  listContato: Contato[] = [this.contato];

  funcionario: Funcionario = {
    id: '',
    nome: '',
    cpf: '',
    dataAdmissao: '',
    dataDemissao: '',
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
    nomeUsuario: '',
    senha: ''
  };

  constructor(
    private service: FuncionarioService,
    toastService: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.funcionario.id = this.route.snapshot.paramMap.get('id');
    this.findById();
  }

  findById(): void {
    this.service.findById(this.funcionario.id).subscribe(
      (resposta) => {
        this.funcionario = resposta;
        this.contato = this.funcionario.contatos[0];
      },
      (ex) => {
        console.log(ex);
      }
    );
  }
}
