package com.mitchellbosecke.benchmark;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import httl.util.IOUtils;



public class Beetl extends BaseBenchmark {

    private Map<String, Object> context;

    private Template template;

    @Setup
    public void setup() throws Exception {
     	//http://httl.github.io/zh/
    	String tpl = IOUtils.readToString(new InputStreamReader(getResourceAsStream("/templates/stocks.beetl.html")));
    	StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
    	Configuration cfg = Configuration.defaultConfiguration();
    	GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
    	template = gt.getTemplate(tpl);
		this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws Exception {
    	StringWriter writer = new StringWriter(BUFFER_SIZE);
        template.fastBinding(context);
        template.renderTo(writer);
        return writer.toString();
    }
    
    public static void main(String[] args) throws Exception {
    	Beetl test = new Beetl();
    	test.setup();
    	System.out.println("==>"+test.benchmark());
	}

}
