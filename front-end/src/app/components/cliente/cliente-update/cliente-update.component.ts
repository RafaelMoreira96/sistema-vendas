import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ClienteService } from './../../../services/cliente.service';
import { Contato } from './../../../models/contato';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';

@Component({
  selector: 'app-cliente-update',
  templateUrl: './cliente-update.component.html',
  styleUrls: ['./cliente-update.component.css'],
})
export class ClienteUpdateComponent implements OnInit {
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

  nome: FormControl = new FormControl(null, Validators.required);
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
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.cliente.id = this.route.snapshot.paramMap.get('id');
    this.findById();

  }

  findById(): void {
    this.service.findById(this.cliente.id).subscribe((resposta) => {
      console.log(resposta);
      this.cliente = resposta;
      this.contato = this.cliente.contatos[0];
      this.numeroTelefone.setValue(this.contato.numero);
      this.tipo.setValue(this.contato.tipo);
    });
  }

  update(): void {
    this.service.update(this.cliente).subscribe(
      () => {
        this.toast.success(
          'Cliente atualizado com sucesso!',
          'Atualização feita com sucesso!'
        );
        this.router.navigate(['/clientes']);
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
