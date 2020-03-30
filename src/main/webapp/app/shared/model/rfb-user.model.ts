import { IRfbEventAttendance } from 'app/shared/model/rfb-event-attendance.model';

export interface IRfbUser {
  id?: number;
  userName?: string;
  locationNameId?: number;
  rfbEventAttendances?: IRfbEventAttendance[];
}

export class RfbUser implements IRfbUser {
  constructor(
    public id?: number,
    public userName?: string,
    public locationNameId?: number,
    public rfbEventAttendances?: IRfbEventAttendance[]
  ) {}
}
