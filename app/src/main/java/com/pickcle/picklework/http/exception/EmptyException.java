package com.pickcle.picklework.http.exception;

/**
 * Created by justcan on 2018/3/28.
 */

public class EmptyException extends RuntimeException {
    public EmptyException(String detailMessage) {
        super(detailMessage);
    }
}
