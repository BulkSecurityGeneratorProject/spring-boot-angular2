import {Component, Input} from '@angular/core';
import {Item}      from '../../../services/item';
import {Order}     from '../../../services/order';
import {NgForm} from '@angular/forms';

const MockItems = [
  new Item('Coffee', 30),
  new Item('Cake', 50),
  new Item('Salad', 35),
  new Item('Drink', 9),
  new Item('Desert', 12),
];

@Component({
  selector: 'my-new-item-form',
  templateUrl: './new-item-form.component.html'
})

export class NewItemFormComponent {

  private items: Item[];

  @Input()
  order: Order;

  constructor() {
    this.items = MockItems;
  }

  selectItem(itemId){
    let selectedItem = this.items.filter( item => {
      return item.itemId === itemId;
    })[0];
    this.order.items.push(selectedItem);
  }
}
