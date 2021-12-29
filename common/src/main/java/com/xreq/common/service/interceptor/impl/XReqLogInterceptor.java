package com.xreq.common.service.interceptor.impl;

import com.xreq.common.service.interceptor.XReqInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XReqLogInterceptor implements XReqInterceptor<Object> {
    @Override
    public Object intercept(Object o) {
        if (o instanceof String) {
            log.info(o+"");
        }
        return o;
    }
}
