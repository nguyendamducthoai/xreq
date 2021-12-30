package com.xreq.agent.service.task.random;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.xreq.common.model.XReqBody;
import com.xreq.common.model.XSystem;
import com.xreq.common.service.interceptor.Worker;
import com.xreq.common.service.interceptor.impl.XTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomAddrTask extends XTask<XReqBody> {

    Faker faker;

    public RandomAddrTask() {
        super(RandomAddrTask.class.getSimpleName());
    }

    @Override
    public void handle(XReqBody input, Worker<XReqBody> whenDone) {
        Address address = faker.address();
        input.getModel().put(XSystem.RANDOM_ADDR, address.fullAddress());
        whenDone.handle(input);
    }

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }
}
