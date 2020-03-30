import { IRfbEventAttendance } from 'app/shared/model/rfb-event-attendance.model';

export interface IRfbUser {
  id?: number;
  userName?: string;
  rfbEventAttendances?: IRfbEventAttendance[];
}

export class RfbUser implements IRfbUser {
  constructor(public id?: number, public userName?: string, public rfbEventAttendances?: IRfbEventAttendance[]) {}
}
