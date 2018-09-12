package com.ef.extract;

import static com.ef.Constant.DAILY;
import static com.ef.Constant.INPUT_DATE_FORMAT;
import static com.ef.Constant.LOG_DATE_FORMAT;
import static com.ef.Constant.HOURLY;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeParserImpl {

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	public DateTimeParserImpl(String date, String duration) {
		startDateTime = convertStringToLocalDateTime(date, INPUT_DATE_FORMAT);
		this.calculateEndDate(duration);
	}

	private void calculateEndDate(String duration) {
		if (HOURLY.equalsIgnoreCase(duration)) {
			endDateTime = startDateTime.plusHours(1);
		} else if (DAILY.equalsIgnoreCase(duration)) {
			endDateTime = startDateTime.plusDays(24);
		}
	}

	private LocalDateTime convertStringToLocalDateTime(String date, String format) {
		Date parsedDate;
		java.text.SimpleDateFormat simpleFormat = new java.text.SimpleDateFormat(format);
		try {
			parsedDate = simpleFormat.parse(date);
		} catch (ParseException exception) {
			exception.printStackTrace();
			throw new IllegalArgumentException("Illegate date argument", exception);
		}
		return LocalDateTime.ofInstant(parsedDate.toInstant(), ZoneId.systemDefault());
	}

	public boolean isDateTimeInRange(String dateTimeString) {

		LocalDateTime localDateTime = this.convertStringToLocalDateTime(dateTimeString, LOG_DATE_FORMAT);
		if (startDateTime.isEqual(localDateTime)
				|| (localDateTime.isAfter(startDateTime) && localDateTime.isBefore(endDateTime))) {
			return true;
		}

		return false;
	}
}
