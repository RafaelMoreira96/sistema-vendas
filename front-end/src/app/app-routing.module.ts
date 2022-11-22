import { ClienteListComponent } from './components/cliente/cliente-list/cliente-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NavComponent } from './components/nav/nav.component';

const routes: Routes = [
  {
    path: '',
    component: NavComponent,
    children: [
      // Tela principal
      { path: 'home', component: HomeComponent },

      // Componentes do Cliente
      { path: 'clientes', component: ClienteListComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }