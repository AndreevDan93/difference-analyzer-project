package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.util.Map;


import static java.nio.file.Files.readString;

public class Parser {

    public static Map<String, Object> fileToMap(Path path) throws Exception {
        String pathString = path.toString();
        String extension = pathString.substring(pathString.lastIndexOf(".") + 1);
        switch (extension) {
            case "json" -> {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(readString(path), new TypeReference<>() {
                });
            }
            case "yml" -> {
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                return mapper.readValue(readString(path), new TypeReference<>() {
                });
            }
            default -> throw new RuntimeException("this file type is not supported");

        }
    }
}


