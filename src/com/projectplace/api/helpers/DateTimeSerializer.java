package com.projectplace.api.helpers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateTimeSerializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser val, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		SimpleDateFormat[] formats = {
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ")
		};
		for (SimpleDateFormat fmt: formats) {
			try {
				return fmt.parse(val.getValueAsString());
			} catch (ParseException e) {
				continue;
			}
		}
		return null;
	}

}
