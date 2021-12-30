package com.xreq.agent.service.task.random;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.xreq.common.model.XReqBody;
import com.xreq.common.model.XSystem;
import com.xreq.common.service.interceptor.Worker;
import com.xreq.common.service.interceptor.impl.XTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomNameTask extends XTask<XReqBody> {

    Faker faker;

    public RandomNameTask() {
        super(RandomNameTask.class.getSimpleName());
    }

    @Override
    public void handle(XReqBody input, Worker<XReqBody> whenDone) {
        Name name = faker.name();

        setModelData(input, XSystem.RANDOM_NAME, name.name());
        setModelData(input, XSystem.RANDOM_FULL_NAME, name.fullName());
        setModelData(input, XSystem.RANDOM_LAST_NAME, name.lastName());
        setModelData(input, XSystem.RANDOM_FIRST_NAME, name.firstName());
        setModelData(input, XSystem.RANDOM_USER_NAME, name.username());
        whenDone.handle(input);
    }

    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }
}
