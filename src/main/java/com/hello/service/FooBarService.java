/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hello.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.hello.model.Bar;
import com.hello.model.Foo;

/**
 * A simple CDI service which is able to say hello to someone
 *
 * @author Pete Muir
 *
 */
public class FooBarService {

	@Inject
	protected EntityManager entityManager;

	@Transactional
	public String persisitFoo(String name) {

		System.out.println("##################### PERSIST FOO (with one BAR)---> " + name);

		Foo foo = new Foo();
		String id_foo = "Foo_" + name + "_id";
		foo.setId(id_foo);
		foo.setName("Foo_"+name);
		
		Bar bar = new Bar();
		bar.setName("Bar_"+name);
		
		String id_bar = "Bar_" + id_foo;
		bar.setId(id_bar);
		List<Bar> bars = new ArrayList<>();
		bars.add(bar);
		bar.setFoo(foo);
		
		
		foo.setBars(bars);
		

		entityManager.persist(foo);
		entityManager.flush();

		
		Foo result = entityManager.find(Foo.class, id_foo);
		return result.toString();
	}
	
	@Transactional
	public void persisitbar(Bar bar) {
		entityManager.persist(bar);
		entityManager.flush();
	}

	@Transactional
	public String getBar(String name) {

		System.out.println("##################### GET BAR---> " + name);

		String id_foo = "Foo_" + name + "_id";
		String id_bar = "Bar_" + id_foo;
		
		// build the EntityManagerFactory as you would build in in Hibernate ORM

		Bar result = entityManager.find(Bar.class, id_bar);
		entityManager.flush();

		return result.toString();
	}

	@Transactional
	public String getFoo(String name) {

		System.out.println("##################### GET FOO---> " + name);

		String id_foo = "Foo_" + name + "_id";

		// build the EntityManagerFactory as you would build in in Hibernate ORM

		Foo result = entityManager.find(Foo.class, id_foo);
		entityManager.flush();

		return result.toString();
	}

}
