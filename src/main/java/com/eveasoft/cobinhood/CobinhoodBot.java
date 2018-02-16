package com.eveasoft.cobinhood;

import com.eveasoft.cobinhood.exception.CobinException;
import com.eveasoft.cobinhood.model.market.Ticker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guilhem on 16/02/2018.
 */
public class CobinhoodBot {

    static final String BASE_URL = "https://api.cobinhood.com/";
    static final String API_JWT = "";

    static Map<String, String> conf;

    public static void main(String[] args) throws CobinException {

        conf = new HashMap<String, String>();
        conf.put("base.url", BASE_URL);
        conf.put("api.jwt", API_JWT);

        final CobinhoodClient cobinhoodClient = CobinhoodClient.getInstance(conf);

        final Ticker ticker = cobinhoodClient.getTicker("ETH-BTC");

        System.out.println(ticker.getLastTradePrice());
    }

}
