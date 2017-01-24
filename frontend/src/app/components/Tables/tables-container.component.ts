import {Component, OnInit} from '@angular/core';
import {Order} from '../../services/order';
import {RestaurantService} from '../../services/restaurant.service';

@Component({
  selector: 'my-body-tables',
  templateUrl: './tables-container.component.html'
})
export class TablesContainerComponent implements OnInit {

  ngOnInit(): void {
    console.log("Tables OnInit")
    this.requestOrders();
  }

  requestOrders(): void {
    this.rest.getOrders().then(orders => {this.orders = orders;});
  }

  public orders: Order[];

  public constructor(private rest: RestaurantService) {}
}
