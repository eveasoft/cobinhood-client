package com.eveasoft.cobinhood.model.wallet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 16/02/2018.
 */
public class Withdrawal {

    @SerializedName("amount")
    private String amount;

    @SerializedName("completed_at")
    private Integer completedAt;

    @SerializedName("confirmations")
    private Integer confirmations;

    @SerializedName("created_at")
    private Integer createdAt;

    @SerializedName("currency")
    private String currency;

    @SerializedName("fee")
    private String fee;

    @SerializedName("required_confirmations")
    private Integer requiredConfirmations;

    @SerializedName("sent_at")
    private Integer sentAt;

    @SerializedName("status")
    private String status;

    @SerializedName("to_address")
    private String toAddress;

    @SerializedName("txhash")
    private String txhash;

    @SerializedName("updated_at")
    private Integer updatedAt;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("withdrawal_id")
    private String withdrawalId;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Integer completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getRequiredConfirmations() {
        return requiredConfirmations;
    }

    public void setRequiredConfirmations(Integer requiredConfirmations) {
        this.requiredConfirmations = requiredConfirmations;
    }

    public Integer getSentAt() {
        return sentAt;
    }

    public void setSentAt(Integer sentAt) {
        this.sentAt = sentAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWithdrawalId() {
        return withdrawalId;
    }

    public void setWithdrawalId(String withdrawalId) {
        this.withdrawalId = withdrawalId;
    }
}
