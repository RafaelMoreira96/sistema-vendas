import { Compra } from '../models/compra';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root',
})
export class CompraService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<Compra> {
    return this.http.get<Compra>(`${API_CONFIG.baseUrl}/compras/${id}`);
  }

  findAll(): Observable<Compra[]> {
    return this.http.get<Compra[]>(`${API_CONFIG.baseUrl}/compras`);
  }

  create(compra: Compra): Observable<Compra> {
    return this.http.post<Compra>(`${API_CONFIG.baseUrl}/compras`, compra);
  }

  update(compra: Compra): Observable<Compra> {
    return this.http.put<Compra>(
      `${API_CONFIG.baseUrl}/compras/${compra.id}`,
      compra
    );
  }

  delete(id: any): Observable<Compra> {
    return this.http.delete<Compra>(`${API_CONFIG.baseUrl}/compras/${id}`);
  }
}
