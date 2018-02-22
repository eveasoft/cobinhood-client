package com.eveasoft.cobinhood.model.chart;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 22/02/2018.
 */
public class Candle {

    @SerializedName("timeframe")
    private String timeframe;

    @SerializedName("trading_pair_id")
    private String tradingPairId;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("volume")
    private String volume;

    @SerializedName("open")
    private String open;

    @SerializedName("close")
    private String close;

    @SerializedName("high")
    private String high;

    @SerializedName("low")
    private String low;

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
