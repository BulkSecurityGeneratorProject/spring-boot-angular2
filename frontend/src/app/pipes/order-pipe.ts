import {Pipe, PipeTransform}  from '@angular/core';
import {Order}      from '../services/order';

@Pipe({ name: 'orderPipe'})
export class OrderPipe implements PipeTransform {

  transform(orders: Order[]) {
    if (orders) {
      return orders
        .filter(order => order.paid == false)
        .map(o => {o.items.forEach(i => o.total += i.price); return o;});
    }
    else {
      return [];
    }
  }
}
