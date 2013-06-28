package com.projectplace.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectplace.api.http.Get;
import com.projectplace.api.http.Post;
import com.projectplace.oauth.Consumer;

public abstract class BaseApi implements API {
	private final Consumer consumer;
	public static final String API_BASE_PATH = "https://api.projectplace.com";

	BaseApi(Consumer consumer) {
    	this.consumer = consumer;
    }
    
    protected final Consumer getConsumer() {
    	return consumer;
    }
    
    private final ObjectMapper mapper() {
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    	return mapper;
    }
    
    private final <T> T mapStreamToClass(InputStream stream, Class<T> cls) {
    	ObjectMapper mapper = mapper();
    	try {
    		return mapper.readValue(stream, cls);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	} finally {
    		if (stream != null) {
    			try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
    		}
    	}
    }
    private final <T> List<T> mapStreamToCollection(InputStream stream, Class<T> cls) {
    	ObjectMapper mapper = mapper();
    	List<T> lst = new ArrayList<T>();
    	try {
    		JsonFactory factory = new JsonFactory();
        	JsonParser jp = factory.createParser(stream);
        	jp.nextToken();
        	
        	while (jp.nextToken() == JsonToken.START_OBJECT) {
        		T obj = mapper.readValue(jp, cls);
        		lst.add(obj);
        	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (stream != null) {
    			try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
    		}
    	}
    	return lst;
    }
    
    protected <T> T get(String url, Class<T> cls) {
    	return mapStreamToClass(new Get(url).asStream(consumer), cls);
    }
    
    protected <T> T post(String path, Map<String, String> params, Class<T> cls) {
    	return mapStreamToClass(new Post(path, params).asStream(consumer), cls);
    }
    

    protected <T> List<T> getAsList(String url, Class<T> cls) {
    	return mapStreamToCollection(new Get(url).asStream(consumer), cls);
    }
}
