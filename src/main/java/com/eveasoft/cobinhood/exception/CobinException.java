package com.eveasoft.cobinhood.exception;

/**
 * Created by Guilhem on 16/02/2018.
 */
public class CobinException extends RuntimeException {

    public CobinException(final Throwable e) {
        super(e);
    }

    public CobinException(final String msg) {
        super(msg);
    }
}
