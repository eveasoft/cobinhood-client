package com.eveasoft.cobinhood.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 12/02/2018.
 */
public class Currency {

    @SerializedName("currency")
    private String currency;

    @SerializedName("name")
    private String name;

    @SerializedName("min_unit")
    private String minUnit;

    @SerializedName("deposit_fee")
    private String depositFee;

    @SerializedName("withdrawal_fee")
    private String withdrawalFee;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public String getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(String depositFee) {
        this.depositFee = depositFee;
    }

    public String getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(String withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

}
