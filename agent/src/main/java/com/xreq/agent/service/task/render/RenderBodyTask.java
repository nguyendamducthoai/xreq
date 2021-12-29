package com.xreq.agent.service.task.render;

import com.xreq.agent.service.task.RenderStringTask;
import com.xreq.common.model.ErrorCode;
import com.xreq.common.model.XReqBody;
import com.xreq.common.service.interceptor.Worker;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RenderBodyTask extends RenderStringTask {
    public RenderBodyTask() {
        super(RenderBodyTask.class.getSimpleName());
    }


    @Override
    public void handle(XReqBody input, Worker<XReqBody> whenDone) {
        String body = input.getBody();
        Optional<String> renderedBody = processTemplate(body, input.getModel());
        if (!renderedBody.isPresent()) {
            setResult(input, ErrorCode.RENDER_BODY);
            whenDone.handle(input);
            return;
        }
        input.setBody(renderedBody.get());
        whenDone.handle(input);
    }
}
