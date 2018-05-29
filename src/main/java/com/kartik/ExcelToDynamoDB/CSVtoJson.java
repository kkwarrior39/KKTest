package com.kartik.ExcelToDynamoDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CSVtoJson {
	public static void convert() {
		/*File output = new File("/home/moglix/test.json");*/
		File output = new File("/home/moglix/Desktop/plant_data.json");
    	try{
    		/*InputStream in = new FileInputStream("/home/moglix/Desktop/CSVtext.csv");*/
    		InputStream in = new FileInputStream("/home/moglix/Desktop/Test_Data.csv");

    	    ReadCSV readCSV = new ReadCSV(true, ';', in );

    	    List < String > fieldNames = null;

    	    if (readCSV.hasNext()) fieldNames = new ArrayList(readCSV.next());

    	    List < Map < String, String >> list = new ArrayList();

    	    while (readCSV.hasNext()) {

    	        List < String > x = readCSV.next();

    	        Map < String, String > obj = new LinkedHashMap();

    	        for (int i = 0; i < fieldNames.size(); i++) {

    	            obj.put(fieldNames.get(i), x.get(i));

    	        }

    	        list.add(obj);

    	    }

    	    ObjectMapper mapper = new ObjectMapper();

    	    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    	    mapper.writeValue(output, list);
    	    System.out.println("Success");

    	}
    	catch(Exception e) {
    		System.err.println();
    	}
	}
}
