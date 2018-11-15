package com.csye6225.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice.datamodel.Program;

public class ProgramsService {
static HashMap<Integer, Program> program_Map = InMemoryDatabase.getProgramDB();
	
	// Getting a list of all Program 
	// GET "..webapi/Programs"
	public List<Program> getAllPrograms() {	
		//Getting the list
		ArrayList<Program> list = new ArrayList<>();
		for (Program program : program_Map.values()) {
			list.add(program);
		}
		System.out.println("Use getAllPrograms()");
		return list;
	}
	
	public Program addProgram(Program program) {	
		int nextAvailableId = program_Map.size() + 1;
		program.setProgramId((nextAvailableId));
		program_Map.put(nextAvailableId, program);
		return program;
	}
	
	// Getting One Program
	public Program getProgram(int programId) {
		return program_Map.get(programId);
	}
	
	
	public List<Program> getProgramByBeginTerm(String beginTerm) {
		//Getting the list
		ArrayList<Program> list = new ArrayList<>();
		for (Program program : program_Map.values()) {
			if (program.getBeginTerm().equals(beginTerm)) {
				list.add(program);
			}
		}
		return list ;
	}
	
	// Deleting a Program
	public Program deleteProgram(int programId) {
		Program deletedprogramDetails = program_Map.get(programId);
		program_Map.remove(programId);
		return deletedprogramDetails;
	}
	
	// Updating Program Info
	public Program updateProgramInformation(int programId, Program program) {	
		// Publishing New Values
		program.setProgramId(programId);
		program_Map.put(programId, program) ;
		return program;
	}
	
}
