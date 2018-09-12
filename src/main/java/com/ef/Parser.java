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

	private static final String LOG_FILE_PATH = "/Users/eifwhno/eclipse-workspace/Solution/files/access.log";


	public static void main(String[] args) {
		

		ProgramArgumentExtractor programArgumentExtractor = new ProgramArgumentExtractor();
		ProgramArgument programArgument= programArgumentExtractor.extractProgramArguments(args);
		
		Map<String, Integer> filteredData = extractDataFromLog(programArgument);
		
		List<BlockedIPEntry> blockedIpEntries = new BlockedIPEntryMapper().map(filteredData);

		Service<BlockedIPEntry, Long> blockedIPService = initiateServiceUsingDependencyInjection();

		blockedIPService.createEntities(blockedIpEntries);

	}


	private static Map<String, Integer> extractDataFromLog(ProgramArgument programArgument) {
		DateTimeParserImpl dateTimeParser = new DateTimeParserImpl(programArgument.getStartDate(), programArgument.getDuration());
		LogContentParser logExtractor = new LogContentParser(dateTimeParser);
		LogFileParser logFileParser = new LogFileParser(logExtractor);
		logFileParser.parse(LOG_FILE_PATH);

		Map<String, Integer> filteredData = new DataFilter().filterData(programArgument.getThreshold(), logExtractor);
		return filteredData;
	}


	public static Service<BlockedIPEntry, Long> initiateServiceUsingDependencyInjection() {

		JDBCHelper jdbcHelper = new MySQLHelper();
		BlockedIPRepository repository = new BlockedIPRepository();
		repository.setConnection(jdbcHelper.getConnection());
		BlockedIPService blockedIPService = new BlockedIPService();
		blockedIPService.setRepository(repository);

		return blockedIPService;

	}
	
}
