package com.hello;

import java.util.List;

public interface PersistentModel {

	void setEntityClasses(List<Class<?>> entityClasses);

	List<Class<?>> getEntityClasses();
	
}
