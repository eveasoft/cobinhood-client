package com.eveasoft.cobinhood.api;

import com.eveasoft.cobinhood.model.CobinResponse;
import com.eveasoft.cobinhood.model.chart.Candle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by Guilhem on 22/02/2018.
 */
public interface CobinhoodChartAPI {

    // Get charting candles:

    @GET("/v1/chart/candles/{trading_pair_id}")
    Call<CobinResponse<List<Candle>>> getCandles(@Path("trading_pair_id") String tradingPairId);
}
