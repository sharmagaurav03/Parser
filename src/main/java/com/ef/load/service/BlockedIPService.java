package com.ef.load.service;

import java.util.List;

import com.ef.entity.BlockedIPEntry;
import com.ef.load.repository.Repository;

public class BlockedIPService implements Service<BlockedIPEntry, Long> {

	private Repository<BlockedIPEntry, Long> repository;

	@Override
	public BlockedIPEntry createEntity(BlockedIPEntry entity) {
		return repository.createEntity(entity);
	}

	@Override
	public List<BlockedIPEntry> createEntities(List<BlockedIPEntry> entities) {
		for (BlockedIPEntry blockedIPEntry : entities) {
			repository.createEntity(blockedIPEntry);
		}
		return entities;
	}

	@Override
	public BlockedIPEntry retrieveEntity(BlockedIPEntry id) {
		throw new UnsupportedOperationException("Operation not supported.");
	}

	@Override
	public void deleteEntity(Long id) {
		throw new UnsupportedOperationException("Operation not supported.");

	}

	@Override
	public void updateEntity(BlockedIPEntry entity) {
		throw new UnsupportedOperationException("Operation not supported.");

	}

	public Repository<BlockedIPEntry, Long> getRepository() {
		return repository;
	}

	public void setRepository(Repository<BlockedIPEntry, Long> repository) {
		this.repository = repository;
	}

}
