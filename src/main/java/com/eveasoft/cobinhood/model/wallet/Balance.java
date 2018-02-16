package com.eveasoft.cobinhood.model.wallet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 16/02/2018.
 */
public class Balance {

    @SerializedName("currency")
    private String currency;

    @SerializedName("type")
    private String type;

    @SerializedName("total")
    private String total;

    @SerializedName("on_order")
    private String onOrder;

    @SerializedName("locked")
    private Boolean locked;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOnOrder() {
        return onOrder;
    }

    public void setOnOrder(String onOrder) {
        this.onOrder = onOrder;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
