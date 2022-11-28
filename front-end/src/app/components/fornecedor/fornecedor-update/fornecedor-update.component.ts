import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FornecedorService } from '../../../services/fornecedor.service';
import { Contato } from '../../../models/contato';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Fornecedor } from 'src/app/models/fornecedor';

@Component({
  selector: 'app-fornecedor-update',
  templateUrl: './fornecedor-update.component.html',
  styleUrls: ['./fornecedor-update.component.css'],
})
export class FornecedorUpdateComponent implements OnInit {
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

  nome: FormControl = new FormControl(null, Validators.minLength(3));
  cnpj: FormControl = new FormControl(null, Validators.required);
  inscricaoEstadual: FormControl = new FormControl(null, Validators.minLength(3));
  nomeFantasia: FormControl = new FormControl(null, Validators.required);

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
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.fornecedor.id = this.route.snapshot.paramMap.get('id');
    this.findById();

  }

  findById(): void {
    this.service.findById(this.fornecedor.id).subscribe((resposta) => {
      console.log(resposta);
      this.fornecedor = resposta;
      this.contato = this.fornecedor.contatos[0];
      this.numeroTelefone.setValue(this.contato.numero);
      this.tipo.setValue(this.contato.tipo);
    });
  }

  update(): void {
    this.service.update(this.fornecedor).subscribe(
      () => {
        this.toast.success(
          'Fornecedor atualizado com sucesso!',
          'Atualização feita com sucesso!'
        );
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
