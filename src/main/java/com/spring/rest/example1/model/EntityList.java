package com.spring.rest.example1.model;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
public class EntityList<T> extends RepresentationModel<EntityList<T>> {
	private List<T> entityList;

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

}
