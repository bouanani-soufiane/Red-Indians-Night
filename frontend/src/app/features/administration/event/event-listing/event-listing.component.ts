import { Component, OnInit } from '@angular/core';
import { EventService } from '../../../../services/event/event.service';
import { Event } from '../../../../shared/models';

@Component({
  selector: 'app-event-listing',
  standalone: true,
  imports: [],
  templateUrl: './event-listing.component.html',
  styleUrl: './event-listing.component.css'
})
export class EventListingComponent implements OnInit {
  events!: Event[];

  constructor(
    private eventService: EventService,
  ) { }

  ngOnInit(): void {
    this.getEvents();
  }

  getEvents() {
    this.eventService.getAll().subscribe(events => {
      this.events = events;
      console.log("events are here yummy", events);
    });
  }
}
