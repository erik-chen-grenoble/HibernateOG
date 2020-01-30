package com.hello.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;



@Alternative
public class DynamicPersistentModel implements PersistentModel {

	private List<Class<?>> entityClasses = new ArrayList<>();

	@Override
	public void setEntityClasses(List<Class<?>> entityClasses) {
		this.entityClasses = entityClasses;
	}

	@Override
	public List<Class<?>> getEntityClasses() {
		return entityClasses;
	}


}
