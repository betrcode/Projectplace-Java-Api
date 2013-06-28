package com.projectplace.api.http;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.projectplace.oauth.Consumer;

public class Post extends Base {
	private final String postParams;

	public Post(String path, Map<String, String> params) {
		super(path);
		
		StringBuilder paramBuilder = new StringBuilder();
		for(String key: params.keySet()) {
			
			try {
				paramBuilder.append(String.format("%s=%s&", key, URLEncoder.encode(params.get(key), "UTF-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		postParams = paramBuilder.toString();
	}
	
	@Override
	public InputStream asStream(Consumer consumer) {
		HttpURLConnection conn = null;

		try {
			String url = this.url.toString() + "?" + postParams;
			System.out.println(url);
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", "0");
			conn.setUseCaches (false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			consumer.sign(conn);

			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
			outStream.flush();
			outStream.close();
			return conn.getInputStream();
			
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
