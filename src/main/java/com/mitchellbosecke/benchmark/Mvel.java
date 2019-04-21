package com.mitchellbosecke.benchmark;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;


public class Mvel extends BaseBenchmark {

    private Map<String, Object> context;

    private CompiledTemplate template;

    @Setup
    public void setup() throws Exception {
    	template = TemplateCompiler.compileTemplate(Mvel.class.getResourceAsStream("/templates/stocks.mvel.html"));
        this.context = getContext();
    }

    @Benchmark
    public String benchmark() throws Exception {
        ByteArrayOutputStream  out = new ByteArrayOutputStream (BUFFER_SIZE);
        TemplateRuntime.execute(template, context,out);
        return out.toString("UTF-8");
    }
    
    public static void main(String[] args) throws Exception {
    	Mvel test = new Mvel();
    	test.setup();
    	System.out.println("==>"+test.benchmark());
	}

}
