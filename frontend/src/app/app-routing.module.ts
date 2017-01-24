import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TablesContainerComponent} from './components/Tables/tables-container.component';
import {OrderViewComponent} from './components/order-view/order-view.component';
import {OrderHistoryComponent} from './components/order-history/order-history.component';

const routes: Routes = [
  { path: '', redirectTo: '/tables', pathMatch: 'full' },
  { path: 'tables', component: TablesContainerComponent },
  { path: 'order/:id', component: OrderViewComponent },
  { path: 'history', component: OrderHistoryComponent }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
