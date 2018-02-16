package com.eveasoft.cobinhood.model.trading;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class Order {

    @SerializedName("id")
    private String id;

    @SerializedName(value = "trading_pair_id", alternate = "trading_pair")
    private String tradingPairId;

    @SerializedName("side")
    private String side;

    @SerializedName("type")
    private String type;

    @SerializedName("price")
    private String price;

    @SerializedName("size")
    private String size;

    @SerializedName("filled")
    private String filled;

    @SerializedName("state")
    private String state;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("eq_price")
    private String eqPrice;

    @SerializedName("completed_at")
    private Object completedAt;

    public Order(final String tradingPairId, final String side, final String type, final String price, final String size) {

        this.tradingPairId = tradingPairId;
        this.side = side;
        this.type = type;
        this.price = price;
        this.size = size;
    }

    public Order(final String price, final String size) {

        this.price = price;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTradingPairId() {
        return tradingPairId;
    }

    public void setTradingPairId(String tradingPairId) {
        this.tradingPairId = tradingPairId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFilled() {
        return filled;
    }

    public void setFilled(String filled) {
        this.filled = filled;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getEqPrice() {
        return eqPrice;
    }

    public void setEqPrice(String eqPrice) {
        this.eqPrice = eqPrice;
    }

    public Object getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Object completedAt) {
        this.completedAt = completedAt;
    }
}
