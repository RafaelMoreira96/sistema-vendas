import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Venda } from 'src/app/models/venda';
import { VendaService } from 'src/app/services/venda.service';

@Component({
  selector: 'app-detalhe-venda',
  templateUrl: './detalhe-venda.component.html',
  styleUrls: ['./detalhe-venda.component.css'],
})
export class DetalheVendaComponent implements OnInit {
  showModal: boolean = false;
  idFind?: any;
  venda: Venda = {
    id: null,
    cliente: 0,
    nomeCliente: '',
    funcionario: 0,
    nomeFuncionario: '',
    itens: [],
    formaPagamento: {
      id: null,
      descricao: '',
    },
    valorVenda: 0.0,
    status: '',
    dataVenda: '',
  };

  constructor(
    private service: VendaService,
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
      (resposta) => {
        this.venda = resposta;
        console.log(this.venda);
      },
      (err) => {
        this.toast.error('Venda não encontrada', 'Erro');
        this.router.navigate(['vendas']);
      }
    );
  }

  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  confirmCancelSale(): void {
    this.service.delete(this.idFind).subscribe(
      (resposta) => {
        this.toast.success('Venda cancelada com sucesso', 'Sucesso');
        this.router.navigate(['/financeiro']);
      },
      (err) => {
        this.toast.error('Erro ao cancelar venda', 'Erro');
        this.router.navigate(['/financeiro']);
      }
    );
  }

}
