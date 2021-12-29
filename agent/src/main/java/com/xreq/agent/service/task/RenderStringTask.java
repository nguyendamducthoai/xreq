package com.xreq.agent.service.task;

import com.xreq.common.model.XReqBody;
import com.xreq.common.service.interceptor.impl.XTask;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Optional;

public abstract class RenderStringTask extends XTask<XReqBody> {
    public RenderStringTask(String taskName) {
        super(taskName);
    }

    public static Optional<String> processTemplate(String templateName, Map<String, Object> model) {
        try {
            Template t = new Template("simple-name", new StringReader(templateName),
                    new Configuration());
            Writer out = new StringWriter();
            t.process(model, out);
            return Optional.of(out.toString());
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
