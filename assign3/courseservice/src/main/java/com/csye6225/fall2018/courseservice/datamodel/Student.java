package com.csye6225.fall2018.courseservice.datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName="Student")
public class Student {
	private String Id;
	private String studentId;
	private String firstName;
	private String lastName;
	private String joiningDate;
	private String department;
	private List<String> registeredCourses;
	private String emailId;
	public Student() {
		
	}

	public Student(String Id, String studentId, String firstName, 
			String lastName, String joiningDate, String department, List<String> registeredCourses, String emailId) {
		super();
		this.Id = Id;
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joiningDate = joiningDate;
		this.department = department;
		this.registeredCourses = registeredCourses;
		this.emailId = emailId;
	}
    
	@DynamoDBAutoGeneratedKey
	@DynamoDBHashKey(attributeName="Id")
	public String getId() {
        return Id;
	}
	public void setId(String Id) {
        this.Id = Id;
	}
	   
	@DynamoDBIndexHashKey(attributeName="studentId", globalSecondaryIndexName = "studentIdIndex")
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@DynamoDBAttribute(attributeName="firstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@DynamoDBAttribute(attributeName="lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@DynamoDBAttribute(attributeName="joiningDate")
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
    
	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
    
	@DynamoDBAttribute(attributeName="registeredCourses")
	public List<String> getRegisteredCourses() {
		return registeredCourses;
	}
	public void setRegisteredCourses(List<String> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}
    
	
	@DynamoDBAttribute(attributeName="emailId")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	

	
	
	
    
	

	
}
