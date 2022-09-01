package hexlet.code.formates;

import hexlet.code.Parser;

import java.util.Map;
import java.util.Set;

public class StylishFormat {
    public static String generateStylishOutputFormat(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keysSet = Parser.getKeySetFromTwoMaps(map1, map2);

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
            } else if (Parser.compareValues(map1.get(key), map2.get(key))) {
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


        builder.append("}");
        return builder.toString().trim();
    }
}
