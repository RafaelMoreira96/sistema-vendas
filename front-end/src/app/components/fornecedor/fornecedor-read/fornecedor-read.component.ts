import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FornecedorService } from '../../../services/fornecedor.service';
import { Contato } from '../../../models/contato';
import { Component, OnInit } from '@angular/core';
import { Fornecedor } from 'src/app/models/fornecedor';

@Component({
  selector: 'app-fornecedor-read',
  templateUrl: './fornecedor-read.component.html',
  styleUrls: ['./fornecedor-read.component.css'],
})
export class FornecedorReadComponent implements OnInit {
  contato: Contato = {
    id: '',
    numero: '',
    tipo: '',
  };

  listContato: Contato[] = [this.contato];

  fornecedor: Fornecedor = {
    id: '',
    cnpj: '',
    nome: '',
    nomeFantasia: '',
    inscricaoEstadual: '',
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
    private service: FornecedorService,
    toastService: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.fornecedor.id = this.route.snapshot.paramMap.get('id');
    this.findById();
  }

  findById(): void {
    this.service.findById(this.fornecedor.id).subscribe(
      (resposta) => {
        this.fornecedor = resposta;
        this.contato = this.fornecedor.contatos[0];
      },
      (ex) => {
        console.log(ex);
      }
    );
  }
}
