package com.mitchellbosecke.benchmark;

import java.io.ByteArrayOutputStream;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;


public class Jtwig extends BaseBenchmark {

    private JtwigModel context;

    private JtwigTemplate template;

    @Setup
    public void setup() throws Exception {
    	//http://jtwig.org/documentation/quick-start/application
    	template = JtwigTemplate.classpathTemplate("/templates/stocks.jtwig.html");
    	context = JtwigModel.newModel( getContext() );
    }

    @Benchmark
    public String benchmark() throws Exception {
        ByteArrayOutputStream  out = new ByteArrayOutputStream (BUFFER_SIZE);
        template.render(context, out);
        return out.toString("UTF-8");
    }
    
    public static void main(String[] args) throws Exception {
    	Jtwig test = new Jtwig();
    	test.setup();
    	System.out.println("==>"+test.benchmark());
	}

}
