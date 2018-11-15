package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

public class Course {
	private int courseId;
	private String name;
	private String board;
	private List<Integer> enrolledStudents;
	private String associatedProfessor;
	private int teachingAssitantId;
//	// Comment out list of lectures
//	private List<Lecture> lectures;
	
	
	public Course() {
		
	}
	
	public Course(int courseId, String name, String board, 
			List<Integer> enrolledStudents, 
			String associatedProfessor,
		int teachingAssitantId) {
	this.courseId = courseId;
	this.name = name;
	this.board = board;
	this.enrolledStudents = enrolledStudents;
	this.associatedProfessor = associatedProfessor;
	this.teachingAssitantId = teachingAssitantId;
}
	
	public List<Integer> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<Integer> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBoard() {
		return board;
	}
	
	public void setBoard(String board) {
		this.board = board;
	}
	
	public String getAssociatedProfessor() {
		return associatedProfessor;
	}
	public void setAssociatedProfessor(String associatedProfessor) {
		this.associatedProfessor = associatedProfessor;
	}
	public int getTeachingAssitantId() {
		return teachingAssitantId;
	}
	public void setTeachingAssitant(int teachingAssitantId) {
		this.teachingAssitantId = teachingAssitantId;
	}
	
	
	
	
	
	
	
	
	
}
