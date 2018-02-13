package com.eveasoft.cobinhood.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 12/02/2018.
 */
public class Ticker {

    @SerializedName("trading_pair_id")
    private String tradingPairId;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("24h_high")
    private String _24hHigh;

    @SerializedName("24h_low")
    private String _24hLow;

    @SerializedName("24h_open")
    private String _24hOpen;

    @SerializedName("24h_volume")
    private String _24hVolume;

    @SerializedName("last_trade_price")
    private String lastTradePrice;

    @SerializedName("highest_bid")
    private String highestBid;

    @SerializedName("lowest_ask")
    private String lowestAsk;

    public String getTradingPairId() {
        return tradingPairId;
    }

    public void setTradingPairId(String tradingPairId) {
        this.tradingPairId = tradingPairId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String get24hHigh() {
        return _24hHigh;
    }

    public void set24hHigh(String _24hHigh) {
        this._24hHigh = _24hHigh;
    }

    public String get24hLow() {
        return _24hLow;
    }

    public void set24hLow(String _24hLow) {
        this._24hLow = _24hLow;
    }

    public String get24hOpen() {
        return _24hOpen;
    }

    public void set24hOpen(String _24hOpen) {
        this._24hOpen = _24hOpen;
    }

    public String get24hVolume() {
        return _24hVolume;
    }

    public void set24hVolume(String _24hVolume) {
        this._24hVolume = _24hVolume;
    }

    public String getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(String lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }
}
