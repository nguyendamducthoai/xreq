package com.xreq.common.model;

public enum ErrorCode {
    SUCCESS(0),
    UNKNOWN(10000),
    RENDER_BODY(10001),
    RENDER_URL(10002),
    RENDER_HEADER(10003),
    ;

    private long value;

    ErrorCode(int value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
