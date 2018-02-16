package com.eveasoft.cobinhood.model.wallet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 16/02/2018.
 */
public class Ledger {

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("currency")
    private String currency;

    @SerializedName("type")
    private String type;

    @SerializedName("action")
    private String action;

    @SerializedName("amount")
    private String amount;

    @SerializedName("balance")
    private String balance;

    @SerializedName("trade_id")
    private String tradeId;

    @SerializedName("deposit_id")
    private Object depositId;

    @SerializedName("withdrawal_id")
    private Object withdrawalId;

    @SerializedName("fiat_deposit_id")
    private Object fiatDepositId;

    @SerializedName("fiat_withdrawal_id")
    private Object fiatWithdrawalId;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Object getDepositId() {
        return depositId;
    }

    public void setDepositId(Object depositId) {
        this.depositId = depositId;
    }

    public Object getWithdrawalId() {
        return withdrawalId;
    }

    public void setWithdrawalId(Object withdrawalId) {
        this.withdrawalId = withdrawalId;
    }

    public Object getFiatDepositId() {
        return fiatDepositId;
    }

    public void setFiatDepositId(Object fiatDepositId) {
        this.fiatDepositId = fiatDepositId;
    }

    public Object getFiatWithdrawalId() {
        return fiatWithdrawalId;
    }

    public void setFiatWithdrawalId(Object fiatWithdrawalId) {
        this.fiatWithdrawalId = fiatWithdrawalId;
    }
}
