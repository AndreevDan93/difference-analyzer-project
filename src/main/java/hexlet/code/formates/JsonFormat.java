package hexlet.code.formates;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.nio.file.Files.readString;

public class JsonFormat {

    public static Map<String, Object> fileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), new TypeReference<>() {
        });
    }

    public static String generateJsonOutputFormat(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keysSet = new TreeSet<>();

        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());


        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (String key : keysSet) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                builder.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(map1.get(key))
                        .append("\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                builder.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(map2.get(key))
                        .append("\n");
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key) == null || map2.get(key) == null) {
                    if (map1.get(key) == null && map2.get(key) == null) {
                        builder.append("    ")
                                .append(key)
                                .append(": ")
                                .append(map1.get(key))
                                .append("\n");
                    } else {
                        builder.append("  - ")
                                .append(key)
                                .append(": ")
                                .append(map1.get(key))
                                .append("\n");
                        builder.append("  + ")
                                .append(key)
                                .append(": ")
                                .append(map2.get(key))
                                .append("\n");
                    }
                } else if (map1.get(key).equals(map2.get(key))) {
                    builder.append("    ")
                            .append(key)
                            .append(": ")
                            .append(map1.get(key))
                            .append("\n");
                } else {
                    builder.append("  - ")
                            .append(key).append(": ")
                            .append(map1.get(key))
                            .append("\n");
                    builder.append("  + ")
                            .append(key)
                            .append(": ")
                            .append(map2.get(key))
                            .append("\n");
                }
            }

        }
        builder.append("}");
        return builder.toString();
    }
}
