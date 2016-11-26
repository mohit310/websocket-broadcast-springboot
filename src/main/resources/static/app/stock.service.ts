import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Stock } from './stock'

import 'rxjs/add/operator/toPromise';

@Injectable()
export class StockService {

  private stocksUrl = 'app/stocks';

  constructor(private http: Http) { }

  getStocks(): Promise<Stock[]> {
    return this.http.get(this.stocksUrl)
             .toPromise()
             .then(response => response.json().data as Stock[])
             .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }


  private headers = new Headers({'Content-Type': 'application/json'});

}
