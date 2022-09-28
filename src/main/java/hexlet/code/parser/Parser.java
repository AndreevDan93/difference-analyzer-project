package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public final class Parser {
    public static Map<String, Object> parse(String data, String ext) throws JsonProcessingException {

        return switch (ext) {
            case "json" -> new ObjectMapper().readValue(data, new TypeReference<>() {
            });
            case "yml" -> new ObjectMapper(new YAMLFactory()).readValue(data, new TypeReference<>() {
            });
            default -> throw new RuntimeException("incorrect file extension: " + ext);
        };
    }
}
