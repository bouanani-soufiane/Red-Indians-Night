import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environement } from '../../../Environement/Environement';
import { Answer } from '../../Models/answer.model';
import { AnswerRequest } from '../../DTOs/answer.request';

@Injectable({
  providedIn: 'root',
})
export class AnswerService {
  private baseUrl: string = `${environement.apiUrl}answers`;

  constructor(private http: HttpClient) { }

  create(answerRequest: AnswerRequest): Observable<Answer> {
    return this.http.post<Answer>(this.baseUrl, answerRequest);
  }

  findByEventQuestionId(eventQuestionId: number): Observable<Answer[]> {
    return this.http.get<Answer[]>(`${this.baseUrl}/question/${eventQuestionId}`);
  }
}
