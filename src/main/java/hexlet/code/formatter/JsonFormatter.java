package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Value;

import java.util.LinkedHashMap;
import java.util.Map;


public class JsonFormatter {

    public static String format(Map<String, Value> valueMap) throws JsonProcessingException {
        Map<String, Object> jMap = new LinkedHashMap<>();

        for (Map.Entry<String, Value> entry : valueMap.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case STATUS_ADDED -> valueWasAdded(jMap, valueMap, entry.getKey());
                case STATUS_DELETED -> valueWasDeleted(jMap, valueMap, entry.getKey());
                case STATUS_CHANGED -> valueWasChanged(jMap, valueMap, entry.getKey());
                case STATUS_UNCHANGED -> valueWasUnchanged(jMap, valueMap, entry.getKey());
                default -> {
                }
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jMap);
    }

    private static void valueWasAdded(Map<String, Object> jMap, Map<String, Value> valueMap, String key) {
        jMap.put("+" + key, valueMap.get(key).getSecondOb());
    }

    private static void valueWasDeleted(Map<String, Object> jMap, Map<String, Value> valueMap, String key) {
        jMap.put("-" + key, valueMap.get(key).getFirstOb());
    }

    private static void valueWasChanged(Map<String, Object> jMap, Map<String, Value> valueMap, String key) {
        jMap.put("-" + key, valueMap.get(key).getFirstOb());
        jMap.put("+" + key, valueMap.get(key).getSecondOb());
    }

    private static void valueWasUnchanged(Map<String, Object> jMap, Map<String, Value> valueMap, String key) {
        jMap.put(key, valueMap.get(key).getFirstOb());
    }


}
