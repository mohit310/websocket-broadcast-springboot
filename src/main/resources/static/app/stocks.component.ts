import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Stock } from './stock';
import { $WebSocket } from './angular2-websocket';

@Component({
  moduleId: module.id,
  selector: 'my-stocks',
  templateUrl: 'stocks.component.html',
  styleUrls: [ 'stocks.component.css' ]
})

export class StocksComponent{
  stocks: Stock[];
  ws: $WebSocket;

  constructor() {
    console.log("creating websocket now");
    this.ws = new $WebSocket("ws://localhost:8080/stocks/websocket");
  }

  ngOnInit(): void {
    console.log("trying to subscribe to ws");
    //this.ws.send("initiate");
    this.ws.getDataStream().subscribe(
      res => {
        console.log(res.data);
        let jsonArray = JSON.parse(res.data);
        this.stocks = jsonArray as Stock[];
      },
      function(e) { console.log('Error: ' + e.message); },
      function() { console.log('Completed'); }
      );
  }

}
