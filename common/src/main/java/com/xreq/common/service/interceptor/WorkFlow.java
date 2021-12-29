package com.xreq.common.service.interceptor;

public interface WorkFlow<T> extends Task<T> {
    void addTask(Task<T> task);
}
