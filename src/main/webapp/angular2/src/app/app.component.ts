import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Http, URLSearchParams, RequestOptions, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from 'rxjs/Observable';
import {PaginationPage, PaginationPropertySort} from './pagination';
import {Table} from './table';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', '../styles/spinner.css']
})
export class AppComponent implements OnInit, Table<Room> {

  private baseUrl = 'http://localhost:8080';
  public submitted: boolean;
  roomsearch: FormGroup;
  roomReserve: FormGroup;
  roomsPage: PaginationPage<Room>;
  self: Table<Room>;
  isSelfSetup = false;
  checkinDate: Date;
  checkoutDate: Date;
  currentCheckInVal: string;
  currentCheckOutVal: string;
  currentShowParamVal: string;
  currentPageNumber: number;
  currentPageSize: number;
  currentSort: PaginationPropertySort;
  currentRoomId: number;
  currentClientFirstName: string;
  currentClientLastName: string;
  currentClientEmail: string;
  currentClientPhone: string;
  reservedRoomsBuffer: Array<number>;
  request: ReserveRoomRequest;
  bookingResultMessage: string;
  searchResultMessage: string;
  showSpinner: boolean;
  showBookingSection = false;

  constructor(private http: Http) {}

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl('', [Validators.required]),
      checkout: new FormControl('', [Validators.required]),
      showParam: new FormControl('availableOnly'),
      sortBy: new FormControl('price,asc'),
      pageSize: new FormControl('10')
    });
    this.roomReserve = new FormGroup({
      clientFirstName: new FormControl('', [Validators.required]),
      clientLastName: new FormControl('', [Validators.required]),
      clientEmail: new FormControl('', [Validators.required]),
      clientPhone: new FormControl('', [Validators.required])
    });
    // Setting the default values that we display in the form
    this.checkinDate = new Date();
    this.checkoutDate = new Date();
    this.checkoutDate.setDate(new Date().getDate() + 1);
    this.currentPageNumber = 0;
    this.showSpinner = false;
    // Keeping track of any changes in the forms
    const roomsearchValueChanges$ = this.roomsearch.valueChanges;
    const roomReserveValueChanges$ = this.roomReserve.valueChanges;
    roomsearchValueChanges$.subscribe(valChange => {
      this.currentCheckInVal = valChange.checkin;
      this.currentCheckOutVal = valChange.checkout;
      this.currentShowParamVal = valChange.showParam;
      this.currentPageSize = valChange.pageSize;
      this.currentSort = {
        property: valChange.sortBy.split(',')[0],
        direction: valChange.sortBy.split(',')[1]
      };
    });
    roomReserveValueChanges$.subscribe(valChange => {
      this.currentClientFirstName = valChange.clientFirstName;
      this.currentClientLastName = valChange.clientLastName;
      this.currentClientEmail = valChange.clientEmail;
      this.currentClientPhone = valChange.clientPhone;
    });
  }

  searchRooms(pageNumber: number, pageSize: number, sort: PaginationPropertySort) {

    this.showSpinner = true;
    this.searchResultMessage = undefined;
    this.reservedRoomsBuffer = []; // Cleaning the buffer

    this.getAll(pageNumber, pageSize, sort)
      .subscribe(
        roomsPage => {
          this.showSpinner = false;
          const rooms = roomsPage.content;
          if (rooms.length > 0 && rooms[0].resMsg !== 'ok') {
            //Displaying error message from server if any occured
            this.searchResultMessage = rooms[0].resMsg;
            this.showBookingSection = true;
          } else {
            //In this case everything is ok on the server and 'roomsPage' gets populated with data
            this.showBookingSection = false;
            this.roomsPage = roomsPage;
            if (!this.isSelfSetup) {
              this.self = this;
              this.isSelfSetup = true;
            }
            //window.scrollTo(0, 0);
          }
        },
        err => {
          this.showSpinner = false;
          this.showBookingSection = true;
          console.log(err);
          this.searchResultMessage = 'Please, make sure you specify check-in and check-out dates correctly.';
        }
      );
  }

  getAll(pageNumber: number, pageSize: number, sort: PaginationPropertySort): Observable<PaginationPage<Room>> {
    let params = new URLSearchParams();
    params.set('checkin', `${this.currentCheckInVal}`);
    params.set('checkout', `${this.currentCheckOutVal}`);
    params.set('showParam', `${this.currentShowParamVal}`);
    params.set('size', `${pageSize}`);
    params.set('page', `${pageNumber}`);
    if (sort !== null) {
      params.set('sort', `${sort.property},${sort.direction}`);
    }
    const options = new RequestOptions({
      search: params
    });
    return this.http.get(this.baseUrl + '/room/reservation/v1', options)
      .map(response => response.json());
  }

  reserveRoom(roomId: string) {
    this.showSpinner = true;
    this.reservedRoomsBuffer.push(parseInt(roomId));
    this.request = new ReserveRoomRequest(roomId, this.currentCheckInVal, this.currentCheckOutVal, this.currentClientFirstName, this.currentClientLastName, this.currentClientEmail, this.currentClientPhone);
    this.createReservation(this.request);
  }
  createReservation(body: ReserveRoomRequest) {
    const bodyString = JSON.stringify(body);
    const headers = new Headers({'Content-Type': 'application/json'});
    const option = new RequestOptions({headers: headers});
    const postRequest = this.http.post(this.baseUrl + '/room/reservation/v1', body, option);
    postRequest.map(res => res.json())
      .subscribe(res => {
        this.showSpinner = false;
        console.log(res);
        this.bookingResultMessage = `${res.resMsg}`;
    },
    err => {
      this.showSpinner = false;
      console.log(err);
      this.bookingResultMessage = `Unfortunately, the booking service is temporary unavailable`;
    }
    );
  }

  isInBuffer(roomId: number) {
      return this.reservedRoomsBuffer.indexOf(roomId) >= 0;
  }
  setCurrentRoomId(roomId: number) {
    this.currentRoomId = roomId;
  }
  showReservationForm(roomId: number) {
    return roomId === this.currentRoomId;
  }
  resetReservationForm() {
    this.currentRoomId = null;
  }
  toggleBookingSection() {
    this.showBookingSection = !this.showBookingSection;
  }
}

export class Room {
  id: string;
  roomNumber: string;
  price: string;
  roomType: string;
  description: string;
  imgURL: string;
  available: boolean;
  resMsg: string;
  links: string;
}

export class ReserveRoomRequest {
  roomId: string;
  checkin: string;
  checkout: string;
  clientFirstName: string;
  clientLastName: string;
  clientEmail: string;
  clientPhone: string;
  constructor(
    roomId: string,
    checkin: string,
    checkout: string,
    clientFirstName: string,
    clientLastName: string,
    clientEmail: string,
    clientPhone: string
  ) {
      this.roomId = roomId;
      this.checkin = checkin;
      this.checkout = checkout;
      this.clientFirstName = clientFirstName;
      this.clientLastName = clientLastName;
      this.clientEmail = clientEmail;
      this.clientPhone = clientPhone;
    }
}
