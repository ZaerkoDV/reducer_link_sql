package com.instinctools.reducerlink.service.support;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.ToolManager;

public final class VelocityEngineUtils {
    private VelocityEngineUtils() {
    }

    public static void mergeTemplate(
        VelocityEngine velocityEngine,
        String templateLocation,
        Map<String, Object> model,
        Writer writer
    ) throws VelocityException {
        VelocityContext velocityContext = new VelocityContext(model, (new ToolManager()).createContext());
        velocityEngine.mergeTemplate(templateLocation, "UTF-8", velocityContext, writer);
    }

    public static void mergeTemplate(
        VelocityEngine velocityEngine,
        String templateLocation,
        String encoding,
        Map<String, Object> model,
        Writer writer
    ) throws VelocityException {
        VelocityContext velocityContext = new VelocityContext(model, (new ToolManager()).createContext());
        velocityEngine.mergeTemplate(templateLocation, encoding, velocityContext, writer);
    }

    public static String mergeTemplateIntoString(
        VelocityEngine velocityEngine,
        String templateLocation,
        Map<String, Object> model
    ) throws VelocityException {
        StringWriter result = new StringWriter();
        mergeTemplate(velocityEngine, templateLocation, model, result);
        return result.toString();
    }

    public static String mergeTemplateIntoString(
        VelocityEngine velocityEngine,
        String templateLocation,
        String encoding,
        Map<String, Object> model
    ) throws VelocityException {
        StringWriter result = new StringWriter();
        mergeTemplate(velocityEngine, templateLocation, encoding, model, result);
        return result.toString();
    }
}
