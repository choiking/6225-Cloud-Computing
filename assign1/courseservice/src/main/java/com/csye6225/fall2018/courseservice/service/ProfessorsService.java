package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Professor;

public class ProfessorsService {
	static HashMap<Integer, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		System.out.println("Use getAllProfessors()");
		return list;
	}
	
	public Professor addProfessor(Professor prof) {	
		int nextAvailableId = prof_Map.size() + 1;
		prof.setProfessorId(nextAvailableId);
		prof_Map.put(nextAvailableId, prof);
		System.out.println(prof.getProfessorId());
		return prof;
	}
	
	// Getting One Professor
	public Professor getProfessor(int profId) {
		return prof_Map.get(profId);
	}
	
	// Deleting a professor
	public Professor deleteProfessor(int profId) {
		Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(int profId, Professor prof) {	
		// Publishing New Values
		prof.setProfessorId(profId);
		prof_Map.put(profId, prof) ;
		return prof;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}
	
	
}
