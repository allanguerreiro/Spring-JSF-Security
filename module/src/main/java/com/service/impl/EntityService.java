package com.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.model.dao.IEntityDAO;
import com.model.domain.Entity;
import com.service.IEntityService;

/**
 * 
 * Entity Service
 * 
 * @author
 * @version 1.0.0
 *
 */
@Named
@Transactional(readOnly = true)
public class EntityService implements IEntityService {

	@Inject
	IEntityDAO entityDAO;

	@Transactional(readOnly = false)
	public void addEntity(Entity entity) {
		getEntityDAO().addEntity(entity);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(Entity entity) {
		getEntityDAO().deleteEntity(entity);
	}

	@Transactional(readOnly = false)
	public void updateEntity(Entity entity) {
		getEntityDAO().updateEntity(entity);
	}

	public Entity getEntityById(int id) {
		return getEntityDAO().getEntity(id);
	}

	public List<Entity> getEntitys() {	
		return getEntityDAO().getEntities();
	}

	public IEntityDAO getEntityDAO() {
		return entityDAO;
	}

	public void setEntityDAO(IEntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
}
