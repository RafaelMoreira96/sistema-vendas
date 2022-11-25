import { Funcionario } from '../models/funcionario';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root',
})
export class FuncionarioService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<Funcionario> {
    return this.http.get<Funcionario>(`${API_CONFIG.baseUrl}/funcionarios/${id}`);
  }

  findAll(): Observable<Funcionario[]> {
    return this.http.get<Funcionario[]>(`${API_CONFIG.baseUrl}/funcionarios`);
  }

  create(funcionario: Funcionario): Observable<Funcionario> {
    return this.http.post<Funcionario>(`${API_CONFIG.baseUrl}/funcionarios`, funcionario);
  }

  update(funcionario: Funcionario): Observable<Funcionario> {
    return this.http.put<Funcionario>(
      `${API_CONFIG.baseUrl}/funcionarios/${funcionario.id}`,
      funcionario
    );
  }

  delete(id: any): Observable<Funcionario> {
    return this.http.delete<Funcionario>(`${API_CONFIG.baseUrl}/funcionarios/${id}`);
  }
}
