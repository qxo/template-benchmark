package com.mitchellbosecke.benchmark;

import java.io.StringWriter;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;


public class Jet extends BaseBenchmark {

    private Map<String, Object> context;

    private JetTemplate template;

    @Setup
    public void setup() throws Exception {
    	//http://subchen.github.io/jetbrick-template/2x/syntax-directive.html
    	String tpl = getResouceContent("/templates/stocks.jet.html");
    	template  =	JetEngine.create().createTemplate(tpl);
		this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws Exception {
    	StringWriter writer = new StringWriter(BUFFER_SIZE);
        template.render(context,writer);
        return writer.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	Jet test = new Jet();
    	test.setup();
    	System.out.println("==>"+test.benchmark());
	}

}
