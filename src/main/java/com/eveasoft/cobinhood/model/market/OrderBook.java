package com.eveasoft.cobinhood.model.market;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Guilhem on 12/02/2018.
 */
public class OrderBook {

    @SerializedName("sequence")
    private long sequence;

    @SerializedName("bids")
    private List<List<String>> bids = null;

    @SerializedName("asks")
    private List<List<String>> asks = null;

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }
}
