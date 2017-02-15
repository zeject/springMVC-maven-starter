package com.test;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PojoBean {

	@NotNull
	@Size(min = 2, max = 14)
	private String name;

	@Min(value = 20, message = "不能小于20")
	private int age;

	@AssertTrue
	private boolean type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PojoBean [name=" + name + ", age=" + age + ", type=" + type + "]";
	}

}
