package com.ef.extract;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LogFileParser {
	
	private LogContentParser logExtractor;

	public LogFileParser(LogContentParser logExtractor) {
		this.logExtractor = logExtractor;
	}

	public void parse(String file) {
		FileInputStream inputStream = null;
		Scanner scanner = null;
		try {
			try {
				inputStream = new FileInputStream(file);
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
				throw new RuntimeException(exception);
			}
			scanner = new Scanner(inputStream);
			while (scanner.hasNextLine()) {
				String logLine = scanner.nextLine();
				logExtractor.parseLogLine(logLine);
			}
			if (scanner.ioException() != null) {
				throw new RuntimeException(scanner.ioException());
			}
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException exception) {
					exception.printStackTrace();
					throw new IllegalStateException(exception);
				}
			}
			if (scanner != null) {
				scanner.close();
			}
		}
	}

}
