export class Item {

  public itemId: string;
  public price: number;
  public label: string;

  constructor(label: string, price: number) {
    this.itemId = (Math.ceil(Math.random() * new Date().getMilliseconds())).toString();
    this.label  = label;
    this.price  = price;
  }
}
