package com.csye6225.fall2018.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.PredefinedClientConfigurations;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

public class SnsConnector {
	
	static AmazonSNS sns;
	
	public void init() {
		if (sns == null) {
			ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
			sns = AmazonSNSClientBuilder.standard()
					   .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
					   .withRegion(Regions.US_EAST_2).build();	
			
			System.out.println("created the client");
		}
	}
	
	public String createTopic(String topic) {
		//create a new SNS topic
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(topic);
		
		CreateTopicResult createTopicResult = sns.createTopic(createTopicRequest);
		return (createTopicResult + "").substring(11, (createTopicResult + "").length() - 1);
	}
	
	public void subscribeTopic(String topicArn, String emailId) {
		//subscribe to an SNS topic
		SubscribeRequest subRequest = new SubscribeRequest(topicArn, "email", emailId);
		sns.subscribe(subRequest);
		//get request id for SubscribeRequest from SNS metadata
		System.out.println("SubscribeRequest - " + sns.getCachedResponseMetadata(subRequest));
		System.out.println("Check your email and confirm subscription.");
	}
	
	public AmazonSNS getAmazonSNS() {	
		return sns;
	}
	

	
}
