package com.eveasoft.cobinhood;

import com.eveasoft.cobinhood.api.CobinhoodChartAPI;
import com.eveasoft.cobinhood.api.CobinhoodMarketAPI;
import com.eveasoft.cobinhood.api.CobinhoodTradingAPI;
import com.eveasoft.cobinhood.api.CobinhoodWalletAPI;
import com.eveasoft.cobinhood.exception.CobinException;
import com.eveasoft.cobinhood.model.*;
import com.eveasoft.cobinhood.model.chart.Candle;
import com.eveasoft.cobinhood.model.market.*;
import com.eveasoft.cobinhood.model.trading.Order;
import com.eveasoft.cobinhood.model.trading.Trade;
import com.eveasoft.cobinhood.model.wallet.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Java client for Cobinhood REST API
 *
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

    public static CobinhoodClient getInstance(final Map<String, String> conf) {

        if (sInstance == null) {
            synchronized (CobinhoodClient.class) {
                if (sInstance == null) {
                    sInstance = new CobinhoodClient(conf);
                }
            }
        }
        return sInstance;
    }

    private CobinhoodClient(final Map<String, String> conf) {

        baseUrl = conf.get("base.url");
        apiJWT = conf.get("api.jwt");

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build();

        marketAPI = retrofit.create(CobinhoodMarketAPI.class);
        chartAPI = retrofit.create(CobinhoodChartAPI.class);
        tradingAPI = retrofit.create(CobinhoodTradingAPI.class);
        walletAPI = retrofit.create(CobinhoodWalletAPI.class);
    }

    private synchronized Response<CobinResponse> execute(final Call call) throws CobinException {

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

    /**
     * Get all currencies.
     *
     * @return  list of currencies
     * @throws CobinException
     */
    public synchronized List<Currency> getAllCurrencies() throws CobinException {

        final Call<CobinResponse<List<Currency>>> call = marketAPI.getAllCurrencies();

        final Response<CobinResponse> resp = execute(call);

        final List<Currency> currencies = ((CobinResponse<List<Currency>>) resp.body()).getResult().getT();

        return currencies;
    }

    /**
     * Get all trading pairs.
     *
     * @return  list of trading pairs
     * @throws CobinException
     */
    public synchronized List<TradingPair> getAllTradingPairs() throws CobinException {

        final Call<CobinResponse<List<TradingPair>>> call = marketAPI.getAllTradingPairs();

        final Response<CobinResponse> resp = execute(call);

        final List<TradingPair> tradingPairs = ((CobinResponse<List<TradingPair>>) resp.body()).getResult().getT();

        return tradingPairs;
    }

    /**
     * Get order book for the trading pair containing all asks/bids
     *
     * @param tradingPairId trading pair id
     * @return              order book
     * @throws CobinException
     */
    public synchronized OrderBook getOrderBook(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<OrderBook>> call = marketAPI.getOrderBook(tradingPairId, null);

        final Response<CobinResponse> resp = execute(call);

        final OrderBook orderBook = ((CobinResponse<OrderBook>) resp.body()).getResult().getT();

        return orderBook;
    }

    /**
     * Get order book for the trading pair containing all asks/bids with limit.
     *
     * @param tradingPairId trading pair id
     * @param limit         limit
     * @return              order book
     * @throws CobinException
     */
    public synchronized OrderBook getOrderBook(final String tradingPairId, final int limit) throws CobinException {

        final Call<CobinResponse<OrderBook>> call = marketAPI.getOrderBook(tradingPairId, limit);

        final Response<CobinResponse> resp = execute(call);

        final OrderBook orderBook = ((CobinResponse<OrderBook>) resp.body()).getResult().getT();

        return orderBook;
    }

    /**
     * Get ticker for specified trading pair.
     *
     * @param tradingPairId trading pair id
     * @return              ticker
     * @throws CobinException
     */
    public synchronized Ticker getTicker(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<Ticker>> call = marketAPI.getTicker(tradingPairId);

        final Response<CobinResponse> resp = execute(call);

        final Ticker ticker = ((CobinResponse<Ticker>) resp.body()).getResult().getT();

        return ticker;
    }

    /**
     * Get the most recent trades for the specified trading pair.
     *
     * @param tradingPairId trading pair id
     * @return              list of trades
     * @throws CobinException
     */
    public synchronized List<Trade> getRecentTrades(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = marketAPI.getRecentTrades(tradingPairId, null);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> recentTrades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return recentTrades;

    }

    /**
     * Get the most recent trades for the specified trading pair with limit.
     *
     * @param tradingPairId trading pair id
     * @param limit         limit
     * @return              list of trades
     * @throws CobinException
     */
    public synchronized List<Trade> getRecentTrades(final String tradingPairId, final int limit) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = marketAPI.getRecentTrades(tradingPairId, limit);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> recentTrades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return recentTrades;

    }

    /**
     * Get spread for the specified trading pair.
     *
     * @param tradingPairId trading pair id
     * @return              spread
     * @throws CobinException
     */
    public synchronized float getSpread(final String tradingPairId) throws CobinException {

        final OrderBook orderBook = getOrderBook(tradingPairId, 1);

        final float highestBid = Float.valueOf(orderBook.getBids().get(0).get(0));

        final float lowestAsk = Float.valueOf(orderBook.getAsks().get(0).get(0));

        final float spread = (lowestAsk > highestBid) ? lowestAsk - highestBid : 0f;

        return spread;
    }

    /**
     * Get market depth for the specified trading pair.
     *
     * @param tradingPairId trading pair id
     * @return              depth
     * @throws CobinException
     */
    public synchronized Depth getDepth(final String tradingPairId) throws CobinException {

        final OrderBook orderBook = getOrderBook(tradingPairId, 1);

        final float highestBid = Float.valueOf(orderBook.getBids().get(0).get(0));

        final float lowestAsk = Float.valueOf(orderBook.getAsks().get(0).get(0));

        final float spread = (lowestAsk > highestBid) ? lowestAsk - highestBid : 0f;

        final float midPrice = (lowestAsk + highestBid) / 2;

        final Depth depth = new Depth(spread, highestBid, lowestAsk, midPrice);

        return depth;
    }


    // Chart API

    /**
     * Get charting candles for the specified trading pair.
     *
     * @param tradingPairId trading pair id
     * @return              list of candles
     * @throws CobinException
     */
    public synchronized List<Candle> getCandles(final String tradingPairId) throws CobinException {

        final Call<CobinResponse<List<Candle>>> call = chartAPI.getCandles(tradingPairId);

        final Response<CobinResponse> resp = execute(call);

        final List<Candle> candles = ((CobinResponse<List<Candle>>) resp.body()).getResult().getT();

        return candles;
    }


    // Trading API

    /**
     * Get information for a single order.
     *
     * @param orderId   order id
     * @return          order
     * @throws CobinException
     */
    public synchronized Order getOrder(final String orderId) throws CobinException {

        final Call<CobinResponse<Order>> call = tradingAPI.getOrder(apiJWT, orderId);

        final Response<CobinResponse> resp = execute(call);

        final Order order = ((CobinResponse<Order>) resp.body()).getResult().getT();

        return order;
    }

    /**
     * Get all trades originating from the specific order.
     *
     * @param orderId   order id
     * @return          list of trades
     * @throws CobinException
     */
    public synchronized List<Trade> getTrades(final String orderId) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = tradingAPI.getTrades(apiJWT, orderId);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> trades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return trades;
    }

    /**
     * Get all current orders for user.
     *
     * @return  list of orders
     * @throws CobinException
     */
    public synchronized List<Order> getOrders() throws CobinException {

        final Call<CobinResponse<List<Order>>> call = tradingAPI.getOrders(apiJWT, null, null);

        final Response<CobinResponse> resp = execute(call);

        final List<Order> orders = ((CobinResponse<List<Order>>) resp.body()).getResult().getT();

        return orders;
    }

    /**
     * Get all current orders for user with pagination.
     *
     * @param page      page
     * @param limit     limit
     * @return          list of orders
     * @throws CobinException
     */
    public synchronized List<Order> getOrders(final int page, final int limit) throws CobinException {

        final Call<CobinResponse<List<Order>>> call = tradingAPI.getOrders(apiJWT, page, limit);

        final Response<CobinResponse> resp = execute(call);

        final List<Order> orders = ((CobinResponse<List<Order>>) resp.body()).getResult().getT();

        return orders;
    }

    /**
     * Place order to ask or bid.
     *
     * @param order     order
     * @return          order id
     * @throws CobinException
     */
    public synchronized String placeOrder(final Order order) throws CobinException {

        final Call<CobinResponse<Order>> call = tradingAPI.placeOrder(apiJWT, System.currentTimeMillis(), order);

        final Response<CobinResponse> resp = execute(call);

        final String orderId = ((CobinResponse<Order>) resp.body()).getResult().getT().getId();

        return orderId;
    }

    /**
     * Modify a single order.
     *
     * @param order     order
     * @param orderId   order id
     * @throws CobinException
     */
    public synchronized void modifyOrder(final Order order, final String orderId) throws CobinException {

        final Call<CobinResponse> call = tradingAPI.modifyOrder(apiJWT, System.currentTimeMillis(), order, orderId);

        execute(call);
    }

    /**
     * Cancel a single order.
     *
     * @param orderId   order id
     * @throws CobinException
     */
    public synchronized void cancelOrder(final String orderId) throws CobinException {

        final Call<CobinResponse> call = tradingAPI.cancelOrder(apiJWT, System.currentTimeMillis(), orderId);

        execute(call);
    }

    /**
     * Get order history for the current user.
     *
     * @return  list of orders
     * @throws CobinException
     */
    public synchronized List<Order> getOrderHistory() throws CobinException {

        final Call<CobinResponse<List<Order>>> call = tradingAPI.getOrderHistory(apiJWT, null, null);

        final Response<CobinResponse> resp = execute(call);

        final List<Order> orders = ((CobinResponse<List<Order>>) resp.body()).getResult().getT();

        return orders;
    }

    /**
     * Get order history for the current user with pagination.
     *
     * @param page  page
     * @param limit limit
     * @return      list of orders
     * @throws CobinException
     */
    public synchronized List<Order> getOrderHistory(final int page, final int limit) throws CobinException {

        final Call<CobinResponse<List<Order>>> call = tradingAPI.getOrderHistory(apiJWT, page, limit);

        final Response<CobinResponse> resp = execute(call);

        final List<Order> orders = ((CobinResponse<List<Order>>) resp.body()).getResult().getT();

        return orders;
    }

    /**
     * Get trade information. A user only can get their own trade.
     *
     * @param tradeId   trade id
     * @return             list of trades
     * @throws CobinException
     */
    public synchronized Trade getTrade(final String tradeId) throws CobinException {

        final Call<CobinResponse<Trade>> call = tradingAPI.getTrade(apiJWT, tradeId);

        final Response<CobinResponse> resp = execute(call);

        final Trade trade = ((CobinResponse<Trade>) resp.body()).getResult().getT();

        return trade;
    }

    /**
     * Get trade history for the current user.
     *
     * @param orderId   order id
     * @return          list of trades
     * @throws CobinException
     */
    public synchronized List<Trade> getTradeHistory(final String orderId) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = tradingAPI.getTradeHistory(apiJWT, null, null);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> trades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return trades;
    }

    /**
     * Get trade history for the current user with pagination.
     *
     * @param orderId   order id
     * @param page      page
     * @param limit     limit
     * @return          list of trades
     * @throws CobinException
     */
    public synchronized List<Trade> getTradeHistory(final String orderId, final int page, final int limit) throws CobinException {

        final Call<CobinResponse<List<Trade>>> call = tradingAPI.getTradeHistory(apiJWT, page, limit);

        final Response<CobinResponse> resp = execute(call);

        final List<Trade> trades = ((CobinResponse<List<Trade>>) resp.body()).getResult().getT();

        return trades;
    }


    // Wallet API

    /**
     * Get balances of the current user.
     *
     * @return  list of balances
     * @throws CobinException
     */
    public synchronized List<Balance> getWalletBalances() throws CobinException {

        final Call<CobinResponse<List<Balance>>> call = walletAPI.getWalletBalances(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Balance> balances = ((CobinResponse<List<Balance>>) resp.body()).getResult().getT();

        return balances;
    }

    /**
     * Get balance history for the current user.
     *
     * @return  list of ledger entries
     * @throws CobinException
     */
    public synchronized List<Ledger> getLedgerEntries() throws CobinException {

        final Call<CobinResponse<List<Ledger>>> call = walletAPI.getLedgerEntries(apiJWT, null, null);

        final Response<CobinResponse> resp = execute(call);

        final List<Ledger> ledgers = ((CobinResponse<List<Ledger>>) resp.body()).getResult().getT();

        return ledgers;
    }

    /**
     * Get balance history for the current user with pagination.
     *
     * @param page  page
     * @param limit limit
     * @return      list of ledger entries
     * @throws CobinException
     */
    public synchronized List<Ledger> getLedgerEntries(final int page, final int limit) throws CobinException {

        final Call<CobinResponse<List<Ledger>>> call = walletAPI.getLedgerEntries(apiJWT, page, limit);

        final Response<CobinResponse> resp = execute(call);

        final List<Ledger> ledgers = ((CobinResponse<List<Ledger>>) resp.body()).getResult().getT();

        return ledgers;
    }

    /**
     * Get wallet deposit addresses.
     *
     * @return  list of addresses
     * @throws CobinException
     */
    public synchronized List<Address> getDepositAddresses() throws CobinException {

        final Call<CobinResponse<List<Address>>> call = walletAPI.getDepositAddresses(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Address> addresses = ((CobinResponse<List<Address>>) resp.body()).getResult().getT();

        return addresses;
    }

    /**
     * Get wallet withdrawal addresses.
     *
     * @return  list of addresses
     * @throws CobinException
     */
    public synchronized List<Address> getWithdrawalAddresses() throws CobinException {

        final Call<CobinResponse<List<Address>>> call = walletAPI.getWithdrawalAddresses(apiJWT);

        final Response<CobinResponse> resp = execute(call);

        final List<Address> addresses = ((CobinResponse<List<Address>>) resp.body()).getResult().getT();

        return addresses;
    }

    /**
     * Get withdrawal information.
     *
     * @param withdrawalId  withdrawal id
     * @return              withdrawal
     * @throws CobinException
     */
    public synchronized Withdrawal getWithdrawal(final String withdrawalId) throws CobinException {

        final Call<CobinResponse<Withdrawal>> call = walletAPI.getWithdrawal(apiJWT, withdrawalId);

        final Response<CobinResponse> resp = execute(call);

        final Withdrawal withdrawal = ((CobinResponse<Withdrawal>) resp.body()).getResult().getT();

        return withdrawal;
    }

    /**
     * Get all withdrawals.
     *
     * @return  list of withdrawals
     * @throws CobinException
     */
    public synchronized List<Withdrawal> getWithdrawals() throws CobinException {

        final Call<CobinResponse<List<Withdrawal>>> call = walletAPI.getWithdrawals(apiJWT, null, null);

        final Response<CobinResponse> resp = execute(call);

        final List<Withdrawal> withdrawals = ((CobinResponse<List<Withdrawal>>) resp.body()).getResult().getT();

        return withdrawals;
    }

    /**
     * Get all withdrawals with pagination.
     *
     * @param page  page
     * @param limit limit
     * @return      list of withdrawals
     * @throws CobinException
     */
    public synchronized List<Withdrawal> getWithdrawals(final int page, final int limit) throws CobinException {

        final Call<CobinResponse<List<Withdrawal>>> call = walletAPI.getWithdrawals(apiJWT, page, limit);

        final Response<CobinResponse> resp = execute(call);

        final List<Withdrawal> withdrawals = ((CobinResponse<List<Withdrawal>>) resp.body()).getResult().getT();

        return withdrawals;
    }

    /**
     * Get deposit information.
     *
     * @param depositId deposit id
     * @return          deposit
     * @throws CobinException
     */
    public synchronized Deposit getDeposit(final String depositId) throws CobinException {

        final Call<CobinResponse<Deposit>> call = walletAPI.getDeposit(apiJWT, depositId);

        final Response<CobinResponse> resp = execute(call);

        final Deposit deposit = ((CobinResponse<Deposit>) resp.body()).getResult().getT();

        return deposit;
    }

    /**
     * Get all deposits.
     *
     * @return  list of deposits
     * @throws CobinException
     */
    public synchronized List<Deposit> getDeposits() throws CobinException {

        final Call<CobinResponse<List<Deposit>>> call = walletAPI.getDeposits(apiJWT, null, null);

        final Response<CobinResponse> resp = execute(call);

        final List<Deposit> deposits = ((CobinResponse<List<Deposit>>) resp.body()).getResult().getT();

        return deposits;
    }

    /**
     * Get all deposits with pagination.
     *
     * @param page  page
     * @param limit limit
     * @return      list of deposits
     * @throws CobinException
     */
    public synchronized List<Deposit> getDeposits(final int page, final int limit) throws CobinException {

        final Call<CobinResponse<List<Deposit>>> call = walletAPI.getDeposits(apiJWT, page, limit);

        final Response<CobinResponse> resp = execute(call);

        final List<Deposit> deposits = ((CobinResponse<List<Deposit>>) resp.body()).getResult().getT();

        return deposits;
    }
}
