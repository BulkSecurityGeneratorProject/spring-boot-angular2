import { Item } from './item'

export class Order {

  public id: number;
  public orderId: string;
  public created: Date;
  public diners: number;
  public items: Item[];
  public comments: string;
  public total: number;
  public paid: boolean;
  private closed: Date;

  public constructor(diners: number = 1) {
    this.orderId = Order.nextId();
    this.created = new Date();
    this.diners  = diners;
    this.paid    = false;
    this.total   = 0;
    this.items   = [];
  }


  public setComments(comments) {
    this.comments = comments;
  }

  public addItem(item: Item) {
    this.items.push(item);
  }

  public removeItem(item: Item) {
    console.log("item removeItem",item)
    this.items.splice(this.items.indexOf(item), 1);
  }

  public addToTotal(item:Item) {
    this.total = 0;
    this.items.forEach(i => this.total += i.price);
  }

  public checkout() {
    this.closed = new Date();
    this.paid   = true;
  }

  private static nextId():string {
    function s4() {
      return Math.floor((1 + Math.random()) * 0x10000)
          .toString(16)
          .substring(1);
    }
    return `${s4()}`;
  }
}
