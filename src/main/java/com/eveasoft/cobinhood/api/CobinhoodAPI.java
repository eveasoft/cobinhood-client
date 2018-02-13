package com.eveasoft.cobinhood.api;

import com.eveasoft.cobinhood.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Guilhem on 12/02/2018.
 */
public interface CobinhoodAPI {

    // Returns ticker for specified trading pair:

    @GET("/v1/market/tickers/{trading_pair_id}")
    Call<TickerResp> getTicker(@Path("trading_pair_id") String tradingPairId);


    // Get order book for the trading pair containing all asks/bids:

    @GET("/v1/market/orderbooks/{trading_pair_id}")
    Call<CobinResponse<OrderBook>> getOrderBook(@Path("trading_pair_id") String tradingPairId);


    // List all current orders for user:

    @GET("/v1/trading/orders")
    Call<CobinResponse<List<Order>>> getOrders(@Header("Authorization") String authorization);


    // Place orders to ask or bid:

    @POST("/v1/trading/orders")
    Call<CobinResponse<Order>> placeOrder(@Header("Authorization") String authorization, @Header("nonce") long nonce, @Body Order order);


    // Modify a single order:

    @PUT("/v1/trading/orders/{order_id}")
    Call<Order> modifyOrder(@Header("Authorization") String authorization, @Header("nonce") long nonce, @Body Order order, @Path("order_id") String orderId);
}
