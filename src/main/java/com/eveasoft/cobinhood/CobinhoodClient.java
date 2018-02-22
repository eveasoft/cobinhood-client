package com.eveasoft.cobinhood;

import com.eveasoft.cobinhood.api.CobinhoodChartAPI;
import com.eveasoft.cobinhood.api.CobinhoodMarketAPI;
import com.eveasoft.cobinhood.api.CobinhoodTradingAPI;
import com.eveasoft.cobinhood.api.CobinhoodWalletAPI;
import com.eveasoft.cobinhood.exception.CobinException;
import com.eveasoft.cobinhood.model.*;
import com.eveasoft.cobinhood.model.chart.Candle;
import com.eveasoft.cobinhood.model.market.Currency;
import com.eveasoft.cobinhood.model.market.OrderBook;
import com.eveasoft.cobinhood.model.market.Ticker;
import com.eveasoft.cobinhood.model.market.TradingPair;
import com.eveasoft.cobinhood.model.trading.Order;
import com.eveasoft.cobinhood.model.trading.Trade;
import com.eveasoft.cobinhood.model.wallet.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class CobinhoodClient {

    private static volatile CobinhoodClient sInstance;

    private final String baseUrl;
    private final String apiJWT;

    private final Retrofit retrofit;

    private final CobinhoodMarketAPI marketAPI;
    private final CobinhoodChartAPI chartAPI;
    private final CobinhoodTradingAPI tradingAPI;
    private final CobinhoodWalletAPI walletAPI;

    public static CobinhoodClient getInstance(Map<String, String> conf) {

        if (sInstance == null) {
            synchronized (CobinhoodClient.class) {
                if (sInstance == null) {
                    sInstance = new CobinhoodClient(conf);
                }
            }
        }
        return sInstance;
    }

    private CobinhoodClient(Map<String, String> conf) {

        baseUrl = conf.get("base.url");
        apiJWT = conf.get("api.jwt");

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        marketAPI = retrofit.create(CobinhoodMarketAPI.class);
        chartAPI = retrofit.create(CobinhoodChartAPI.class);
        tradingAPI = retrofit.create(CobinhoodTradingAPI.class);
        walletAPI = retrofit.create(CobinhoodWalletAPI.class);
    }

    private synchronized Response<CobinResponse> execute(Call call) throws CobinException {

        try {
            final Response<CobinResponse> resp = call.execute();

            if (resp.code() >= 100 && resp.code() <= 399 && resp.body().getSuccess()) {
                return resp;
            } else if (resp.code() >= 400 && resp.code() <= 599) {
                throw new CobinException(String.format("%d - %s", resp.code(), resp.message()));
            } else {
                throw new CobinException("Unknown error.");
            }
        } catch (IOException e) {
            throw new CobinException(e);
        }
    }

    // Market API

    public synchronized List<Currency> getAllCurrencies() throws CobinException {

        final Call<CobinResponse<List<Currency>>> call = marketAPI.getAllCurrencies();

        final Response<CobinResponse> resp = execute(call);

        final List<Currency> currencies = ((CobinResponse<List<Currency>>) resp.body()).getResult().getT();

        return currencies;
    }

    public synchronized List<TradingPair> getAllTradingPairs() throws CobinException {

        final Call<CobinResponse<List<TradingPair>>> call = marketAPI.getAllTradingPairs();

        final Response<CobinResponse> resp = execute(call);

        final List<TradingPair> tradingPairs = ((CobinResponse<List<TradingPair>>) resp.body()).getResult().getT();

        return tradingPairs;
    }

    public synchronized OrderBook getOrderBook(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<OrderBook>> call = marketAPI.getOrderBook(tradingPairId);

        final Response<CobinResponse> resp = execute(call);

        final OrderBook orderBook = ((CobinResponse<OrderBook>) resp.body()).getResult().getT();

        return orderBook;
    }

    public synchronized Ticker getTicker(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<Ticker>> call = marketAPI.getTicker(tradingPairId);

        final Response<CobinResponse> resp = execute(call);

        final Ticker ticker = ((CobinResponse<Ticker>) resp.body()).getResult().getT();

        return ticker;
    }

    public synchronized List<Trade> getRecentTrades(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = marketAPI.getRecentTrades(tradingPairId);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> recentTrades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return recentTrades;

    }

    public synchronized float getSpread(final String tradingPairId) throws CobinException {

        final OrderBook orderBook = getOrderBook(tradingPairId);

        final float highestBid = Float.valueOf(orderBook.getBids().get(0).get(0));

        final float lowestAsk = Float.valueOf(orderBook.getAsks().get(0).get(0));

        final float spread = (lowestAsk > highestBid) ? lowestAsk - highestBid : 0f;

        return spread;
    }


    // Chart API

    public synchronized List<Candle> getCandles(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<List<Candle>>> call = chartAPI.getCandles(tradingPairId);

        final Response<CobinResponse> resp = execute(call);

        final List<Candle> candles = ((CobinResponse<List<Candle>>) resp.body()).getResult().getT();

        return candles;
    }


    // Trading API

    public synchronized Order getOrder(final String orderId) throws CobinException {

        final Call<CobinResponse<Order>> call = tradingAPI.getOrder(apiJWT, orderId);

        final Response<CobinResponse> resp = execute(call);

        final Order order = ((CobinResponse<Order>) resp.body()).getResult().getT();

        return order;
    }

    public synchronized List<Trade> getTrades(final String orderId) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = tradingAPI.getTrades(apiJWT, orderId);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> trades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return trades;
    }

    public synchronized List<Order> getOrders() throws CobinException {

        final Call<CobinResponse<List<Order>>> call = tradingAPI.getOrders(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Order> orders = ((CobinResponse<List<Order>>) resp.body()).getResult().getT();

        return orders;
    }

    public synchronized String placeOrder(final Order order) throws CobinException {

        final Call<CobinResponse<Order>> call = tradingAPI.placeOrder(apiJWT, System.currentTimeMillis(), order);

        final Response<CobinResponse> resp = execute(call);

        final String orderId = ((CobinResponse<Order>) resp.body()).getResult().getT().getId();

        return orderId;
    }

    public synchronized void modifyOrder(final Order order, final String orderId) throws CobinException {

        final Call<CobinResponse> call = tradingAPI.modifyOrder(apiJWT, System.currentTimeMillis(), order, orderId);

        execute(call);
    }

    public synchronized void cancelOrder(final String orderId) throws CobinException {

        final Call<CobinResponse> call = tradingAPI.cancelOrder(apiJWT, System.currentTimeMillis(), orderId);

        execute(call);
    }

    public synchronized List<Order> getOrderHistory() throws CobinException {

        final Call<CobinResponse<List<Order>>> call = tradingAPI.getOrderHistory(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Order> orders = ((CobinResponse<List<Order>>) resp.body()).getResult().getT();

        return orders;
    }

    public synchronized Trade getTrade(final String tradeId) throws CobinException {

        final Call<CobinResponse<Trade>> call = tradingAPI.getTrade(apiJWT, tradeId);

        final Response<CobinResponse> resp = execute(call);

        final Trade trade = ((CobinResponse<Trade>) resp.body()).getResult().getT();

        return trade;
    }

    public synchronized List<Trade> getTradeHistory(final String orderId) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = tradingAPI.getTradeHistory(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> trades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return trades;
    }


    // Wallet API

    public synchronized List<Balance> getWalletBalances() throws CobinException {

        final Call<CobinResponse<List<Balance>>> call = walletAPI.getWalletBalances(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Balance> balances = ((CobinResponse<List<Balance>>) resp.body()).getResult().getT();

        return balances;
    }

    public synchronized List<Ledger> getLedgerEntries() throws CobinException {

        final Call<CobinResponse<List<Ledger>>> call = walletAPI.getLedgerEntries(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Ledger> ledgers = ((CobinResponse<List<Ledger>>) resp.body()).getResult().getT();

        return ledgers;
    }

    public synchronized List<Address> getDepositAddresses() throws CobinException {

        final Call<CobinResponse<List<Address>>> call = walletAPI.getDepositAddresses(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Address> addresses = ((CobinResponse<List<Address>>) resp.body()).getResult().getT();

        return addresses;
    }

    public synchronized List<Address> getWithdrawalAddresses() throws CobinException {

        final Call<CobinResponse<List<Address>>> call = walletAPI.getWithdrawalAddresses(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Address> addresses = ((CobinResponse<List<Address>>) resp.body()).getResult().getT();

        return addresses;
    }

    public synchronized Withdrawal getWithdrawal(final String withdrawalId) throws CobinException {

        final Call<CobinResponse<Withdrawal>> call = walletAPI.getWithdrawal(apiJWT, withdrawalId);

        final Response<CobinResponse> resp = execute(call);

        final Withdrawal withdrawal = ((CobinResponse<Withdrawal>) resp.body()).getResult().getT();

        return withdrawal;
    }

    public synchronized List<Withdrawal> getWithdrawals() throws CobinException {

        final Call<CobinResponse<List<Withdrawal>>> call = walletAPI.getWithdrawals(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Withdrawal> withdrawals = ((CobinResponse<List<Withdrawal>>) resp.body()).getResult().getT();

        return withdrawals;
    }

    public synchronized Deposit getDeposit(final String depositId) throws CobinException {

        final Call<CobinResponse<Deposit>> call = walletAPI.getDeposit(apiJWT, depositId);

        final Response<CobinResponse> resp = execute(call);

        final Deposit deposit = ((CobinResponse<Deposit>) resp.body()).getResult().getT();

        return deposit;
    }

    public synchronized List<Deposit> getDeposits() throws CobinException {

        final Call<CobinResponse<List<Deposit>>> call = walletAPI.getDeposits(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Deposit> deposits = ((CobinResponse<List<Deposit>>) resp.body()).getResult().getT();

        return deposits;
    }

}
