package com.projectplace.api.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import com.projectplace.oauth.Consumer;

public class Get extends Base {

	public Get(String path) {
		super(path);
	}
	
	@Override
	public InputStream asStream(Consumer consumer) {
		HttpURLConnection conn = null;
    	try {
        	conn = (HttpURLConnection) this.url.openConnection();
        	consumer.sign(conn);
        	conn.connect();
        	return conn.getInputStream();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
	}
}
