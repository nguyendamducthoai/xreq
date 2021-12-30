package com.xreq.agent.service.task;

import com.xreq.common.model.XReqBody;
import com.xreq.common.service.interceptor.impl.XTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public abstract class RenderStringTask extends XTask<XReqBody> {

    private final static String TEMPLATE_LOCAL = "US";
    private TemplateEngine templateEngine;
    public RenderStringTask(String taskName) {
        super(taskName);
    }

    public Optional<String> processTemplate(String templateName, Map<String, Object> model) {
        try {
            Context context = new Context(new Locale(TEMPLATE_LOCAL));
            model.entrySet().stream().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
            String process = templateEngine.process(templateName, context);
            return Optional.ofNullable(process);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Autowired
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
}
