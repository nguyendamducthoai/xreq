package com.xreq.common.service.interceptor.impl;

import com.xreq.common.model.ErrorCode;
import com.xreq.common.service.interceptor.Task;
import com.xreq.common.service.interceptor.XReq;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public abstract class XTask<T extends XReq> implements Task<T> {

    protected final String taskName;

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    public XTask(String taskName) {
        this.taskName = taskName;
    }

    public void setResult(XReq input, ErrorCode errCode) {
        setResult(input, errCode, "Error found");
    }


    public void setResult(XReq input, ErrorCode errCode, String errString) {
        long errCodeValue = errCode.getValue();
        input.setErrCode(errCodeValue);
        input.setErrDesc(errString);
    }

    public void setModelData(T input,String key, Object data) {
        input.getModel().put(key, data);
    }
}
