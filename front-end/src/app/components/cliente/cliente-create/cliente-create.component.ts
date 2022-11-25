import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from './../../../services/cliente.service';
import { Contato } from './../../../models/contato';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';

@Component({
  selector: 'app-cliente-create',
  templateUrl: './cliente-create.component.html',
  styleUrls: ['./cliente-create.component.css'],
})
export class ClienteCreateComponent implements OnInit {
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

  constructor(
    private service: ClienteService,
    private toast: ToastrService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  create(): void {
    this.service.create(this.cliente).subscribe(
      () => {
        this.toast.success('Cadastro efetuado', 'Cadastrado');
        this.router.navigate(['clientes']);
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
