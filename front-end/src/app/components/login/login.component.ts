import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';
import { Credenciais } from './../../models/credenciais';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  data = new Date().getFullYear();

  creds: Credenciais = {
    nomeUsuario: '',
    senha: '',
  };

  nomeUsuario = new FormControl(null, Validators.required);
  senha = new FormControl(null, Validators.minLength(3));

  constructor(
    private toast: ToastrService,
    private service: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  logar() {
    this.service.authenticate(this.creds).subscribe(
      (resposta) => {
        this.service.successfullLogin(
          resposta.headers.get('Authorization')?.substring(7) as any
        );
        this.router.navigate(['home']);
        this.toast.info('Bem-vindo, Usuário!', 'Login', { timeOut: 3000 });
      },
      () => {
        this.toast.error('Usuário e/ou senha invalidas');
      }
    );
  }
}
