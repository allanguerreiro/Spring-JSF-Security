package com.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity(name="Entity")
@Table(name = "ENTITY")
public class Entity {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@Getter
	@Setter
	private int id;
	@Column(name="attribute")
	@Getter @Setter
	private String attribute;
	
	public Entity(){}
	
	public Entity(int id, String attribute) {
		super();
		this.id = id;
		this.attribute = attribute;
	}
}
