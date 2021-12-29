package com.xreq.common.service.interceptor;

import com.xreq.common.model.ErrorCode;

import java.util.Map;

public interface XReq {
    boolean isSuccess();

    void setErrCode(long errCode);

    void setErrDesc(String errDesc);

    void setError(ErrorCode error);

    Map<String, Object> getModel();

    ErrorCode getError();
}
