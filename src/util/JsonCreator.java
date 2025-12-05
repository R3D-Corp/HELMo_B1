package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonCreator {
    public JsonCreator(String filePath) {
        Path path = Paths.get(filePath);
        
        if(!Files.exists(path)) {
            String emptyJsonContent = "[]";
            try {
                Files.write(path, emptyJsonContent.getBytes(StandardCharsets.UTF_8));
            } catch(IOException e) {
                IO.println("Error while creating json file : "+e.getMessage());
            }
        }
    }
}
