package com.eveasoft.cobinhood.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class CobinResult<T> {

    @SerializedName(value="t", alternate={"currencies", "trading_pairs", "orderbook", "ticker", "orders", "order"})
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
