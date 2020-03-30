import { Moment } from 'moment';

export interface IRfbEventAttendance {
  id?: number;
  eventAttendance?: Moment;
  rfbEventId?: number;
  rfbUserId?: number;
}

export class RfbEventAttendance implements IRfbEventAttendance {
  constructor(public id?: number, public eventAttendance?: Moment, public rfbEventId?: number, public rfbUserId?: number) {}
}
