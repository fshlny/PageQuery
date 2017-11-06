package com.fsh.collections;

public class Student {
	private int id;
	private int age;
	private String name;
	private String simplePY;
	private String className;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSimplePY() {
		return simplePY;
	}
	public void setSimplePY(String simplePY) {
		this.simplePY = simplePY;
	}
	public Student(int id, int age, String name, String simplePY, String className) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.simplePY = simplePY;
		this.className = className;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", age=" + age + ", name=" + name + ", simplePY=" + simplePY + ", className="
				+ className + "]";
	}
	
}
