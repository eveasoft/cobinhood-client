package com.eveasoft.cobinhood.api;

import com.eveasoft.cobinhood.model.CobinResponse;
import com.eveasoft.cobinhood.model.market.Currency;
import com.eveasoft.cobinhood.model.market.OrderBook;
import com.eveasoft.cobinhood.model.market.Ticker;
import com.eveasoft.cobinhood.model.market.TradingPair;
import com.eveasoft.cobinhood.model.trading.Trade;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by Guilhem on 16/02/2018.
 */
public interface CobinhoodMarketAPI {

    // Returns info for all currencies available for trade:

    @GET("/v1/market/currencies")
    Call<CobinResponse<List<Currency>>> getAllCurrencies();


    // Get info for all trading pairs:

    @GET("/v1/market/trading_pairs")
    Call<CobinResponse<List<TradingPair>>> getAllTradingPairs();


    // Get order book for the trading pair containing all asks/bids:

    @GET("/v1/market/orderbooks/{trading_pair_id}")
    Call<CobinResponse<OrderBook>> getOrderBook(@Path("trading_pair_id") String tradingPairId);


    // Returns ticker for specified trading pair:

    @GET("/v1/market/tickers/{trading_pair_id}")
    Call<CobinResponse<Ticker>> getTicker(@Path("trading_pair_id") String tradingPairId);


    // Returns most recent trades for the specified trading pair:

    @GET("/v1/market/trades/{trading_pair_id}")
    Call<CobinResponse<List<Trade>>> getRecentTrades(@Path("trading_pair_id") String tradingPairId);

}
