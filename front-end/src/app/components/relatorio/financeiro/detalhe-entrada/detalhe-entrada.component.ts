import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Compra } from 'src/app/models/compra';
import { CompraService } from 'src/app/services/compra.service';

@Component({
  selector: 'app-detalhe-entrada',
  templateUrl: './detalhe-entrada.component.html',
  styleUrls: ['./detalhe-entrada.component.css'],
})
export class DetalheEntradaComponent implements OnInit {
  showModal: boolean = false;
  idFind?: any;
  
  compra: Compra = {
    id: undefined,
    fornecedor: 0,
    nomeFornecedor: '',
    funcionario: 0,
    nomeFuncionario: '',
    itens: [],
    status: undefined,
    valorVenda: 0,
    dataVenda: '',
  };

  constructor(
    private service: CompraService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.idFind = this.route.snapshot.paramMap.get('id');
    this.findById(this.idFind);
  }

  findById(id: any): void {
    this.service.findById(this.idFind).subscribe(
      (resp) => {
        this.compra = resp;
      },
      (err) => {
        this.toast.error('Compra não encontrada', 'Erro');
        this.router.navigate(['financeiro']);
      }
    );
  }

  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  confirmCancelEntrada(): void {
    this.service.delete(this.idFind).subscribe(
      (resposta) => {
        this.toast.success('Compra cancelada com sucesso', 'Sucesso');
        this.router.navigate(['financeiro']);
      },
      (err) => {
        this.toast.error('Erro ao cancelar compra', 'Erro');
        this.router.navigate(['financeiro']);
      }
    );
  }
}
