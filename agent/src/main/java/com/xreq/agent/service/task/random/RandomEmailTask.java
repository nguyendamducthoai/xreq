package com.xreq.agent.service.task.random;

import com.github.javafaker.service.FakeValuesService;
import com.xreq.common.model.XReqBody;
import com.xreq.common.model.XSystem;
import com.xreq.common.service.interceptor.Worker;
import com.xreq.common.service.interceptor.impl.XTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomEmailTask extends XTask<XReqBody> {

    FakeValuesService fakeValuesService;

    public RandomEmailTask() {
        super(RandomEmailTask.class.getSimpleName());
    }

    @Override
    public void handle(XReqBody input, Worker<XReqBody> whenDone) {
        setModelData(input, XSystem.RANDOM_EMAIL, getRandomEmail());
        whenDone.handle(input);
    }

    String getRandomEmail() {
        String email = fakeValuesService.bothify("????##@gmail.com");;
        return email;
    }

    @Autowired
    public void setFakeValuesService(FakeValuesService fakeValuesService) {
        this.fakeValuesService = fakeValuesService;
    }
}
