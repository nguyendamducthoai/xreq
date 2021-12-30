package com.xreq.common.model;

import com.xreq.common.service.interceptor.XReq;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
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

    private long errCode;

    private String errDesc;

    private ErrorCode error = ErrorCode.SUCCESS;

    private Map<String, Object> model = new HashMap<>();

    @NotNull
    @NotEmpty
    private Map<String, String> headers;

    public boolean isSuccess() {
        return getError().getValue() == 0;
    }

}
