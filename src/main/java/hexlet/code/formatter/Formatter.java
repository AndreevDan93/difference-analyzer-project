package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Value;

import java.util.Map;

public class Formatter {
    public static String format(Map<String, Value> map, String format) throws JsonProcessingException {
        IFormatter formatter = switch (format) {
            case "json" -> new JsonFormatter();
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            default -> throw new RuntimeException("format" + format + "is not supported");
        };
        return formatter.format(map);
    }
}
