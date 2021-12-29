package com.xreq.agent.service.task.render;

import com.xreq.common.service.interceptor.impl.XWorkflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RenderRequestWorkFlow extends XWorkflow {

    private RenderBodyTask renderBodyTask;
    private RenderHeaderTask renderHeaderTask;
    private RenderUrlTask renderUrlTask;

    public RenderRequestWorkFlow() {
        super(RenderRequestWorkFlow.class.getSimpleName());
    }

    @Autowired
    public void setRenderBodyTask(RenderBodyTask renderBodyTask) {
        this.renderBodyTask = renderBodyTask;
        addTask(renderBodyTask);
    }

    @Autowired
    public void setRenderHeaderTask(RenderHeaderTask renderHeaderTask) {
        this.renderHeaderTask = renderHeaderTask;
        addTask(renderHeaderTask);
    }

    @Autowired
    public void setRenderUrlTask(RenderUrlTask renderUrlTask) {
        this.renderUrlTask = renderUrlTask;
        addTask(renderUrlTask);
    }
}
