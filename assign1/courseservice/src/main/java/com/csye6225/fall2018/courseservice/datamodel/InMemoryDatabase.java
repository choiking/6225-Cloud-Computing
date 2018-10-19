package com.csye6225.fall2018.courseservice.datamodel;

import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<Integer, Professor> professorDB = new HashMap<> ();
	private static HashMap<Integer, Course> courseDB = new HashMap<> ();
	private static HashMap<Integer, Student> studentDB = new HashMap<> ();
	private static HashMap<Integer, Program> programDB = new HashMap<> ();

	public static HashMap<Integer, Professor> getProfessorDB() {
		return professorDB;
	}
	public static HashMap<Integer, Course> getCourseDB() {
		return courseDB;
	}
	public static HashMap<Integer, Student> getStudentDB() {
		return studentDB;
	}
	public static HashMap<Integer, Program> getProgramDB() {
		return programDB;
	}
	
}
