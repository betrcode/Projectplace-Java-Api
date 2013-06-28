package com.projectplace.api.http;

import java.io.InputStream;

import com.projectplace.oauth.Consumer;

public interface Streamable {

	InputStream asStream(Consumer consumer);
}
