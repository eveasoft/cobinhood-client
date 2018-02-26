# Java client for Cobinhood REST API

Covers all endpoints provided by the Cobinhood REST API v1: https://cobinhood.github.io/api-public/

Designed to be as simple as possible.

### Dependencies
This project uses the following libraries:
- retrofit 2.3.0
- okhttp 3.8.0
- gson 2.7
- junit 4.11

### Usage

```
public static void main(String[] args) throws CobinException {

    final Map<String, String> conf = new HashMap<String, String>();
    conf.put("base.url", "https://api.cobinhood.com/");
    conf.put("api.jwt", "<put your api token here>");

    final CobinhoodClient cobinhoodClient = CobinhoodClient.getInstance(conf);

    // Get ticker for a specified trading pair:

    final Ticker ticker = cobinhoodClient.getTicker("ETH-BTC");

    System.out.println(String.format("Last trade price of ETH-BTC pair: %s", ticker.getLastTradePrice()));
    
    // Get market depth for a specified trading pair:
    
    final Depth depth = cobinhoodClient.getDepth("ETH-BTC");
    
    System.out.println(String.format("Highest bid: %f", depth.getHighestBid()));
    System.out.println(String.format("Lowest ask: %f", depth.getLowestAsk()));
    System.out.println(String.format("Spread: %f", depth.getSpread()));
    System.out.println(String.format("Mid price: %f", depth.getMidPrice()));
}
```
Note: API token (api.jwt) is only necessary when requesting the private API (trading and wallet related operations)

### Donation
```
BTC: 1AdSLcRvvSgVr7y1paCM6K4LAiNNbCzsxd
ETH: 0x864d4C1E3a7525F40086B85E48af6d6550cE0539
```