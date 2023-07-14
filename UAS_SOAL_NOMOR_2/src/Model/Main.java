package Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {

            String xmlString = new String(Files.readAllBytes(Paths.get("src/Model/xml.xml")));
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            String jsonString = jsonObject.toString(4);

            System.out.println(jsonString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
