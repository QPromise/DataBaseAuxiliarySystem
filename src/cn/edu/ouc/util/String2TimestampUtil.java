package cn.edu.ouc.util;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;

public class String2TimestampUtil implements Converter<String, Timestamp> {

	@Override
	public Timestamp convert(String arg0) {
		Timestamp timestamp = Timestamp.valueOf(arg0);
		return timestamp;
	}

}
