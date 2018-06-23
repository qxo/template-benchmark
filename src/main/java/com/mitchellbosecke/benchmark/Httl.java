package com.mitchellbosecke.benchmark;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import httl.Engine;
import httl.Template;
import httl.util.IOUtils;


public class Httl extends BaseBenchmark {

    private Map<String, Object> context;

    private Template template;

    @Setup
    public void setup() throws Exception {
     	//http://httl.github.io/zh/
    	Engine engine = Engine.getEngine();
    	String tpl = IOUtils.readToString(new InputStreamReader(getResourceAsStream("/templates/stocks.httl.html")));
    	template = engine.parseTemplate(tpl);
    	
		this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws Exception {
    	StringWriter writer = new StringWriter(BUFFER_SIZE);
        template.render(context,writer);
        return writer.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	Httl test = new Httl();
    	test.setup();
    	System.out.println("==>"+test.benchmark());
	}

}
