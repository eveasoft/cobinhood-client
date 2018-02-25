import com.eveasoft.cobinhood.CobinhoodClient;
import com.eveasoft.cobinhood.model.chart.Candle;
import com.eveasoft.cobinhood.model.market.*;
import com.eveasoft.cobinhood.model.trading.Order;
import com.eveasoft.cobinhood.model.trading.Trade;
import com.eveasoft.cobinhood.model.wallet.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Guilhem on 22/02/2018.
 */
public class Test_CobinhoodClient {

    static final String BASE_URL = "https://api.cobinhood.com/";
    static final String API_JWT = "<put your api token here>";

    private CobinhoodClient cobinhoodClient;

    @Before
    public void init() {

        final Map<String, String> conf = new HashMap<String, String>();
        conf.put("base.url", BASE_URL);
        conf.put("api.jwt", API_JWT);

        cobinhoodClient = CobinhoodClient.getInstance(conf);
    }

    @Test
    public void testGetAllCurrencies() {

        final List<Currency> currencies = cobinhoodClient.getAllCurrencies();
    }

    @Test
    public void testGetAllTradingPairs() {

        final List<TradingPair> tradingPairs = cobinhoodClient.getAllTradingPairs();
    }

    @Test
    public void testGetOrderBook() {

        final OrderBook orderBook = cobinhoodClient.getOrderBook("ETH-BTC", 2);

        System.out.println(orderBook.getAsks().get(0).get(0));
        System.out.println(orderBook.getAsks().get(0).get(1));
        System.out.println(orderBook.getAsks().get(0).get(2));
        System.out.println(orderBook.getBids().get(0).get(0));
        System.out.println(orderBook.getBids().get(0).get(1));
        System.out.println(orderBook.getBids().get(0).get(2));
    }

    @Test
    public void testGetTicker() {

        final Ticker ticker = cobinhoodClient.getTicker("ETH-BTC");
    }

    @Test
    public void testGetRecentTrades() {

        final List<Trade> trades = cobinhoodClient.getRecentTrades("ETH-BTC", 2);
    }

    @Test
    public void testGetSpread() {

        final float spread = cobinhoodClient.getSpread("ETH-BTC");
    }

    @Test
    public void testGetDepth() {

        final Depth depth = cobinhoodClient.getDepth("ETH-BTC");

        System.out.println(String.format("Highest bid: %f", depth.getHighestBid()));
        System.out.println(String.format("Lowest ask: %f", depth.getLowestAsk()));
        System.out.println(String.format("Spread: %f", depth.getSpread()));
        System.out.println(String.format("Mid price: %f", depth.getMidPrice()));
    }

    @Test
    public void testGetCandles() {

        final List<Candle> candles = cobinhoodClient.getCandles("ETH-BTC");
    }


    @Test
    public void testGetOrder() {

        final List<Order> orders = cobinhoodClient.getOrders(1, 2);

        if (orders.size() > 0) {
            final Order order = cobinhoodClient.getOrder(orders.get(0).getId());
        }
    }

    @Test
    public void testGetTrades() {

        final List<Order> orders = cobinhoodClient.getOrders(1, 2);

        if (orders.size() > 0) {
            final List<Trade> trades = cobinhoodClient.getTrades(orders.get(0).getId());
        }
    }

    @Test
    public void testGetOrders() {

        final List<Order> orders = cobinhoodClient.getOrders(1, 2);
    }

    @Test
    public void testGetOrderHistory() {

        final List<Order> orders = cobinhoodClient.getOrderHistory(1, 2);
    }

    @Test
    public void testGetTrade() {

        final List<Order> orders = getOrders();
        for (final Order order : orders) {
            final List<Trade> trades = getTrades(order.getId());
            if (trades.size() > 0) {
                final Trade trade = cobinhoodClient.getTrade(trades.get(0).getId());
                break;
            }
        }
    }

    @Test
    public void testGetTradeHistory() {

        final List<Order> orders = getOrders();
        for (final Order order : orders) {
            final List<Trade> trades = cobinhoodClient.getTradeHistory(order.getId(), 1, 2);
        }
    }

    private List<Order> getOrders() {

        return cobinhoodClient.getOrders();
    }

    private List<Trade> getTrades(final String orderId) {

        return cobinhoodClient.getTrades(orderId);
    }

    @Test
    public void testGetWalletBalances() {

        final List<Balance> balances = cobinhoodClient.getWalletBalances();
    }

    @Test
    public void testGetLedgerEntries() {

        final List<Ledger> ledgerEntries = cobinhoodClient.getLedgerEntries(1, 2);
    }

    @Test
    public void testGetDepositAddresses() {

        final List<Address> addresses = cobinhoodClient.getDepositAddresses();
    }

    @Test
    public void testGetWithdrawalAddresses() {

        final List<Address> addresses = cobinhoodClient.getWithdrawalAddresses();
    }

    @Test
    public void testGetWithdrawal() {

        final List<Withdrawal> withdrawals = getWithdrawals();
        if (withdrawals.size() > 0) {
            final Withdrawal withdrawal = cobinhoodClient.getWithdrawal(withdrawals.get(0).getWithdrawalId());
        }
    }

    @Test
    public void testGetWithdrawals() {

        final List<Withdrawal> withdrawals = cobinhoodClient.getWithdrawals(1, 2);
    }

    @Test
    public void testGetDeposit() {

        final List<Deposit> deposits = getDeposits();
        if (deposits.size() > 0) {
            final Deposit deposit = cobinhoodClient.getDeposit(deposits.get(0).getDepositId());
        }
    }

    @Test
    public void testGetDeposits() {

        final List<Deposit> deposits = cobinhoodClient.getDeposits(1, 2);
    }

    private List<Deposit> getDeposits() {

        return cobinhoodClient.getDeposits();
    }

    private List<Withdrawal> getWithdrawals() {

        return cobinhoodClient.getWithdrawals();
    }
}
