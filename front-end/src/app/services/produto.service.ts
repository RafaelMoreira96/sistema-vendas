import { Produto } from '../models/produto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root',
})
export class ProdutoService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<Produto> {
    return this.http.get<Produto>(`${API_CONFIG.baseUrl}/produtos/${id}`);
  }

  findByCodBarras(codBarras: string): Observable<Produto>{
    return this.http.get<Produto>(`${API_CONFIG.baseUrl}/produtos/cod/${codBarras}`);
  }

  findAll(): Observable<Produto[]> {
    return this.http.get<Produto[]>(`${API_CONFIG.baseUrl}/produtos`);
  }

  create(produto: Produto): Observable<Produto> {
    return this.http.post<Produto>(`${API_CONFIG.baseUrl}/produtos`, produto);
  }

  update(produto: Produto): Observable<Produto> {
    return this.http.put<Produto>(
      `${API_CONFIG.baseUrl}/produtos/${produto.id}`,
      produto
    );
  }

  delete(id: any): Observable<Produto> {
    return this.http.delete<Produto>(`${API_CONFIG.baseUrl}/produtos/${id}`);
  }
}
