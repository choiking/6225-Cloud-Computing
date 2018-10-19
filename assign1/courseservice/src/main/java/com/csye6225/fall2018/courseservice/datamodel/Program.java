package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

public class Program {
	private int programId;
	private String name;
	private List<Integer> listOfCourses;
	private List<Integer> enrolledStudents;
	private List<Integer> listOfProfessors;
	private String beginTerm;
	
	public Program() {
		
	}

	public Program(String name, List<Integer> listOfCourses, List<Integer> enrolledStudents,
			List<Integer> listOfProfessors, String beginTerm, int programId) {
		this.name = name;
		this.listOfCourses = listOfCourses;
		this.enrolledStudents = enrolledStudents;
		this.listOfProfessors = listOfProfessors;
		this.beginTerm = beginTerm; 
		this.programId = programId;
	}
    
	
	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public String getBeginTerm() {
		return beginTerm;
	}

	public void setBeginTerm(String beginTerm) {
		this.beginTerm = beginTerm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getListOfCourses() {
		return listOfCourses;
	}

	public void setListOfCourses(List<Integer> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}

	public List<Integer> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<Integer> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public List<Integer> getListOfProfessors() {
		return listOfProfessors;
	}

	public void setListOfProfessors(List<Integer> listOfProfessors) {
		this.listOfProfessors = listOfProfessors;
	}
}
