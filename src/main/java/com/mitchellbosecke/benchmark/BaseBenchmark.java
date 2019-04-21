package com.mitchellbosecke.benchmark;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import com.mitchellbosecke.benchmark.model.Stock;

import httl.util.IOUtils;

@Fork(5)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public abstract class BaseBenchmark {

	protected static final int BUFFER_SIZE = 1024;
	
    protected Map<String, Object> getContext() {
        Map<String, Object> context = new HashMap<>();
        context.put("items", Stock.dummyItems());
        return context;
    }
    
    protected final InputStream getResourceAsStream(String resourcePath) {
    	return BaseBenchmark.class.getResourceAsStream(resourcePath);
    }
    protected final URL getResource(String resourcePath) {
    	return BaseBenchmark.class.getResource(resourcePath);
    }
    
    protected final String getResouceContent(String resourcePath) throws IOException {
    	String tpl = IOUtils.readToString(new InputStreamReader(getResourceAsStream( resourcePath)));
    	return tpl;
    }
    public abstract void setup() throws Exception ;
    
    public abstract String benchmark() throws Exception ;
}
