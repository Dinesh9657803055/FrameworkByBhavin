package com.openxcell.OnlineShopping.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		String filePath = (System.getProperty("user.dir")+"\\src\\main\\java\\com\\openxcell\\OnlineShopping\\Resources\\PurchaseOrder.json");
		System.out.println(filePath);
		File jsonFile = new File(filePath);
		System.out.println(jsonFile.getName());
		//Read JSON data to String
		String jsonContent = FileUtils.readFileToString(jsonFile, StandardCharsets.UTF_8);
		
		//Convert String to HashMap object using Jackson Databind library
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		data.stream().forEach(System.out::println);
		//So the data variable to store all the json data in to the HashMap format
		return data;
	}
}