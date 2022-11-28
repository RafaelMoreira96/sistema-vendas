import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProdutoService } from '../../../services/produto.service';
import { Contato } from '../../../models/contato';
import { FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/models/produto';

@Component({
  selector: 'app-produto-create',
  templateUrl: './produto-create.component.html',
  styleUrls: ['./produto-create.component.css'],
})
export class ProdutoCreateComponent implements OnInit {
  produto: Produto = {
    id: '',
    descricao: '',
    codBarras: '',
    precoAtacado: 0,
    precoVarejo: 0,
    qteEstoque: 0,
    qteMin: 0,
    qteMax: 0,
  };

  descricao: FormControl = new FormControl(null, Validators.required);
  codBarras: FormControl = new FormControl(null, Validators.required);
  precoVarejo: FormControl = new FormControl(null, Validators.required);
  precoAtacado: FormControl = new FormControl(null, Validators.minLength(3));

  qteMin: FormControl = new FormControl(null, Validators.required);
  qteMax: FormControl = new FormControl(null, Validators.required);
  qteEstoque: FormControl = new FormControl(null, Validators.required);

  constructor(
    private service: ProdutoService,
    private toast: ToastrService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  create(): void {
    this.service.create(this.produto).subscribe(
      () => {
        console.log(this.produto);
        this.toast.success('Cadastro efetuado', 'Cadastrado');
        this.router.navigate(['produtos']);
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
