package com.hibernate.hql.HqlDemo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student1 
{
	@Id
	private int rollno;
	private String name;
	private int marks;
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	@Override
	public String toString() {
		return "Student1 [rollno=" + rollno + ", name=" + name + ", marks=" + marks + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	

}
