package com.ef;

import java.util.List;
import java.util.Map;

import com.ef.db.JDBCHelper;
import com.ef.db.MySQLHelper;
import com.ef.entity.BlockedIPEntry;
import com.ef.entity.ProgramArgument;
import com.ef.extract.DateTimeParserImpl;
import com.ef.extract.LogContentParser;
import com.ef.extract.LogFileParser;
import com.ef.extract.ProgramArgumentExtractor;
import com.ef.filter.DataFilter;
import com.ef.load.repository.BlockedIPRepository;
import com.ef.load.service.BlockedIPService;
import com.ef.load.service.Service;
import com.ef.transform.mapper.BlockedIPEntryMapper;

/**
 * This is the entry point of the code.
 *
 */
public class Parser {

	public static void main(String[] args) {

		Parser parser = new Parser();

		// Extract provided program argument.
		ProgramArgumentExtractor programArgumentExtractor = new ProgramArgumentExtractor();
		ProgramArgument programArgument = programArgumentExtractor.extractProgramArguments(args);

		// Get the extracted data.
		Map<String, Integer> filteredData = parser.extractDataFromLog(programArgument);

		// Get the list of blocked ip entries to be persisted in db.
		List<BlockedIPEntry> blockedIpEntries = new BlockedIPEntryMapper().map(filteredData);

		// Initiate the object structure using dependency injection. Spring Dependenency
		// injection is preferred.
		Service<BlockedIPEntry, Long> blockedIPService = parser.initiateServiceUsingDependencyInjection();

		//Persist the logs data in DB.
		blockedIPService.createEntities(blockedIpEntries);

	}

	private Map<String, Integer> extractDataFromLog(ProgramArgument programArgument) {
		DateTimeParserImpl dateTimeParser = new DateTimeParserImpl(programArgument.getStartDate(),
				programArgument.getDuration());
		LogContentParser logExtractor = new LogContentParser(dateTimeParser);
		LogFileParser logFileParser = new LogFileParser(logExtractor);
		logFileParser.parse(programArgument.getAccesslog());

		Map<String, Integer> filteredData = new DataFilter().filterData(programArgument.getThreshold(), logExtractor);
		return filteredData;
	}

	public Service<BlockedIPEntry, Long> initiateServiceUsingDependencyInjection() {

		JDBCHelper jdbcHelper = new MySQLHelper();
		BlockedIPRepository repository = new BlockedIPRepository();
		repository.setConnection(jdbcHelper.getConnection());
		BlockedIPService blockedIPService = new BlockedIPService();
		blockedIPService.setRepository(repository);

		return blockedIPService;

	}

}
