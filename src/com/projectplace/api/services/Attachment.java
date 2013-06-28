package com.projectplace.api.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectplace.api.APISerializable;

public final class Attachment extends APISerializable {

	public String mimetype, name;
	@JsonProperty(value="url")
	public String path;
	public int id, size;
	
}
