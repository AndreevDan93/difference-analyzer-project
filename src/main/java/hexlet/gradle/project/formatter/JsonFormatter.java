package hexlet.gradle.project.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;


public final class JsonFormatter {

    public static String format(List<Map<String, List<Object>>> diffList) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffList);
    }

}
