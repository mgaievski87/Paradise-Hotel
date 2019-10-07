import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-gallary',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
  })

export class GalleryComponent implements OnInit {

  imgLargeArr: Array<Image> = [
    {index: 1, URL: './src/app/images/rooms/Double_large.jpg', caption: 'Double room'},
    {index: 2, URL: './src/app/images/rooms/Family_large.jpg', caption: 'Family room'},
    {index: 3, URL: './src/app/images/rooms/Presidential_large.jpg', caption: 'Presidential apartment'},
    {index: 4, URL: './src/app/images/rooms/Single_large.jpg', caption: 'Single room'},
    {index: 5, URL: './src/app/images/rooms/Twin_large.jpg', caption: 'Twin room'}
  ];
  imgSmallArr: Array<Image> = [
    {index: 1, URL: './src/app/images/rooms/Double.jpg', caption: 'Double room'},
    {index: 2, URL: './src/app/images/rooms/Family.jpg', caption: 'Family room'},
    {index: 3, URL: './src/app/images/rooms/Presidential.jpg', caption: 'Presidential apartment'},
    {index: 4, URL: './src/app/images/rooms/Single.jpg', caption: 'Single room'},
    {index: 5, URL: './src/app/images/rooms/Twin.jpg', caption: 'Twin room'}
  ];
  currentSlideIndex: number;
  currentImg: Image;
  ngOnInit() {
    this.showSlide(1);
  }
  showSlide(index: number) {
    if (index > this.imgLargeArr.length) {this.currentSlideIndex = 1; } else {
      if (index < 1) { this.currentSlideIndex = this.imgLargeArr.length; } else {
        this.currentSlideIndex = index; }
    }
    this.currentImg = this.imgLargeArr[this.currentSlideIndex - 1];
  }
}

export class Image {
  index: number;
  URL: string;
  caption: string;
}
