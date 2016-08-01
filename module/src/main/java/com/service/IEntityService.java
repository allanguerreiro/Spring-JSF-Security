package com.service;

import java.util.List;

import com.model.domain.Entity;
/**
 * 
 * Entity Service Interface
 * 
 * @author
 * @version 1.0.0
 *
 */

public interface IEntityService {
	
	public void addEntity(Entity entity);
	
	public void updateEntity(Entity entity);

	public void deleteEntity(Entity entity);
	
	public Entity getEntityById(int id);
	
	public List<Entity> getEntitys();
}
