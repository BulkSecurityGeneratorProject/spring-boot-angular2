import {Component, OnInit}         from '@angular/core';
import {RestaurantService} from '../../services/restaurant.service';


@Component({
  selector: 'my-top-navbar',
  templateUrl: './top-navbar.component.html'
})

export class TopNavBarComponent implements OnInit {
  ngOnInit(): void {
  }

  constructor(private rest: RestaurantService) {
    console.log(location.protocol+'//'+location.hostname+(location.port?":"+location.port:"")+location.pathname+(location.search?location.search:"")
    )
  }

  newTable() {
    this.rest.newOrder();
  }
}
