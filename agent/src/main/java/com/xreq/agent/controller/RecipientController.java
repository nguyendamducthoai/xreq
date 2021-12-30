package com.xreq.agent.controller;

import com.xreq.agent.service.task.random.RandomAddrTask;
import com.xreq.agent.service.task.random.RandomEmailTask;
import com.xreq.agent.service.task.random.RandomNameTask;
import com.xreq.agent.service.task.render.RenderRequestWorkFlow;
import com.xreq.common.model.XReqBody;
import com.xreq.common.service.interceptor.impl.XWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/handle")
public class RecipientController {

    RenderRequestWorkFlow renderRequestWorkFlow;

    XWorkflow myW = new XWorkflow("custom-workflow");

    @Autowired
    RandomEmailTask randomEmailTask;

    @Autowired
    RandomNameTask randomNameTask;

    @Autowired
    RandomAddrTask randomAddrTask;

    @GetMapping
    String ping() {
        return "pong";
    }

    @PostConstruct
    public void onInit() {
//        this.renderRequestWorkFlow.getTasks().add(0, randomEmailTask);
        myW.addTask(randomEmailTask);
        myW.addTask(randomNameTask);
        myW.addTask(randomAddrTask);
        myW.addTask(renderRequestWorkFlow);
    }

    @PostMapping
    Mono<XReqBody> handle(@Valid @RequestBody XReqBody input) {
        CompletableFuture<XReqBody> stringFuture = new CompletableFuture<>();
        myW.handle(input, x -> {
            stringFuture.complete(x);
        });
        return Mono.fromFuture(stringFuture);
    }

    @Autowired
    public void setRenderRequestWorkFlow(RenderRequestWorkFlow renderRequestWorkFlow) {
        this.renderRequestWorkFlow = renderRequestWorkFlow;
    }
}
