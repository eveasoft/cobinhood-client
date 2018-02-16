package com.eveasoft.cobinhood.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guilhem on 13/02/2018.
 */
public class CobinResponse<T> {

    @SerializedName("success")
    private boolean success;

    @SerializedName(value="result", alternate="error")
    private CobinResult<T> result;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CobinResult<T> getResult() {
        return result;
    }

    public void setResult(CobinResult<T> result) {
        this.result = result;
    }
}
