package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Value;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


final class JsonFormatter extends Formatter {

    private final List<Map<String, Object>> diffList = new LinkedList<>();

    @Override

    public String format(Map<String, Value> valueMap) throws JsonProcessingException {
        generateFormat(valueMap);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffList);
    }

    @Override
    protected void valueWasAdded(Map<String, Value> valueMap, String key) {
        String value = key + ":" + valueMap.get(key).getSecondOb();
        putValueToList(valueMap, key, value);
    }

    @Override
    protected void valueWasDeleted(Map<String, Value> valueMap, String key) {
        String value = key + ":" + valueMap.get(key).getFirstOb();
        putValueToList(valueMap, key, value);
    }

    @Override
    protected void valueWasChanged(Map<String, Value> valueMap, String key) {
        String value = key + ":" + valueMap.get(key).getFirstOb() + "," + valueMap.get(key).getSecondOb();
        putValueToList(valueMap, key, value);
    }

    @Override
    protected void valueWasUnchanged(Map<String, Value> valueMap, String key) {
        String value = key + ":" + valueMap.get(key).getFirstOb();
        putValueToList(valueMap, key, value);
    }

    private void putValueToList(Map<String, Value> valueMap, String key, String value) {
        String diffKey = valueMap.get(key).getStatus().getValue();
        diffList.add(Map.of(diffKey, value));
    }

}
