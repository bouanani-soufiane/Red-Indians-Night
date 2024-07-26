import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environement } from '../../environement/Environement';
import { Event } from '../../shared/models';
import { EventRequest } from '../../shared/DTOs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private readonly baseUrl: string = `${environement.apiUrl}/events`;

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<Event[]> {
    return this.http.get<Event[]>(this.baseUrl);
  }

  paginate(pageSize: number, pageNum: number): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}?pageNum=${pageNum}&pageSize=${pageSize}`);
  }

  getById(id: number): Observable<Event> {
    return this.http.get<Event>(`${this.baseUrl}/${id}`);
  }

  create(data: EventRequest): Observable<Event> {
    return this.http.post<Event>(this.baseUrl, data);
  }

  update(id: number, data: EventRequest): Observable<Event> {
    return this.http.patch<Event>(`${this.baseUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
