package com.pickcle.picklework.http.exception;

/**
 * Created by justcan on 2018/3/28.
 */

public class ServerException extends RuntimeException {
    public ServerException(String detailMessage) {
        super(detailMessage);
    }
}
