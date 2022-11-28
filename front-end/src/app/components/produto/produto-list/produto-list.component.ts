import { ProdutoService } from '../../../services/produto.service';
import { Component, OnInit} from '@angular/core';
import { Produto } from 'src/app/models/produto';

@Component({
  selector: 'app-produto-list',
  templateUrl: './produto-list.component.html',
  styleUrls: ['./produto-list.component.css'],
})
export class ProdutoListComponent implements OnInit {
  ELEMENT_DATA: Produto[] = [];

  constructor(private service: ProdutoService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe((resp) => {
      this.ELEMENT_DATA = resp;
    });
  }
}
