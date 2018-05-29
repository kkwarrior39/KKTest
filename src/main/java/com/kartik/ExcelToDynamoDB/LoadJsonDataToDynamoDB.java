	package com.kartik.ExcelToDynamoDB;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonObject;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
/*import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;*/
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
/*import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;*/
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoadJsonDataToDynamoDB {
	
	public static AWSCredentials awsCredentials() {
		  return new BasicAWSCredentials("AKIAJZ3LGLWZSAXV3NZQ", "3goTCSDermrBvjP3X10yoXKK1Y+BKRgi858QfQP/");
		}

		
		public static AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials) {
			Regions re = Regions.AP_SOUTHEAST_1;
			AmazonDynamoDBClient client = new AmazonDynamoDBClient(awsCredentials).withRegion(re).withEndpoint("http://dynamodb.ap-southeast-1.amazonaws.com");
		  /*if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
		    amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
		  }*/
		  return client;
		}
	
		public DynamoDB dynamoDB(AmazonDynamoDB amazonDynamoDB) {
			  return new DynamoDB(amazonDynamoDB);
			}

	public static void jsonToDB() throws JsonParseException, IOException {

		/*@SuppressWarnings("deprecation")
		AmazonDynamoDBClient client = new AmazonDynamoDBClient() 
		         .withEndpoint("http://localhost:8000");*/

		LoadJsonDataToDynamoDB ltd = new LoadJsonDataToDynamoDB();
		DynamoDB dynamoDB = ltd.dynamoDB(amazonDynamoDB(awsCredentials()));

        /*Table table = dynamoDB.getTable("Music");*/
        /*Table table = dynamoDB.getTable("TestTable3");*/
        Table table = dynamoDB.getTable("TestData");
        
        /*JsonParser parser = new JsonFactory().createParser(new File("/home/moglix/Desktop/test.json"));*/
        JsonParser parser = new JsonFactory().createParser(new File("/home/moglix/Desktop/test_data.json"));
        
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();

        JsonNode currentNode;
        int i=1;
        while (iter.hasNext()) {
            currentNode = iter.next();
            /*String artist = currentNode.path("Artist").asText();
            String songTitle = currentNode.path("SongTitle").asText();
            JsonObject jo = (JsonObject) Json.createObjectBuilder()
                    .add("Actors", currentNode.path("Actors").asText())
                    .add("Release Date", currentNode.path("Release Date").asText())
                    .add("Plot", currentNode.path("Plot").asText())
                    .add("Genres", currentNode.path("Genres").asText())
                    .add("Directors", currentNode.path("Directors").asText())
                    .add("Rating", currentNode.path("Rating").asText())
                    .add("Ranking", currentNode.path("Ranking").asText())
                    .add("Running Time", currentNode.path("Running Time").asText())
                    .add("Language", currentNode.path("Language").asText())
                    .add("Greeting", currentNode.path("Greeting").asText())
                    .add("Name", currentNode.path("Name").asText())
                    .build();*/
            /*String id = currentNode.path("CCF_Contract_Id").asText();
            String mart = currentNode.path("Master_Agreement_Request_Type").asText();
            String cn = currentNode.path("Contract_Name").asText();
            String ctct = currentNode.path("CTC_Type").asText();
            boolean pc = currentNode.path("Provisional_Contract").asBoolean();
            String network = currentNode.path("Network").asText();
            String port = currentNode.path("Portfolio").asText();
            String tn = currentNode.path("Team_Name").asText();
            String clus = currentNode.path("Clus").asText();
            String coun = currentNode.path("Country").asText();
            
            JsonObject jo1 = (JsonObject) Json.createObjectBuilder().add("Unit Price", currentNode.path("Unit Price").asText())
            		.add("Minimum Quantity", currentNode.path("Minimum Quantity").asText())
            		.add("Maximum Quantity", currentNode.path("Maximum Quantity").asText()).build();
            JsonObject jo2 = (JsonObject) Json.createObjectBuilder().add("Unit_Price", currentNode.path("Unit_Price").asText())
            		.add("Unit Price Begin Date", currentNode.path("Unit Price Begin Date").asText())
            		.add("Price Scale", jo1).build();
            JsonObject jo3 = (JsonObject) Json.createObjectBuilder().add("Value", currentNode.path("Value").asText())
            		.add("Condition", currentNode.path("Condition").asText())
            		.add("Pricing Contition #", currentNode.path("Pricing Contition #").asText())
            		.add("Vendor Code", currentNode.path("Vendor Code").asDouble()).build();
            JsonObject joMain = (JsonObject) Json.createObjectBuilder().add("Line Item Number", i)
            		.add("SAP System", currentNode.path("SAP System").asText())
            		.add("Material ID", currentNode.path("Material ID").asText())
            		.add("Material Description", currentNode.path("Material Description").asText())
            		.add("Pricing Condition", jo3)
            		.add("Price Validity", jo2).build();*/
            long matid = currentNode.path("Material_ID").asLong();
            String matname = currentNode.path("Material_Name").asText();
            String plid = currentNode.path("Plant_ID").asText();
            
            
            //String info = currentNode.path("actors").toString()+","+ currentNode.path("release_date").toString()+"," + currentNode.path("plot").toString()+","+currentNode.path("genres").toString()+","+currentNode.path("directors").toString()+","+currentNode.path("rating").toString()+","+currentNode.path("ranking").toString()+","+currentNode.path("running_time_secs").toString();
            //System.out.println(info);
            try {
                /*table.putItem(new Item().withPrimaryKey("Artist", artist, "SongTitle", songTitle).withJSON("info", jo.toString()));*/
                /*table.putItem(new Item().withPrimaryKey("CCF_Contract_Id", id)
                		.withString("Master_Agreement_Request_Type", mart)
                		.withString("Contract_Name", cn)
                		.withString("CTC_Type", ctct)
                		.withBoolean("Provisional_Contract", pc)
                		.withString("Network", network)
                		.withString("Portfolio", port)
                		.withString("Team_Name", tn)
                		.withString("Clus", clus)
                		.withString("Country", coun)
                		.withJSON("Line_Items", joMain.toString()));*/
            	table.putItem(new Item().withPrimaryKey("Material_ID", matid)
                		.withString("Material_Name", matname)
                		.withString("Plant_ID", plid));
                
                /*System.out.println("PutItem succeeded: " + artist + " " + songTitle);*/
                /*System.out.println("PutItem succeeded: " + id);*/
            	System.out.println("PutItem succeeded: " + matid);
                i++;
            }
            catch (Exception e) {
                /*System.err.println("Unable to add movie: " + artist + " " + songTitle);*/
            	/*System.err.println("Unable to add id: " + id);*/
            	System.err.println("Unable to add id: " + matid);
                System.err.println(e.getMessage());
                break;
            }
        }
        parser.close();
    }

}
