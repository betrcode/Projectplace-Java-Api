package com.projectplace.api;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectplace.oauth.Consumer;

class BaseApi {
	private final Consumer consumer;
	protected static final String API_BASE_PATH = "https://api.projectplace.com/1";
    BaseApi(Consumer consumer) {
    	this.consumer = consumer;
    }
    
    protected final Consumer getConsumer() {
    	return consumer;
    }
    
    private final InputStream openStream(String url) {
    	try {
    		URL profile = new URL(API_BASE_PATH + url);
        	HttpURLConnection conn = (HttpURLConnection) profile.openConnection();
        	
        	consumer.sign(conn);
        	conn.connect();
        	return conn.getInputStream();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    private final ObjectMapper mapper() {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    	return mapper;
    }
    
    public <T> T get(String url, Class<T> cls) {
    	ObjectMapper mapper = mapper();
    	T obj = null;
		try {
			obj = mapper.readValue(openStream(url), cls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
    }
    
    public <T> List<T> getAsList(String url, Class<T> cls) {
    	List<T> lst = new ArrayList<T>();
    	try {
        	JsonFactory factory = new JsonFactory();
        	JsonParser jp = factory.createParser(openStream(url));
        	ObjectMapper mapper = mapper();
        	jp.nextToken();
        	
        	while (jp.nextToken() == JsonToken.START_OBJECT) {
        		T obj = mapper.readValue(jp, cls);
        		lst.add(obj);
        	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return lst;
    }
}
