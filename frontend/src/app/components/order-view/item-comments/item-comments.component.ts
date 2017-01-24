import {Input, Component} from '@angular/core';
import {Order}     from '../../../services/order';

@Component({
  selector: 'my-item-comments',
  templateUrl: './item-comments.component.html'
})

export class ItemCommentsComponent {
  @Input()
  order: Order;
}
