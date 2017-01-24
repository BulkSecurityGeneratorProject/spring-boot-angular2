import {Order}     from '../../../services/order';
import {Component, Input} from '@angular/core';

@Component({
  selector: 'my-item-list',
  templateUrl: './item-list.component.html'
})

export class ItemListComponent {

  @Input()
  order: Order;

  removeItem(item) {
    console.log("remove item",item)
    this.order.items.splice(this.order.items.indexOf(item), 1);
  }

  calculateTotal() {
    this.order.total = 0;
    this.order.items.forEach(i => this.order.total += i.price);
    console.log(this.order.total);
  }
}
