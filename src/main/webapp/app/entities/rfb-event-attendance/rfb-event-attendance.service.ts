import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRfbEventAttendance } from 'app/shared/model/rfb-event-attendance.model';

type EntityResponseType = HttpResponse<IRfbEventAttendance>;
type EntityArrayResponseType = HttpResponse<IRfbEventAttendance[]>;

@Injectable({ providedIn: 'root' })
export class RfbEventAttendanceService {
  public resourceUrl = SERVER_API_URL + 'api/rfb-event-attendances';

  constructor(protected http: HttpClient) {}

  create(rfbEventAttendance: IRfbEventAttendance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rfbEventAttendance);
    return this.http
      .post<IRfbEventAttendance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rfbEventAttendance: IRfbEventAttendance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rfbEventAttendance);
    return this.http
      .put<IRfbEventAttendance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRfbEventAttendance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRfbEventAttendance[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rfbEventAttendance: IRfbEventAttendance): IRfbEventAttendance {
    const copy: IRfbEventAttendance = Object.assign({}, rfbEventAttendance, {
      eventAttendance:
        rfbEventAttendance.eventAttendance && rfbEventAttendance.eventAttendance.isValid()
          ? rfbEventAttendance.eventAttendance.format(DATE_FORMAT)
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.eventAttendance = res.body.eventAttendance ? moment(res.body.eventAttendance) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rfbEventAttendance: IRfbEventAttendance) => {
        rfbEventAttendance.eventAttendance = rfbEventAttendance.eventAttendance ? moment(rfbEventAttendance.eventAttendance) : undefined;
      });
    }
    return res;
  }
}
