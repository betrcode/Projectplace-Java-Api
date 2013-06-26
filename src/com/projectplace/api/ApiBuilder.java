package com.projectplace.api;

import java.lang.reflect.Constructor;

import com.projectplace.oauth.Consumer;

public class ApiBuilder {
	
	private final Consumer consumer;
	
	public ApiBuilder(Consumer consumer) {
		this.consumer = consumer;
	}
	
	public <T extends BaseApi> T service(Class<T> cls) {
		try {
			Constructor<T> c = cls.getDeclaredConstructor(consumer.getClass());
			return c.newInstance(new Object[] {consumer});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ApiBuilder useConsumer(Consumer consumer) {
		return new ApiBuilder(consumer);
	}
}
