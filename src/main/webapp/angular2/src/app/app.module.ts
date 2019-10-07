import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AgmCoreModule } from '@agm/core';

import { AppComponent } from './app.component';
import { RoomsPaginationComponent } from './rooms-pagination/rooms-pagination.component';
import {SlideShowComponent} from './slide-show/slide-show.component';
import {GalleryComponent} from './gallery/gallery.component';
import {MapComponent} from './map/map.component';
import {EnquireFormComponent} from './enquire-form/enquire-form.component';
import {SearchComponent} from './search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    RoomsPaginationComponent,
    SlideShowComponent,
    GalleryComponent,
    MapComponent,
    EnquireFormComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBFOP35kh9BHc0E9prfgn9nId-PA2WfB7c'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
