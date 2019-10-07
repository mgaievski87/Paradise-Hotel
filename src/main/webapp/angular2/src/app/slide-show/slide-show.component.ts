import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-slide-show',
  templateUrl: './slide-show.component.html',
  styleUrls: ['./slide-show.component.css']
})

export class SlideShowComponent implements OnInit {
  currentImgURL: string;
  imgURLArr = ['./src/app/images/slide-show/Header-Qualia-Resort-Luxury-Australia-Holidays-1-1.jpg',
    './src/app/images/slide-show/whitsundays-apartments-room.jpg',
    './src/app/images/slide-show/heart-hotel-lobby.jpg',
    './src/app/images/slide-show/shingley-resort-airlie-beach-night.jpg',
    './src/app/images/slide-show/Whitsundays-Wedding-Venue-4-1024x512.jpg'
  ];
  slideIndex = 0;
  ngOnInit() {
    this.currentImgURL = this.imgURLArr[0];
    setInterval(() => { this.showSlides(); }, 3000);
  }
  showSlides() {
    let index = this.slideIndex;
    this.slideIndex = ++index % this.imgURLArr.length;
    this.currentImgURL = this.imgURLArr[this.slideIndex];
  }
}
