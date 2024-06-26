import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Composition } from '../model/composition';
import { Form } from '../model/form';

@Injectable({
  providedIn: 'root'
})
export class CompositionService {
  private apiUrl = 'http://localhost:8888'; // Remplacez par l'URL correcte de votre backend

  constructor(private http: HttpClient) { }

  getCompositions(): Observable<Composition[]> {
    return this.http.get<Composition[]>(`${this.apiUrl}/compositions`);
  }

  getComposition(id: number): Observable<Composition> {
    return this.http.get<Composition>(`${this.apiUrl}/compositions/${id}`);
  }

  addComposition(composition: Composition): Observable<Composition> {
    return this.http.post<Composition>(`${this.apiUrl}/compositions`, composition);
  }

  updateComposition(composition: Composition): Observable<Composition> {
    return this.http.put<Composition>(`${this.apiUrl}/compositions`, composition);
  }

  deleteComposition(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/compositions/${id}`);
  }

  addFormToComposition(compositionId: number, form: Form): Observable<Composition> {
    return this.http.post<Composition>(`${this.apiUrl}/compositions/${compositionId}/forms`, form);
  }

  updateFormInComposition(compositionId: number, formId: number, form: Form): Observable<Composition> {
    return this.http.put<Composition>(`${this.apiUrl}/compositions/${compositionId}/forms/${formId}`, form);
  }

  deleteFormFromComposition(compositionId: number, formId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/compositions/${compositionId}/forms/${formId}`);
  }
}
