package com.ef.load.repository;

public interface Repository<Entity, ID> {

	public Entity createEntity(Entity entity);

	public Entity retrieveEntity(Entity id);

	public void deleteEntity(ID id);

	public void updateEntity(Entity entity);

}
