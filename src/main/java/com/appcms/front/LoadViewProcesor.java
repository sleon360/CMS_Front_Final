package com.appcms.front;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class LoadViewProcesor extends AbstractProcessorDialect {
	 public LoadViewProcesor() {
	        super(
	                "View Dialect",    // Dialect name
	                "view",            //  prefix (view:*)
	                1);              // Dialect precedence
	    }

	    public Set<IProcessor> getProcessors(final String dialectPrefix) {
	        final Set<IProcessor> processors = new HashSet<IProcessor>();
	        processors.add(new LoadViewTagProcesor(dialectPrefix));
	        return processors;
	    }
}
