package com.xreq.common.service.interceptor;

public interface Worker<T> {
    void handle(T input);
}
