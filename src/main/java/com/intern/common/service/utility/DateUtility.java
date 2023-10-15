package com.intern.common.service.utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtility {

	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public static Timestamp asTimeStamp(LocalDateTime localDateTime) {
		return Timestamp.valueOf(localDateTime);
	}
}
