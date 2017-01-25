import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TablesContainerComponent} from './components/Tables/tables-container.component';
import {OrderViewComponent} from './components/order-view/order-view.component';
import {OrderHistoryComponent} from './components/order-history/order-history.component';
import {LoginComponent} from './log-in/log-in.component';
import {AuthGuard} from './services/auth-guard';

const routes: Routes = [
  { path: '', redirectTo: '/tables', pathMatch: 'full' },
  { path: 'tables', component: TablesContainerComponent , canActivate: [AuthGuard]},
  { path: 'order/:id', component: OrderViewComponent , canActivate: [AuthGuard]},
  { path: 'history', component: OrderHistoryComponent , canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent },
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  providers: [AuthGuard]
})
export class AppRoutingModule {}
