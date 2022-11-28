import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cliente } from 'src/app/models/cliente';
import { Contato } from 'src/app/models/contato';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-cliente-delete',
  templateUrl: './cliente-delete.component.html',
  styleUrls: ['./cliente-delete.component.css'],
})
export class ClienteDeleteComponent implements OnInit {
  idFind?: any;

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
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.idFind = this.route.snapshot.paramMap.get('id');
    this.findById(this.idFind);
  }

  findById(id: any): void {
    this.service.findById(this.idFind).subscribe((resposta) => {
      this.cliente = resposta;
      this.contato = resposta.contatos[0];
    });
  }

  delete(): void {
    this.service.delete(this.cliente.id).subscribe(
      () => {
        this.toast.success(
          'Cliente removido com sucesso!',
          'Remover cliente'
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
