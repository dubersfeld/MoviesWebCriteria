package com.dub.spring.site;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCorrect {
	
	private static SimpleDateFormat sdf 
							= new SimpleDateFormat("MM-dd-yyy Z");
	
	public static Date correctDate(Date date) throws ParseException {
		
		StringBuffer buf = new StringBuffer();
		
		sdf.format(date, buf, new FieldPosition(0));
						
		buf.replace(11, buf.length(), "+0000");
						
		return sdf.parse(buf.toString());
	}
}