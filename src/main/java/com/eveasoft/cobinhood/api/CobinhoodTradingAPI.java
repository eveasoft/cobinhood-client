package com.eveasoft.cobinhood.api;

import com.eveasoft.cobinhood.model.CobinResponse;
import com.eveasoft.cobinhood.model.trading.Order;
import com.eveasoft.cobinhood.model.trading.Trade;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Guilhem on 16/02/2018.
 */
public interface CobinhoodTradingAPI {

    // Get information for a single order:

    @GET("/v1/trading/orders/{order_id}")
    Call<CobinResponse<Order>> getOrder(
            @Header("Authorization") String authorization,
            @Path("order_id") String orderId
    );


    // Get all trades originating from the specific order:

    @GET("/v1/trading/orders/{order_id}/trades")
    Call<CobinResponse<List<Trade>>> getTrades(
            @Header("Authorization") String authorization,
            @Path("order_id") String orderId
    );


    // List all current orders for user:

    @GET("/v1/trading/orders")
    Call<CobinResponse<List<Order>>> getOrders(
            @Header("Authorization") String authorization,
            @Query("page") Integer page,
            @Query("limit") Integer limit
    );


    // Place orders to ask or bid:

    @POST("/v1/trading/orders")
    Call<CobinResponse<Order>> placeOrder(
            @Header("Authorization") String authorization,
            @Header("nonce") long nonce,
            @Body Order order
    );


    // Modify a single order:

    @PUT("/v1/trading/orders/{order_id}")
    Call<CobinResponse> modifyOrder(
            @Header("Authorization") String authorization,
            @Header("nonce") long nonce,
            @Body Order order,
            @Path("order_id") String orderId
    );


    // Cancel a single order:

    @DELETE("/v1/trading/orders/{order_id}")
    Call<CobinResponse> cancelOrder(
            @Header("Authorization") String authorization,
            @Header("nonce") long nonce,
            @Path("order_id") String orderId
    );


    // Returns order history for the current user:

    @GET("/v1/trading/order_history")
    Call<CobinResponse<List<Order>>> getOrderHistory(
            @Header("Authorization") String authorization,
            @Query("page") Integer page,
            @Query("limit") Integer limit
    );


    // Get trade information:

    @GET("/v1/trading/trades/{trade_id}")
    Call<CobinResponse<Trade>> getTrade(
            @Header("Authorization") String authorization,
            @Path("trade_id") String tradeId
    );


    // Returns trade history for the current user:

    @GET("/v1/trading/trades")
    Call<CobinResponse<List<Trade>>> getTradeHistory(
            @Header("Authorization") String authorization,
            @Query("page") Integer page,
            @Query("limit") Integer limit
    );
}
