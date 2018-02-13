package com.eveasoft.cobinhood;

import com.eveasoft.cobinhood.api.CobinhoodAPI;
import com.eveasoft.cobinhood.model.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class CobinhoodClient {

    private static volatile CobinhoodClient sInstance;

    static final String BASE_URL = "https://api.cobinhood.com/";

    private final Retrofit retrofit;
    private final CobinhoodAPI api;

    public static CobinhoodClient getInstance() {

        if (sInstance == null) {
            synchronized (CobinhoodClient.class) {
                if (sInstance == null) {
                    sInstance = new CobinhoodClient();
                }
            }
        }
        return sInstance;
    }

    private CobinhoodClient() {

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        api = retrofit.create(CobinhoodAPI.class);
    }


    public Ticker getTicker(final String tradingPairId) {

        final Call<TickerResp> call = api.getTicker(tradingPairId);

        final Ticker ticker;

        try {
            final Response<TickerResp> resp = call.execute();

            ticker = resp.body().getResult().getTicker();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ticker;
    }

    public OrderBook getOrderBook(final String tradingPairId) {

        final Call<CobinResponse<OrderBook>> call = api.getOrderBook(tradingPairId);

        final OrderBook orderBook;

        try {
            final Response<CobinResponse<OrderBook>> resp = call.execute();

            orderBook = resp.body().getResult().getT();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orderBook;
    }


    public List<Order> getOrders(final String authorization) {

        final Call<CobinResponse<List<Order>>> call = api.getOrders(authorization);

        final List<Order> orders;

        try {
            final Response<CobinResponse<List<Order>>> resp = call.execute();

            orders = resp.body().getResult().getT();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    public String placeOrder(final String authorization, final long nonce, final Order order) {

        final Call<CobinResponse<Order>> call = api.placeOrder(authorization, nonce, order);

        final String orderId;

        try {
            final Response<CobinResponse<Order>> resp = call.execute();

            orderId = resp.body().getResult().getT().getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orderId;
    }

    public void modifyOrder(final String authorization, final long nonce, final Order order, final String orderId) {

        final Call<Order> call = api.modifyOrder(authorization, nonce, order, orderId);

        try {
            call.execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
