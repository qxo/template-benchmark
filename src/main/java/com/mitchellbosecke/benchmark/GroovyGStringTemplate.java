package com.mitchellbosecke.benchmark;

import java.io.StringWriter;
import java.net.URL;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import groovy.text.GStringTemplateEngine;
import groovy.text.Template;


public class GroovyGStringTemplate extends BaseBenchmark {

    private Map<String, Object> context;

    private Template template;

    @Setup
    public void setup() throws Exception {
    	URL url = getResource("/templates/stocks.groovy_simple.html");
		template = new GStringTemplateEngine().createTemplate(url);
        this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws Exception {
    	StringWriter writer = new StringWriter(BUFFER_SIZE);
        template.make(context).writeTo(writer);
        return writer.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	GroovyGStringTemplate test = new GroovyGStringTemplate();
    	test.setup();
    	System.out.println("==>"+test.benchmark());
	}

}
