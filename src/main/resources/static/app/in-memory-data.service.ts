import { InMemoryDbService } from 'angular-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {

  createDb() {
    let stocks = [
        { id: 11, ticker: 'AMD', exchange: 'NASDAQ', price: '8.79' },
        { id: 12, ticker: 'AAPL', exchange: 'NASDAQ', price: '100.45' },
        { id: 13, ticker: 'GOOG', exchange: 'NASDAQ', price: '600.79' }
    ];
    return {stocks};
  }
}
