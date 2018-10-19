package com.csye6225.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private int studentId;
	private List<Integer> coursesEnrolled;
	private String programName;
	
	public Student() {
		
	}
	
	public Student(String name, int studentId, List<Integer> coursesEnrolled, String programName) {
		this.name = name;
		this.studentId = studentId;
		this.coursesEnrolled = new ArrayList<> ();
		this.programName = programName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public List<Integer> getCoursesEnrolled() {
		return coursesEnrolled;
	}


	public void setCoursesEnrolled(List<Integer> coursesEnrolled) {
		this.coursesEnrolled = coursesEnrolled;
	}


	public String getProgramName() {
		return programName;
	}


	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	
	
	
	
}
