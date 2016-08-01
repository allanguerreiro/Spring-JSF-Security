package com.model.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.dao.IEntityDAO;
import com.model.domain.Entity;

/**
 * 
 * Entity DAO
 * 
 * @author
 * @version 1.0.0
 * 
 */
@Named
@Slf4j
public class EntityDAO implements IEntityDAO {
	@Inject
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEntity(Entity entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(entity);
		log.info("Save a entity!");
		trans.commit();
	}

	public void deleteEntity(Entity entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.delete(entity);
		log.info("Delete a entity!");
		trans.commit();
	}

	public void updateEntity(Entity entity) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.update(entity);
		log.info("Update a entity!");
		trans.commit();
	}

	public Entity getEntity(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		List<?> list = session
				.createQuery("from Entity where id=?").setParameter(0, id)
				.list();
		
		trans.commit();
		log.info("Select a entity by id!");
		return (Entity) list.get(0);
	}

	public List<Entity> getEntities() {
		Session session = getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Entity> list = (List<Entity>) session.createQuery("from Entity").list();
		
		trans.commit();
		log.info("Select all entities!");
		return list;
	}

}
