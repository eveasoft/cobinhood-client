package com.eveasoft.cobinhood.model.market;

/**
 * Created by Guilhem on 25/02/2018.
 */
public class Depth {

    private float spread;

    private float highestBid;

    private float lowestAsk;

    private float midPrice;

    public Depth(float spread, float highestBid, float lowestAsk, float midPrice) {

        this.spread = spread;
        this.highestBid = highestBid;
        this.lowestAsk = lowestAsk;
        this.midPrice = midPrice;
    }

    public float getSpread() {
        return spread;
    }

    public void setSpread(float spread) {
        this.spread = spread;
    }

    public float getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(float highestBid) {
        this.highestBid = highestBid;
    }

    public float getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(float lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public float getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(float midPrice) {
        this.midPrice = midPrice;
    }
}
