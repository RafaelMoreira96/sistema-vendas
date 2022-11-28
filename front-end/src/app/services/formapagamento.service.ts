import { FormaPagamento } from '../models/formapagamento';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root',
})
export class FormaPagamentoService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<FormaPagamento> {
    return this.http.get<FormaPagamento>(`${API_CONFIG.baseUrl}/formapagamentos/${id}`);
  }

  findAll(): Observable<FormaPagamento[]> {
    return this.http.get<FormaPagamento[]>(`${API_CONFIG.baseUrl}/formapagamentos`);
  }

  create(formaPagamento: FormaPagamento): Observable<FormaPagamento> {
    return this.http.post<FormaPagamento>(`${API_CONFIG.baseUrl}/formapagamentos`, formaPagamento);
  }

  update(formaPagamento: FormaPagamento): Observable<FormaPagamento> {
    return this.http.put<FormaPagamento>(
      `${API_CONFIG.baseUrl}/formapagamentos/${formaPagamento.id}`,
      formaPagamento
    );
  }

  delete(id: any): Observable<FormaPagamento> {
    return this.http.delete<FormaPagamento>(`${API_CONFIG.baseUrl}/formapagamentos/${id}`);
  }
}
