import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FuncionarioService } from '../../../services/funcionario.service';
import { Contato } from '../../../models/contato';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Funcionario } from 'src/app/models/funcionario';

@Component({
  selector: 'app-funcionario-create',
  templateUrl: './funcionario-create.component.html',
  styleUrls: ['./funcionario-create.component.css'],
})
export class FuncionarioCreateComponent implements OnInit {
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

  nome: FormControl = new FormControl(null, Validators.minLength(3));
  cpf: FormControl = new FormControl(null, Validators.required);

  cep: FormControl = new FormControl(null, Validators.required);
  logradouro: FormControl = new FormControl(null, Validators.required);
  numero: FormControl = new FormControl(null, Validators.required);
  complemento: FormControl = new FormControl(null);
  bairro: FormControl = new FormControl(null, Validators.required);
  cidade: FormControl = new FormControl(null, Validators.required);
  estado: FormControl = new FormControl(null, Validators.required);

  numeroTelefone: FormControl = new FormControl(null, Validators.required);
  tipo: FormControl = new FormControl(null, Validators.required);

  nomeUsuario: FormControl = new FormControl(null, Validators.required);
  senha: FormControl = new FormControl(null, Validators.required);

  constructor(
    private service: FuncionarioService,
    private toast: ToastrService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  create(): void {
    this.service.create(this.funcionario).subscribe(
      () => {
        this.toast.success('Cadastro efetuado', 'Cadastrado');
        this.router.navigate(['funcionarios']);
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
