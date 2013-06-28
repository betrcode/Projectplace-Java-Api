package com.projectplace.api.http;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.projectplace.api.API;
import com.projectplace.api.Volatile;

abstract class Base implements Streamable {
	private final static Logger LOGGER = Logger.getLogger(Base.class.getName());
	protected static final String API_BASE_PATH = "https://api.projectplace.com";
	protected final URL url;
	
	Base(String path) {
		url = asUrl(path);
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement callee = elements[4];
		String className = callee.getClassName();
		String methodName = callee.getMethodName();
    	try {
    		@SuppressWarnings("unchecked")
    		Class<? extends API> calleeCls = (Class<? extends API>) Class.forName(className);
    		for (Method m: calleeCls.getMethods()) {
    			if (m.getName() == methodName && m.isAnnotationPresent(Volatile.class)) {
    				LOGGER.log(Level.WARNING, "You are making a request to a non-standard API: " + methodName);
    			}
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}

	}
	
	protected final URL asUrl(String path) {
    	URL url = null;
    	try {
    		if (path.startsWith("http")) {
    			url = new URL(path);	
    		} else {
    			url = new URL(API_BASE_PATH + path);
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return url;
    }
}
