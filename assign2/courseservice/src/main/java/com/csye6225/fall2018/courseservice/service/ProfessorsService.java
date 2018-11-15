package com.csye6225.fall2018.courseservice.service;


import java.util.HashMap;
import java.util.List;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice.datamodel.DynamoDBConnector;
import com.csye6225.fall2018.courseservice.datamodel.Professor;

public class ProfessorsService {
	static DynamoDBConnector dynamoDB;
	DynamoDBMapper mapper; 
	

	public ProfessorsService() {
		dynamoDB = new DynamoDBConnector();
		dynamoDB.init();
		mapper = new DynamoDBMapper(dynamoDB.getClient());
	}
	
	
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		
		return mapper.scan(Professor.class, new DynamoDBScanExpression());
	}
	
	public Professor addProfessor(Professor prof) {	
        mapper.save(prof);
		return prof;
	}
	
	// Getting Professor by professorId
	public Professor getProfessor(String profId) {
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(profId));
		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor> ()
				.withIndexName("professorIdIndex")
				.withConsistentRead(false)
			    .withKeyConditionExpression("professorId = :v1")
			    .withExpressionAttributeValues(eav);
		List<Professor> res = mapper.query(Professor.class, queryExpression);
		if (res.isEmpty()) return new Professor();
		return res.get(0);
	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		ProfessorsService ps = new ProfessorsService();
		Professor p = ps.getProfessor(profId);
		mapper.delete(p);		
		return p;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) {	
		ProfessorsService ps = new ProfessorsService();
		Professor p = ps.getProfessor(profId);
		if (prof.getFirstName() != null)
			p.setFirstName(prof.getFirstName());
		if (prof.getLastName() != null)
			p.setLastName(prof.getLastName());
		if (prof.getDepartment() != null)
			p.setDepartment(prof.getDepartment());
		if (prof.getJoiningDate() != null)
			p.setJoiningDate(prof.getJoiningDate());
		mapper.save(p);
		return p;
	}
	
	// Get professors by department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(department));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("begins_with(department,:v1)")
			    .withExpressionAttributeValues(eav);	
		return mapper.scan(Professor.class, scanExpression);
	}
	
	
}
