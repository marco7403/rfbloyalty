import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRfbLocation } from 'app/shared/model/rfb-location.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RfbLocationService } from './rfb-location.service';
import { RfbLocationDeleteDialogComponent } from './rfb-location-delete-dialog.component';

@Component({
  selector: 'jhi-rfb-location',
  templateUrl: './rfb-location.component.html'
})
export class RfbLocationComponent implements OnInit, OnDestroy {
  rfbLocations?: IRfbLocation[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected rfbLocationService: RfbLocationService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.rfbLocationService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IRfbLocation[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInRfbLocations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRfbLocation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRfbLocations(): void {
    this.eventSubscriber = this.eventManager.subscribe('rfbLocationListModification', () => this.loadPage());
  }

  delete(rfbLocation: IRfbLocation): void {
    const modalRef = this.modalService.open(RfbLocationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rfbLocation = rfbLocation;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IRfbLocation[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/rfb-location'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.rfbLocations = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
