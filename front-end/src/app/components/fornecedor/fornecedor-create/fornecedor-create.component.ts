import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FornecedorService } from '../../../services/fornecedor.service';
import { Contato } from '../../../models/contato';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Fornecedor } from 'src/app/models/fornecedor';

@Component({
  selector: 'app-fornecedor-create',
  templateUrl: './fornecedor-create.component.html',
  styleUrls: ['./fornecedor-create.component.css'],
})
export class FornecedorCreateComponent implements OnInit {
  contato: Contato = {
    id: '',
    numero: '',
    tipo: '',
  };

  listContato: Contato[] = [this.contato];

  fornecedor: Fornecedor = {
    id: '',
    nome: '', //razao social
    nomeFantasia: '',
    cnpj: '',
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

  nome: FormControl = new FormControl(null, Validators.required);
  nomeFantasia: FormControl = new FormControl(null, Validators.required);
  cnpj: FormControl = new FormControl(null, Validators.required);
  inscricaoEstadual: FormControl = new FormControl(null, Validators.minLength(3));

  cep: FormControl = new FormControl(null, Validators.required);
  logradouro: FormControl = new FormControl(null, Validators.required);
  numero: FormControl = new FormControl(null, Validators.required);
  complemento: FormControl = new FormControl(null);
  bairro: FormControl = new FormControl(null, Validators.required);
  cidade: FormControl = new FormControl(null, Validators.required);
  estado: FormControl = new FormControl(null, Validators.required);

  numeroTelefone: FormControl = new FormControl(null, Validators.required);
  tipo: FormControl = new FormControl(null, Validators.required);

  constructor(
    private service: FornecedorService,
    private toast: ToastrService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  create(): void {
    this.service.create(this.fornecedor).subscribe(
      () => {
        console.log(this.fornecedor);
        this.toast.success('Cadastro efetuado', 'Cadastrado');
        this.router.navigate(['fornecedores']);
      },
      (ex) => {
        if (ex.error.errors) {
          ex.error.errors.forEach(
            (element: { message: string | undefined }) => {
              this.toast.error(element.message);
            }
          );
        } else {
          this.toast.error(ex.error.message);
        }
      }
    );
  }
}
