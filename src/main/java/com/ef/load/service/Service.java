package com.ef.load.service;

import java.util.List;

public interface Service<Entity, ID> {

	public Entity createEntity(Entity entity);

	public List<Entity> createEntities(List<Entity> entities);

	public Entity retrieveEntity(Entity id);

	public void deleteEntity(ID id);

	public void updateEntity(Entity entity);

}
