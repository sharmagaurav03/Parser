package com.ef.filter;

import java.util.Map;
import java.util.stream.Collectors;

import com.ef.extract.LogContentParser;

public class DataFilter {

	public Map<String, Integer> filterData(int threshold, LogContentParser logExtractor) {
		Map<String, Integer> filteredData = (Map<String, Integer>) logExtractor.getData().entrySet().stream()
				.filter(map -> map.getValue() >= threshold)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return filteredData;
	}

}
