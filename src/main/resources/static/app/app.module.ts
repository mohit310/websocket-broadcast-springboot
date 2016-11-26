import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { RouterModule }   from '@angular/router';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule }     from './app-routing.module';

// Imports for loading & configuring the in-memory web api
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';

import { AppComponent }  from './app.component';
import { StocksComponent } from './stocks.component';
import { StockService } from './stock.service';
import './rxjs-extensions';

@NgModule({
  imports:
    [
      BrowserModule,
      FormsModule,
      HttpModule,
      InMemoryWebApiModule.forRoot(InMemoryDataService),
      AppRoutingModule
    ],
  declarations:
    [
      AppComponent,
      StocksComponent
    ],
  providers:
    [
      StockService
    ],
  bootstrap:
    [ AppComponent ]

})

export class AppModule { }
