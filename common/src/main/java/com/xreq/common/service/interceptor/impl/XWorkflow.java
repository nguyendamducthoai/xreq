package com.xreq.common.service.interceptor.impl;

import com.xreq.common.model.XReqBody;
import com.xreq.common.service.interceptor.Task;
import com.xreq.common.service.interceptor.Worker;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
public class XWorkflow extends XTask<XReqBody> {

    List<Task<XReqBody>> tasks = new LinkedList<>();

    final private boolean stopOnFail;

    public XWorkflow(String taskName) {
        super(taskName);
        this.stopOnFail = true;
    }

    public XWorkflow(String taskName, boolean stopOnFail) {
        super(taskName);
        this.stopOnFail = stopOnFail;
    }

    public void addTask(Task<XReqBody> task) {
        if(task == null) return;
        getTasks().add(task);
        log.info(String.format("task added: %s", task.getTaskName()));
    }


    public void process(XReqBody input, Worker<XReqBody> whenAllDone, int index) {

        boolean isContinue = isStopOnFail() && input.isSuccess();

        if (!isContinue || index == getTasks().size()) {
            log.info(String.format("workflow ended: %s", getTaskName()));
            whenAllDone.handle(input);
            return;
        }
        Task<XReqBody> task = getTasks().get(index);
        task.handle(input, x -> {
            process(x, whenAllDone, index + 1);
        });
    }

    @Override
    public void handle(XReqBody input, Worker<XReqBody> whenDone) {
        process(input, whenDone, 0);
    }

    public void onInit() {

    }
}
