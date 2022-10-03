package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Value;


import java.util.Map;


final class JsonFormatter extends Formatter {

    @Override
    public String format(Map<String, Value> valueMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(valueMap);
    }

    @Override
    protected void valueWasAdded(Map<String, Value> valueMap, String key) {
        throw new RuntimeException("unsupported method");
    }

    @Override
    protected void valueWasDeleted(Map<String, Value> valueMap, String key) {
        throw new RuntimeException("unsupported method");
    }

    @Override
    protected void valueWasChanged(Map<String, Value> valueMap, String key) {
        throw new RuntimeException("unsupported method");
    }

    @Override
    protected void valueWasUnchanged(Map<String, Value> valueMap, String key) {
        throw new RuntimeException("unsupported method");
    }
}


