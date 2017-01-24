import {Component, OnInit} from '@angular/core';
import {Order} from '../../services/order';
import {RestaurantService} from '../../services/restaurant.service';

@Component({
  selector: 'my-order-history',
  templateUrl: './order-history.component.html'
})

export class OrderHistoryComponent implements OnInit {

  public orders: Order[];

  ngOnInit(): void {
    console.log("OrderHistory OnInit")
    this.requestOrders();
  }

  requestOrders(): void {
    this.rest.getOrders().then(orders => {this.orders = orders;});
  }
  public constructor(private rest: RestaurantService) {}
}
