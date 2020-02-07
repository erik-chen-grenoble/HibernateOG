package com.hello.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Foo {
		
	@Id
    private String id;
    
	private String name;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true, mappedBy = "foo", targetEntity = Bar.class)
    private List<Bar> bars = new ArrayList<>();

	public List<Bar> getBars() {
		return bars;
	}

	public void setBars(List<Bar> bars) {
		this.bars = bars;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		
		if(bars.size() == 0) {
			return "Foo : " + name +" --- NO Bar" ;
		}
		return "Foo : " + name +" --- Bars["+bars.get(0).getName()+"]";
	}


	public void addBar(String name) {
		Bar bar = new Bar();
		bar.setName("Bar_"+name);
		String id_foo = "Foo_" + name + "_id";
		String id_bar = "Bar_" + id_foo;
		bar.setId(id_bar);
		bars.add(bar);
		bar.setFoo(this);
	}
}
