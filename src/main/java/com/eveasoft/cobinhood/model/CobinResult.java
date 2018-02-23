package com.eveasoft.cobinhood.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class CobinResult<T> {

    @SerializedName("limit")
    private int limit;

    @SerializedName("page")
    private int page;

    @SerializedName("total_page")
    private int totalPage;

    @SerializedName(value="t", alternate={"currencies", "trading_pairs", "orderbook", "ticker", "trades", "candles",
            "orders", "order", "order_history", "balances", "ledger", "deposit_addresses", "withdrawal_addresses",
            "withdrawal", "withdrawals", "deposit", "deposits"})
    private T t;

    public int getLimit() {
        return limit;
    }

    public void setT(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
