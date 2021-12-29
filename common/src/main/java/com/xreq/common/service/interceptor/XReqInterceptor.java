package com.xreq.common.service.interceptor;

public interface XReqInterceptor<X> {
    X intercept(X x);
}
