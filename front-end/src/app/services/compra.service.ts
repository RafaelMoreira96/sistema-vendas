import { Compra } from '../models/compra';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CompraService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<Compra> {
    return this.http.get<Compra>(`${API_CONFIG.baseUrl}/compras/${id}`);
  }

  getComprasEntreDatas(dataInicial: string, dataFinal: string) {
    const params = { dataInicial, dataFinal };
    return this.http.get<any[]>(
      `${API_CONFIG.baseUrl}/compras/searchBetweenDates`,
      { params }
    );
  }

  findAll(): Observable<Compra[]> {
    return this.http.get<Compra[]>(`${API_CONFIG.baseUrl}/compras`);
  }

  create(compra: Compra): Observable<Compra> {
    return this.http
      .post<Compra>(`${API_CONFIG.baseUrl}/compras`, compra)
      .pipe(retry(3), catchError(this.handleError));
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
