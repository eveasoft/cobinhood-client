package com.eveasoft.cobinhood.model.trading;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class Trade {

    @SerializedName("id")
    private String id;

    @SerializedName("price")
    private String price;

    @SerializedName("size")
    private String size;

    @SerializedName("maker_side")
    private String makerSide;

    @SerializedName("timestamp")
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMakerSide() {
        return makerSide;
    }

    public void setMakerSide(String makerSide) {
        this.makerSide = makerSide;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
