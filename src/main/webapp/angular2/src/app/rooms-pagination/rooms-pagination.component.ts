import {Component, OnInit, OnChanges, Input} from '@angular/core';
import {PaginationPage, PaginationPropertySort} from '../pagination';
import {Table} from '../table';

@Component ({
  selector: 'app-rooms-pagination',
  templateUrl: './rooms-pagination.component.html',
  styleUrls: ['./rooms-pagination.component.css']
})

export class RoomsPaginationComponent implements OnInit, OnChanges {

  @Input() table: Table<any>;
  @Input() page: PaginationPage<any>;
  pagesIndexes: Array<number> = [];

  ngOnInit() {}

  ngOnChanges(changes) {
    if (changes['page']) {
      const pagesindexes_: Array<number> = [];
      if (this.page.totalPages !== undefined && this.page.totalPages !== null) {
        for (let i = 0; i < this.page.totalPages; i++) {
          pagesindexes_.push(i + 1);
        }
      }
      this.pagesIndexes = pagesindexes_;
    }
  }

  fetchPageNumber(pageNumber: number) {
    this.table.searchRooms(pageNumber - 1, this.page.size, this.getSort());
    console.log('fetchPageNumber() finished');
  }
  fetchNextPage() {
    if (this.page.number + 1 >= this.page.totalPages) {
      return;
    }
    this.table.searchRooms(this.page.number + 1, this.page.size, this.getSort());
  }
  fetchPreviousPage() {
    if (this.page.number === 0) {
      return;
    }
    this.table.searchRooms(this.page.number - 1, this.page.size, this.getSort());
  }

  private getSort(): PaginationPropertySort {
    if (this.page.sort != null && this.page.sort.length > 0) {
      return this.page.sort[0];
    } else {
      return null;
    }
  }
}

