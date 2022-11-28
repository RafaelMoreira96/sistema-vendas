import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Produto } from 'src/app/models/produto';
import { Contato } from 'src/app/models/contato';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-produto-delete',
  templateUrl: './produto-delete.component.html',
  styleUrls: ['./produto-delete.component.css'],
})
export class ProdutoDeleteComponent implements OnInit {
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

  constructor(
    private service: ProdutoService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.produto.id = this.route.snapshot.paramMap.get('id');
    this.findById(this.produto);
  }

  findById(id: any): void {
    this.service.findById(this.produto.id).subscribe((resposta) => {
      this.produto = resposta;
    });
  }

  delete(): void {
    this.service.delete(this.produto.id).subscribe(
      () => {
        this.toast.success('Produto removido com sucesso!', 'Remover produto');
        this.router.navigate(['/produtos']);
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
