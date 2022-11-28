import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProdutoService } from '../../../services/produto.service';
import { Contato } from '../../../models/contato';
import { Component, OnInit } from '@angular/core';
import { Produto } from 'src/app/models/produto';

@Component({
  selector: 'app-produto-read',
  templateUrl: './produto-read.component.html',
  styleUrls: ['./produto-read.component.css'],
})
export class ProdutoReadComponent implements OnInit {
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
    toastService: ToastrService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.produto.id = this.route.snapshot.paramMap.get('id');
    this.findById();
  }

  findById(): void {
    this.service.findById(this.produto.id).subscribe(
      (resposta) => {
        this.produto = resposta;
      },
      (ex) => {
        console.log(ex);
      }
    );
  }
}
