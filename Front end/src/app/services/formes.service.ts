import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormeGeometriqueDTO } from '../dto/forme-geometrique-dto';

@Injectable({
  providedIn: 'root'
})
export class FormesService {

  private baseUrl = 'http://localhost:8888/forms'; //

  constructor(private http: HttpClient) { }

  // Méthode pour obtenir toutes les formes
  getAllFormes(): Observable<FormeGeometriqueDTO[]> {
    return this.http.get<FormeGeometriqueDTO[]>(`${this.baseUrl}`);
  }

  // Méthode pour ajouter une nouvelle forme
  addForme(forme: FormeGeometriqueDTO): Observable<FormeGeometriqueDTO> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<FormeGeometriqueDTO>(`${this.baseUrl}`, forme, { headers });
  }

  // Méthode pour obtenir une forme par son index
  getFormeByIndex(index: number): Observable<FormeGeometriqueDTO> {
    return this.http.get<FormeGeometriqueDTO>(`${this.baseUrl}/${index}`);
  }

  // Méthode pour mettre à jour une forme
  updateForme(index: number, forme: FormeGeometriqueDTO): Observable<FormeGeometriqueDTO> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<FormeGeometriqueDTO>(`${this.baseUrl}/${index}`, forme, { headers });
  }

  // Méthode pour supprimer une forme
  deleteForme(index: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${index}`);
  }
}
