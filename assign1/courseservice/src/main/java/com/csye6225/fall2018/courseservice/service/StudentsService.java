package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Professor;
import com.csye6225.fall2018.courseservice.datamodel.Student;

public class StudentsService {
static HashMap<Integer, Student> student_Map = InMemoryDatabase.getStudentDB();
	
	// Getting a list of all Student 
	// GET "..webapi/Students"
	public List<Student> getAllStudents() {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student student : student_Map.values()) {
			list.add(student);
		}
		System.out.println("Use getAllStudents()");
		return list ;
	}

	
	public Student addStudent(Student student) {	
		int nextAvailableId = student_Map.size() + 1;
		student.setStudentId(nextAvailableId);
		student_Map.put(nextAvailableId, student);
		return student;
	}
	
	// Getting One Student
	public Student getStudent(int studentId) {
		return student_Map.get(studentId);
	}
	
	// Deleting a Student
	public Student deleteStudent(int studentId) {
		Student deletedstudentDetails = student_Map.get(studentId);
		student_Map.remove(studentId);
		return deletedstudentDetails;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(int studentId, Student student) {
		student.setStudentId(studentId);
		student_Map.put(studentId, student) ;	
		return student;
	}
	
	// Get professors in a department 
		public List<Student> getStudentsByProgram(String program) {	
			//Getting the list
			ArrayList<Student> list = new ArrayList<>();
			for (Student student : student_Map.values()) {
				if (student.getProgramName().equals(program)) {
					list.add(student);
				}
			}
			return list ;
		}
	
}
