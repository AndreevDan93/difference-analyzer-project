package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Value;

import java.util.Map;

public final class FormatBuilder {
    public static String format(Map<String, Value> map, String format) throws JsonProcessingException {
        Formatter formatter = switch (format) {
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            case "json" -> new JsonFormatter();
            default ->  throw new RuntimeException(format + "this format is not supported");
        };

        return formatter.format(map);
    }
}
