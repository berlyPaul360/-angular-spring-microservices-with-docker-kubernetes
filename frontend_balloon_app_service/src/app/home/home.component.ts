import { Component } from '@angular/core';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  imageUrl1 = 'assets/images/balloons_1.jpg';
  imageUrl2 = 'assets/images/baby_boxes.jpg';
  imageUrl3 = 'assets/images/christian_apperal.jpg';

  items = [
    {
      title: 'Balloon 1',
      image: 'assets/images/balloons_1.jpg',
      content: 'Description for Balloon 1'
    }
  ];

  constructor(private router: Router) {}

  navigate(path: string) {
    this.router.navigate([path]);
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.items, event.previousIndex, event.currentIndex);
  }

}
