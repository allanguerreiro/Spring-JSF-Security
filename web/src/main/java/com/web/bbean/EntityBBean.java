package com.web.bbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;

import com.model.domain.Entity;
import com.service.IEntityService;

/**
 * 
 * Entity Backed Bean
 * 
 * @author
 * @version 1.0.0
 *
 */

@Named("entityBBean")
@Scope("session")
@Slf4j
public class EntityBBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Getter @Setter
	@Inject
	private IEntityService entityService;

	@Getter @Setter
	private int id;
	@Getter @Setter
	private String attribute;
	@Setter
	private List<Entity> entityList;

	public void addEntity() {
		try {
			Entity entity = new Entity();
			entity.setId(getId());
			entity.setAttribute(getAttribute());
			getEntityService().addEntity(entity);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Message: "));
			log.info("Add a entity!");
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "D'oh!", "Message: ")); 
		} 	
		
	}

	public void reset() {
		this.setId(0);
		this.setAttribute("");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Clean!", "Message: "));
	}

	public List<Entity> getEntityList() {
		entityList = new ArrayList<Entity>();
		entityList.addAll(getEntityService().getEntitys());
		log.info("List a entity!");
		return entityList;
	}
 }