package com.ef.extract;

import static com.ef.Constant.LOG_ENTRY_DELEMETER;

import java.util.HashMap;

public class LogContentParser implements ContentParser<String, Integer> {

	private final DateTimeParserImpl dateTimeParser;
	private final HashMap<String, Integer> logCountByIp;
	

	public LogContentParser(DateTimeParserImpl dateTimeParser) {
		this.dateTimeParser = dateTimeParser;
		logCountByIp = new HashMap<String, Integer>();
	}

	public void parseLogLine(String logLine) {
		String[] logParts = logLine.split(LOG_ENTRY_DELEMETER);
		boolean isLogTimeInRange = dateTimeParser.isDateTimeInRange(logParts[0]);
		if (isLogTimeInRange) {
			Integer tempCount = logCountByIp.get(logParts[1]);
			if (tempCount == null) {
				logCountByIp.put(logParts[1], 1);
			} else {
				logCountByIp.put(logParts[1], ++tempCount);
			}
		}

	}

	@Override
	public HashMap<String, Integer> getData() {
		return logCountByIp;
	}

}
