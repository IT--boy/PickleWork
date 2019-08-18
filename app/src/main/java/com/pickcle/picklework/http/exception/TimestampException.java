package com.pickcle.picklework.http.exception;

/**
 * Created by justcan on 2018/1/10.
 */

public class TimestampException extends RuntimeException {

    private long timestamp;
    private int requestVersion;

    public TimestampException(long timestamp, int reqeustVersion) {
        this.timestamp = timestamp;
        this.requestVersion = reqeustVersion;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getRequestVersion() {
        return requestVersion;
    }

    public void setRequestVersion(int requestVersion) {
        this.requestVersion = requestVersion;
    }
}
