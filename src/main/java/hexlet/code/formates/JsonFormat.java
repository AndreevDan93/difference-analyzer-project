package hexlet.code.formates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Parser;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.nio.file.Files.readString;

public class JsonFormat {

    public static Map<String, Object> fileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), new TypeReference<>() {
        });
    }

    public static String generateJsonOutputFormat(Map<String, Object> map1, Map<String, Object> map2)
            throws JsonProcessingException {
        Set<String> keysSet = Parser.getKeySetFromTwoMaps(map1, map2);
        Map<String, Object> jMap = new LinkedHashMap<>();
        for (String key : keysSet) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                jMap.put("-" + key, map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                jMap.put("+" + key, map1.get(key));
            } else if (Parser.compareValues(map1.get(key), map2.get(key))) {
                jMap.put(key, map1.get(key));
            } else {
                jMap.put("-" + key, map1.get(key));
                jMap.put("+" + key, map1.get(key));
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(jMap);
    }


}
