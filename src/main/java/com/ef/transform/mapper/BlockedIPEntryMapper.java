package com.ef.transform.mapper;

import static com.ef.Constant.THREASHOLD_REACHED;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ef.entity.BlockedIPEntry;

public class BlockedIPEntryMapper {


	public List<BlockedIPEntry> map(Map<String, Integer> entries) {
		List<BlockedIPEntry> list = new ArrayList<BlockedIPEntry>();
		for (Map.Entry<String, Integer> entry : entries.entrySet()) {
			BlockedIPEntry blockedIPEntry = new BlockedIPEntry(entry.getKey(), entry.getValue(), THREASHOLD_REACHED);
			list.add(blockedIPEntry);
		}
		return list;
	}

}
