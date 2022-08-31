package hexlet.code.formates;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.readString;

public class JsonFormat {

    public static Map<String, Object> fileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), new TypeReference<>() {
        });
    }
}
