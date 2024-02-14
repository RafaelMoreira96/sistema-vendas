import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';

import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';

import { ClienteCreateComponent } from './components/cliente/cliente-create/cliente-create.component';
import { ClienteListComponent } from './components/cliente/cliente-list/cliente-list.component';
import { ClienteUpdateComponent } from './components/cliente/cliente-update/cliente-update.component';
import { ClienteReadComponent } from './components/cliente/cliente-read/cliente-read.component';
import { ClienteDeleteComponent } from './components/cliente/cliente-delete/cliente-delete.component';

import { FuncionarioListComponent } from './components/funcionario/funcionario-list/funcionario-list.component';
import { FuncionarioCreateComponent } from './components/funcionario/funcionario-create/funcionario-create.component';
import { FuncionarioDeleteComponent } from './components/funcionario/funcionario-delete/funcionario-delete.component';
import { FuncionarioReadComponent } from './components/funcionario/funcionario-read/funcionario-read.component';
import { FuncionarioUpdateComponent } from './components/funcionario/funcionario-update/funcionario-update.component';

import { FornecedorCreateComponent } from './components/fornecedor/fornecedor-create/fornecedor-create.component';
import { FornecedorDeleteComponent } from './components/fornecedor/fornecedor-delete/fornecedor-delete.component';
import { FornecedorListComponent } from './components/fornecedor/fornecedor-list/fornecedor-list.component';
import { FornecedorReadComponent } from './components/fornecedor/fornecedor-read/fornecedor-read.component';
import { FornecedorUpdateComponent } from './components/fornecedor/fornecedor-update/fornecedor-update.component';

import { ProdutoDeleteComponent } from './components/produto/produto-delete/produto-delete.component';
import { ProdutoListComponent } from './components/produto/produto-list/produto-list.component';
import { ProdutoReadComponent } from './components/produto/produto-read/produto-read.component';
import { ProdutoUpdateComponent } from './components/produto/produto-update/produto-update.component';
import { ProdutoCreateComponent } from './components/produto/produto-create/produto-create.component';

import { PdvComponent } from './components/pdv/pdv.component';
import { CompraComponent } from './components/compra/compra.component';
import { FinanceiroComponent } from './components/relatorio/financeiro/financeiro.component';
import { DetalheVendaComponent } from './components/relatorio/financeiro/detalhe-venda/detalhe-venda.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: '',
    component: NavComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'home', component: HomeComponent },

      // Componentes de CLIENTE
      { path: 'clientes', component: ClienteListComponent },
      { path: 'clientes/create', component: ClienteCreateComponent },
      { path: 'clientes/update/:id', component: ClienteUpdateComponent },
      { path: 'clientes/read/:id', component: ClienteReadComponent },
      { path: 'clientes/delete/:id', component: ClienteDeleteComponent },

      // Components de FUNCIONARIO
      { path: 'funcionarios', component: FuncionarioListComponent },
      { path: 'funcionarios/create', component: FuncionarioCreateComponent },
      {
        path: 'funcionarios/update/:id',
        component: FuncionarioUpdateComponent,
      },
      { path: 'funcionarios/read/:id', component: FuncionarioReadComponent },
      {
        path: 'funcionarios/delete/:id',
        component: FuncionarioDeleteComponent,
      },

      // Components de FORNECEDOR
      { path: 'fornecedores', component: FornecedorListComponent },
      { path: 'fornecedores/create', component: FornecedorCreateComponent },
      { path: 'fornecedores/update/:id', component: FornecedorUpdateComponent },
      { path: 'fornecedores/read/:id', component: FornecedorReadComponent },
      { path: 'fornecedores/delete/:id', component: FornecedorDeleteComponent },

      // Components de PRODUTO
      { path: 'produtos', component: ProdutoListComponent },
      { path: 'produtos/create', component: ProdutoCreateComponent },
      { path: 'produtos/update/:id', component: ProdutoUpdateComponent },
      { path: 'produtos/read/:id', component: ProdutoReadComponent },
      { path: 'produtos/delete/:id', component: ProdutoDeleteComponent },

      // Components de PDV e Compra
      { path: 'vendas', component: PdvComponent },
      { path: 'compras', component: CompraComponent },

      // Financeiro
      { path: 'financeiro', component: FinanceiroComponent },
      { path: 'financeiro/readVenda/:id', component: DetalheVendaComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
