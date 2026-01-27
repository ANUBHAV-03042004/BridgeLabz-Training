package validateanemailfieldusingjsonschema;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateAnEmailFieldUsingJsonSchema {
	static boolean EmailValidation(String email)
	{
		String regex ="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		return email.matches(regex);
	}
public static void main(String[] args) {
	String source = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\jsondatahandling\\readajsonfileandprintallkeysandvalues\\student.json";
	ObjectMapper mapper = new ObjectMapper();
	try {
		JsonNode rootNode = mapper.readTree(new File(source));
		  if(!rootNode.isArray()) {
			  throw new RuntimeException("Invalid Json : Root element must be an array");
		  }
		  for(JsonNode node:rootNode) {
			 
		     if(!node.has("email")||!node.get("email").isTextual()) {
		    	 throw new RuntimeException("Invalid or missing Email.");
			  }
		     String email = node.get("email").asText();
		      if(EmailValidation(email)) {
		     System.out.println("Email : "+ email);
		     System.out.println("Email is Valid");
		      }
		      else {  
		    	  System.out.println("Email : "+ email);
				     System.out.println("Email is InValid");
				      }
		  }
	}catch(Exception e) {
		System.out.println("Email Validation failed :"+ e.getMessage());
}
	}
}
