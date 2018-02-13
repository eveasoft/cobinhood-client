package com.eveasoft.cobinhood.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 12/02/2018.
 */
public class TickerResp {

    @SerializedName("success")
    private boolean success;

    @SerializedName("result")
    private TickerRes result;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TickerRes getResult() {
        return result;
    }

    public void setResult(TickerRes result) {
        this.result = result;
    }
}
