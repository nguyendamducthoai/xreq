package com.xreq.common.model;

import com.xreq.common.service.interceptor.XReq;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class XReqBody implements XReq {

    @NotNull
    @NotBlank
    private String url;

    @NotNull
    @NotBlank
    private String body;

    @NotBlank
    @NotNull
    private String method;

//    private boolean success = true;

    private long errCode;

    private String errDesc;

    private ErrorCode error = ErrorCode.SUCCESS;

    private Map<String, Object> model = new HashMap<>();

    private List<InterceptorParam> bodyRequestInterceptors;
    private List<InterceptorParam> bodyResponseInterceptors;

    @NotNull
    @NotEmpty
    private Map<String, String> headers;

    private List<InterceptorParam> headerRequestInterceptors;
    private List<InterceptorParam> headerResponseInterceptors;

    public boolean isSuccess() {
        return getError().getValue() == 0;
    }

}
