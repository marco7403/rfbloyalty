export interface IRfbUser {
  id?: number;
  userName?: string;
  locationNameId?: number;
}

export class RfbUser implements IRfbUser {
  constructor(public id?: number, public userName?: string, public locationNameId?: number) {}
}
