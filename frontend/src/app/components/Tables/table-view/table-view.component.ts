import {Component, Input} from '@angular/core';
import {Router}    from '@angular/router';
import {Order}     from '../../../services/order';

@Component({
  selector: 'my-table-view',
  templateUrl: './table-view.component.html'
})
export class TableViewComponent {

  @Input()
  order: Order;

  constructor(private router: Router) {}

  public editOrder(orderId) {
    this.router.navigate( ['order', orderId] );
  }
}
