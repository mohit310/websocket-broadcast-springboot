import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StocksComponent }      from './stocks.component';

const routes: Routes = [
  { path: '', redirectTo: '/stocks', pathMatch: 'full' },
  { path: 'stocks',     component: StocksComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
