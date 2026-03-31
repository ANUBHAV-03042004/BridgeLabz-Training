package mergetwojsonfilesintosinglejsonobject;



import java.util.Iterator;
import java.io.File;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeJsonFilesIntoSingleJsonObject {
public static void main(String[] args) {
	ObjectMapper mapper  = new ObjectMapper();
	String filePath1="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\jsondatahandling\\readajsonfileandprintallkeysandvalues\\student.json";
	String filePath2="C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\io-programming-practice\\gcr-codebase\\jsondatahandling\\readajsonfileandprintallkeysandvalues\\student.json";
    try {
        JsonNode json1 = mapper.readTree(new File(filePath1));
        JsonNode json2 = mapper.readTree(new File(filePath2));

        if (!json1.isArray() || !json2.isArray()) {
            throw new RuntimeException("Both JSON files must contain arrays at the root.");
        }

        ArrayNode merged = mapper.createArrayNode();
        merged.addAll((ArrayNode) json1);
        merged.addAll((ArrayNode) json2);

        System.out.println("Merged JSON:");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged));

    } catch (Exception e) {
        e.printStackTrace();
    }

}
}
