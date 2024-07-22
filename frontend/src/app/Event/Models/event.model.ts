export interface IEvent {
  id: number;
  title: string;
  description: string;
  price: number;
  location: string;
  startDate: Date;
  endDate: Date;
  numberOfAttendees: number;
  isLive: boolean;
}
