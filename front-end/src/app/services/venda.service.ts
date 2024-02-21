import { Venda } from '../models/venda';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root',
})
export class VendaService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<Venda> {
    return this.http.get<Venda>(`${API_CONFIG.baseUrl}/vendas/${id}`);
  }

  findAll(): Observable<Venda[]> {
    return this.http.get<Venda[]>(`${API_CONFIG.baseUrl}/vendas`);
  }

  getVendasEntreDatas(dataInicial: string, dataFinal: string) {
    const params = { dataInicial, dataFinal };
    return this.http.get<any[]>(
      `${API_CONFIG.baseUrl}/vendas/searchBetweenDates`,
      { params }
    );
  }

  create(venda: Venda): Observable<Venda> {
    return this.http.post<Venda>(`${API_CONFIG.baseUrl}/vendas`, venda).pipe(
      // Tentativa de nova transmissão até 3 vezes em caso de erro
      retry(3),
      // Tratamento de erros
      catchError(this.handleError)
    );
  }

  update(venda: Venda): Observable<Venda> {
    return this.http.put<Venda>(
      `${API_CONFIG.baseUrl}/vendas/${venda.id}`,
      venda
    );
  }

  delete(id: any): Observable<Venda> {
    return this.http.delete<Venda>(`${API_CONFIG.baseUrl}/vendas/${id}`);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('Ocorreu um erro:', error.error.message);
    } else {
      console.error(
        `Código do erro ${error.status}, ` + `Erro: ${error.error}`
      );
    }
    return throwError(
      'Ocorreu um erro ao processar a solicitação. Por favor, tente novamente.'
    );
  }
}
