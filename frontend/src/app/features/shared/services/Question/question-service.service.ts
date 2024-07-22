import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environement } from '../../Environement/Environement';
import { Question } from '../../Models/question.model';
import { QuestionRequest } from '../../DTOs/question.request';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  private baseUrl: string = `${environement.apiUrl}questions`;

  constructor(private http: HttpClient) { }

  findAll(pageNum: number, pageSize: number): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.baseUrl}?pageNum=${pageNum}&pageSize=${pageSize}`);
  }

  findById(id: number): Observable<Question> {
    return this.http.get<Question>(`${this.baseUrl}/${id}`);
  }

  create(questionRequest: QuestionRequest): Observable<Question> {
    return this.http.post<Question>(this.baseUrl, questionRequest);
  }

  update(questionRequest: QuestionRequest, id: number): Observable<Question> {
    return this.http.patch<Question>(`${this.baseUrl}/${id}`, questionRequest);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
