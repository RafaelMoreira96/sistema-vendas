import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Funcionario } from 'src/app/models/funcionario';
import { Contato } from 'src/app/models/contato';
import { FuncionarioService } from 'src/app/services/funcionario.service';

@Component({
  selector: 'app-funcionario-delete',
  templateUrl: './funcionario-delete.component.html',
  styleUrls: ['./funcionario-delete.component.css'],
})
export class FuncionarioDeleteComponent implements OnInit {
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
    senha: '',
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
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.funcionario.id = this.route.snapshot.paramMap.get('id');
    this.findById();
  }

  findById(): void {
    this.service.findById(this.funcionario.id).subscribe((resposta) => {
      this.funcionario = resposta;
    });
  }

  delete(): void {
    this.service.delete(this.funcionario.id).subscribe(
      () => {
        this.toast.success(
          'Funcionario removido com sucesso!',
          'Remover funcionario'
        );
        this.router.navigate(['/funcionarios']);
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
