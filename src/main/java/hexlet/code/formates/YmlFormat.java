package hexlet.code.formates;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.readString;

public class YmlFormat {

    public static Map<String, Object> fileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(readString(path), new TypeReference<>() { });
    }
}
