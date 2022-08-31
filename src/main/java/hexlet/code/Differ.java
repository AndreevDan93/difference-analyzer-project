package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> map1 = Parser.fileToMap(filePath1);
        Map<String, Object> map2 = Parser.fileToMap(filePath2);

        Set<String> keysSet = new TreeSet<>();

        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());


        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (String key : keysSet) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                builder.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                builder.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key) == null || map2.get(key) == null) {
                    if (map1.get(key) == null && map2.get(key) == null) {
                        builder.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
                    } else {
                        builder.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                        builder.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
                    }
                } else if (map1.get(key).equals(map2.get(key))) {
                    builder.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
                } else {
                    builder.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                    builder.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
                }
            }

        }
        builder.append("}");
        return builder.toString();
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }

}
