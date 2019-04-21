package com.mitchellbosecke.benchmark;

import java.io.StringWriter;
import java.util.Map;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import com.jfinal.template.Engine;
import com.jfinal.template.Template;



public class Enjoy extends BaseBenchmark {

    private Map<String, Object> context;

    private Template template;

    @Setup
    public void setup() throws Exception {
     	//http://httl.github.io/zh/
    	Engine engine = new  Engine();
    	String tpl = getResouceContent("/templates/stocks.enjoy.html");
    	template = engine.getTemplateByString(tpl);
    	this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws Exception {
    	StringWriter writer = new StringWriter(BUFFER_SIZE);
        template.render(context,writer);
        return writer.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	Enjoy test = new Enjoy();
    	test.setup();
    	System.out.println("==>"+test.benchmark());
	}

}
