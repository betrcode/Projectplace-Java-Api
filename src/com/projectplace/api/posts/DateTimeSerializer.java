package com.projectplace.api.posts;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

class DateTimeSerializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser val, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			return fmt.parse(val.getValueAsString());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
