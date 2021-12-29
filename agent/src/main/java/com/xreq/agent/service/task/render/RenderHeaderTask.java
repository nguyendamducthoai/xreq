package com.xreq.agent.service.task.render;

import com.xreq.agent.service.task.RenderStringTask;
import com.xreq.common.model.ErrorCode;
import com.xreq.common.model.XReqBody;
import com.xreq.common.service.interceptor.Worker;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class RenderHeaderTask extends RenderStringTask {
    public RenderHeaderTask() {
        super(RenderHeaderTask.class.getSimpleName());
    }

    @Override
    public void handle(XReqBody input, Worker<XReqBody> whenDone) {
        Map<String, String> headers = input.getHeaders();
        Set<String> keys = headers.keySet();
        for (String k : keys) {
            String val = headers.get(k);
            Optional<String> renderedString = processTemplate(val, input.getModel());
            if (renderedString.isEmpty()) {
                setResult(input, ErrorCode.RENDER_HEADER);
                break;
            }
            headers.put(k, renderedString.get());
        }
        whenDone.handle(input);
    }
}
