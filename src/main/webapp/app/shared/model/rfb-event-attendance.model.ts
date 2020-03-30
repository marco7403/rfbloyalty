import { Moment } from 'moment';

export interface IRfbEventAttendance {
  id?: number;
  eventAttendance?: Moment;
  rfbUserId?: number;
  rfbEventId?: number;
}

export class RfbEventAttendance implements IRfbEventAttendance {
  constructor(public id?: number, public eventAttendance?: Moment, public rfbUserId?: number, public rfbEventId?: number) {}
}
