package com.csye6225.fall2018.courseservice.datamodel;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;


public class DynamoDBConnector {

//	static AmazonDynamoDB dynamoDb;
// 
//	public void init() {
//	  if (dynamoDb == null) {
//		   @SuppressWarnings("deprecation")
//		InstanceProfileCredentialsProvider credentialsProvider = new InstanceProfileCredentialsProvider();
//		   credentialsProvider.getCredentials();
//		   
//		   dynamoDb = AmazonDynamoDBClientBuilder
//				   .standard()
//				   .withCredentials(credentialsProvider)
//				   .withRegion("us-east-2")
//				   .build();
//		   
//		   System.out.println("I created the client");
//	     
//	  }
//	}
	
	
	static AmazonDynamoDB dynamoDB;

    public void init() {
        if (dynamoDB == null) {
            InstanceProfileCredentialsProvider credentialsProvider = new InstanceProfileCredentialsProvider(false);
//            ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
            credentialsProvider.getCredentials();
            dynamoDB = AmazonDynamoDBClientBuilder
                        .standard()
                        .withCredentials(credentialsProvider)
                        .withRegion("us-east-2")
                        .build();
            System.out.println("created the client");
        }

    }

    public AmazonDynamoDB getConnector() {
        return dynamoDB;
    }

//    public static void main(String[] args) throws Exception {
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
//    }
//  
    
	public AmazonDynamoDB getClient() {
		return dynamoDB;
	}
	
	
	
	
}