package convertjsontoxmlformat;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
public class ConvertJsonToXmlFormat {
public static void main(String[] args) {
	try {
        String filePath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\jsondatahandling\\readajsonfileandprintallkeysandvalues\\student.json";

        // Read JSON
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonNode = jsonMapper.readTree(new File(filePath));
        ObjectNode root = jsonMapper.createObjectNode();
        root.set("students", jsonNode); 


        // Convert to XML
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);

        // Print XML
        System.out.println("Converted XML:");
        System.out.println(xml);

    } catch (Exception e) {
        e.printStackTrace();
    }

}
}
