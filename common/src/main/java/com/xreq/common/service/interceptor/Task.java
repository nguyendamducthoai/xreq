package com.xreq.common.service.interceptor;

public interface Task<T> {
    void handle(T input, Worker<T> whenDone);

    String getTaskName();
}
