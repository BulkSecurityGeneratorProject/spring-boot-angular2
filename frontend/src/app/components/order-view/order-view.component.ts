import {Order}        from '../../services/order';
import {ActivatedRoute, Params} from '@angular/router';
import {RestaurantService} from '../../services/restaurant.service';
import {Component} from '@angular/core';

@Component({
  selector: 'my-order-view',
  templateUrl: './order-view.component.html'
})

export class OrderViewComponent {

  public order: Order;

  constructor(private route: ActivatedRoute, private rest:RestaurantService) {
  }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.rest.getOrderById(+params['id']))
      .subscribe(o => this.order = o);
  }
}
