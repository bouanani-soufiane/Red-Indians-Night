import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environement } from '../../Environement/Environement';
import { EventRequest } from '../../DTOs/event.request';

@Injectable({
  providedIn: 'root',
})
export class EventServiceService {
  private baseUrl: string = `${environement.apiUrl}events`;

  constructor(private http: HttpClient) { }

  findAll(pageNum: number, pageSize: number): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}?pageNum=${pageNum}&pageSize=${pageSize}`);
  }

  findById(id: number): Observable<Event> {
    return this.http.get<Event>(`${this.baseUrl}${id}`);
  }

  create(eventRequest: EventRequest): Observable<Event> {
    return this.http.post<Event>(this.baseUrl, eventRequest);
  }

  update(eventRequest: EventRequest, id: number): Observable<Event> {
    return this.http.patch<Event>(`${this.baseUrl}${id}`, eventRequest);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}${id}`);
  }
}
