package com.csye6225.fall2018.courseservice.datamodel;


import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;


public class DynamoDBConnector {
	
	
	static AmazonDynamoDB dynamoDB;

    public void init() {
        if (dynamoDB == null) {
            InstanceProfileCredentialsProvider credentialsProvider = new InstanceProfileCredentialsProvider(false);
            //ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            credentialsProvider.getCredentials();
            dynamoDB = AmazonDynamoDBClientBuilder
                        .standard()
                        .withCredentials(credentialsProvider)
                        .withRegion("us-east-2")
                        .build();
            System.out.println("created the client");
        }

    }

//    public AmazonDynamoDB getConnector() {
//        return dynamoDB;
//    }

    public void main(String[] args) throws Exception {
//        DynamoDBConnector.init();
//        String tableName = "Professor";
//
//        GetItemRequest getItemRequest = new GetItemRequest();
//
//        // key that are you looking for: student_id with value 123
//        Map<String, AttributeValue> itemToFetch = new HashMap<>();
//        itemToFetch.put("Id", new AttributeValue().withN("0"));
//
//        getItemRequest.setKey(itemToFetch);
//
//        // the table that we are looking at
//        getItemRequest.setTableName(tableName);
//        GetItemResult getItemResult = dynamoDB.getItem(getItemRequest);
//        System.out.println("GetItemResult:" + getItemResult);
    }
// 
	public AmazonDynamoDB getClient() {
		return dynamoDB;
  
	}
	
	
	
	
	
}