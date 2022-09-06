package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.nio.file.Files.readString;

public class Parser {
    public static Map<String, Value> getDataFile(Path path1, Path path2) throws Exception {
        Map<String, Object> map1 = fileToMap(path1);
        Map<String, Object> map2 = fileToMap(path2);
        Map<String, Value> fileChangesMap = new LinkedHashMap<>();
        Set<String> keySet = getKeySetFromTwoMaps(map1, map2);
        String isNotInitialized = Value.IS_NOT_INITIALIZED;
        for (String key : keySet) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                fileChangesMap.put(key, new Value(map1.get(key), map2.get(key)));
            } else if (!map1.containsKey(key)) {
                fileChangesMap.put(key, new Value(isNotInitialized, map2.get(key)));
            } else {
                fileChangesMap.put(key, new Value(map1.get(key), isNotInitialized));
            }
        }
        return fileChangesMap;
    }

    public static Map<String, Object> fileToMap(Path path) throws Exception {
        String pathString = path.toString();
        if (pathString.endsWith("json")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(readString(path), new TypeReference<>() {
            });
        }
        if (pathString.endsWith("yml")) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(readString(path), new TypeReference<>() {
            });
        }
        throw new RuntimeException("this file type is not supported");
    }

    public static Set<String> getKeySetFromTwoMaps(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keysSet = new TreeSet<>();
        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());
        return keysSet;
    }

}
