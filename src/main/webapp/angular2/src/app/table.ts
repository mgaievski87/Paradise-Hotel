import {PaginationPage, PaginationPropertySort} from './pagination';
import {Observable} from 'rxjs/Observable';

export interface Table<T> {
  searchRooms(pageNumber: number, pageSize: number, sort: PaginationPropertySort);
}
