package com.eveasoft.cobinhood.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 12/02/2018.
 */
public class TickerRes {

    @SerializedName("ticker")
    private Ticker ticker;

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}
