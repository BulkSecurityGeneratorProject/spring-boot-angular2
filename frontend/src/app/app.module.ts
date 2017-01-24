import {NgModule, ApplicationRef} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {AppComponent} from "./app.component";
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";
import {ApiService} from "./shared";
import {removeNgStyles, createNewHosts} from "@angularclass/hmr";
import {AppRoutingModule} from "./app-routing.module";
import {RestaurantService} from "./services/restaurant.service";
import {TopNavBarComponent} from "./components/top-navbar/top-navbar.component";
import {TablesContainerComponent} from "./components/Tables/tables-container.component";
import {OrderViewComponent} from "./components/order-view/order-view.component";
import {OrderHistoryComponent} from "./components/order-history/order-history.component";
import {NewItemFormComponent} from "./components/order-view/new-item-form/new-item-form.component";
import {ItemListComponent} from "./components/order-view/item-list/item-list.component";
import {ItemCommentsComponent} from "./components/order-view/item-comments/item-comments.component";
import {ItemButtonsComponent} from "./components/order-view/item-buttons/item-buttons.component";
import {OrderPipe} from "./pipes/order-pipe";
import {TableViewComponent} from "./components/Tables/table-view/table-view.component";
import {OrderByPipe} from './pipes/order-by-pipe';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    TopNavBarComponent,
    TableViewComponent,
    OrderViewComponent,
    OrderHistoryComponent,
    TablesContainerComponent,
    NewItemFormComponent,
    ItemListComponent,
    ItemCommentsComponent,
    ItemButtonsComponent,
    OrderPipe,
    OrderByPipe
  ],
  providers: [
    ApiService,
    RestaurantService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(public appRef: ApplicationRef) {}
  hmrOnInit(store) {
    console.log('HMR store', store);
  }
  hmrOnDestroy(store) {
    let cmpLocation = this.appRef.components.map(cmp => cmp.location.nativeElement);
    // recreate elements
    store.disposeOldHosts = createNewHosts(cmpLocation);
    // remove styles
    removeNgStyles();
  }
  hmrAfterDestroy(store) {
    // display new elements
    store.disposeOldHosts();
    delete store.disposeOldHosts;
  }
}
