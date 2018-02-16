package com.eveasoft.cobinhood.model.market;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class TradingPair {

    @SerializedName("id")
    private String id;

    @SerializedName("base_currency_id")
    private String baseCurrencyId;

    @SerializedName("quote_currency_id")
    private String quoteCurrencyId;

    @SerializedName("base_min_size")
    private String baseMinSize;

    @SerializedName("base_max_size")
    private String baseMaxSize;

    @SerializedName("quote_increment")
    private String quoteIncrement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseCurrencyId() {
        return baseCurrencyId;
    }

    public void setBaseCurrencyId(String baseCurrencyId) {
        this.baseCurrencyId = baseCurrencyId;
    }

    public String getQuoteCurrencyId() {
        return quoteCurrencyId;
    }

    public void setQuoteCurrencyId(String quoteCurrencyId) {
        this.quoteCurrencyId = quoteCurrencyId;
    }

    public String getBaseMinSize() {
        return baseMinSize;
    }

    public void setBaseMinSize(String baseMinSize) {
        this.baseMinSize = baseMinSize;
    }

    public String getBaseMaxSize() {
        return baseMaxSize;
    }

    public void setBaseMaxSize(String baseMaxSize) {
        this.baseMaxSize = baseMaxSize;
    }

    public String getQuoteIncrement() {
        return quoteIncrement;
    }

    public void setQuoteIncrement(String quoteIncrement) {
        this.quoteIncrement = quoteIncrement;
    }
}
