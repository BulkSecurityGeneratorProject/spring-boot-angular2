
import {RestaurantService}        from '../../../services/restaurant.service';
import {Input, Component} from '@angular/core';
import {Router} from '@angular/router';
import {Order}     from '../../../services/order';

@Component({
  selector: 'my-item-buttons',
  templateUrl: './item-buttons.component.html'
})

export class ItemButtonsComponent {

  @Input()
  order: Order;

  constructor(private router: Router, private rest:RestaurantService) {}

  public done(order){
    this.rest.saveOrder(order).then(() => this.router.navigate( ['tables']));
  }

  public checkout(order){
    this.rest.checkout(order).then(() => this.router.navigate( ['tables']));
  }
}
