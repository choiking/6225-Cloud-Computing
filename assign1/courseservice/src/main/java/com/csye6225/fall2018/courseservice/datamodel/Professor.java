package com.csye6225.fall2018.courseservice.datamodel;


public class Professor {
	private String firstName;
	private String department;
	private int professorId;
	private String joiningDate;
	
	public Professor() {
		System.out.println("use empty constructor");	
	}
	
	public Professor(int professorId, String firstName, String department, String joiningDate) {
		this.professorId = professorId;
		this.firstName = firstName;
		this.department = department;
		this.joiningDate = joiningDate;
	}
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	public int getProfessorId() {
		System.out.println("use getProfessorId()");
		return professorId;
	}
	
	public void setProfessorId(int professorId) {
		System.out.println("use setProfessorId()");
		this.professorId = professorId;
	}
	
	public String getJoiningDate() {
		return joiningDate;
	}
	
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
}
