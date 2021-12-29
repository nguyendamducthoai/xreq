package com.xreq.agent.service.task.render;

import com.xreq.agent.service.task.RenderStringTask;
import com.xreq.common.model.ErrorCode;
import com.xreq.common.model.XReqBody;
import com.xreq.common.service.interceptor.Worker;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RenderUrlTask extends RenderStringTask {
    public RenderUrlTask() {
        super(RenderUrlTask.class.getSimpleName());
    }

    @Override
    public void handle(XReqBody input, Worker<XReqBody> whenDone) {
        String url = input.getUrl();
        Optional<String> renderedString = processTemplate(url, input.getModel());
        if (renderedString.isEmpty()) {
            setResult(input, ErrorCode.RENDER_URL);
        } else {
            input.setUrl(renderedString.get());
        }
        whenDone.handle(input);
    }
}
