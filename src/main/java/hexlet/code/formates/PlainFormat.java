package hexlet.code.formates;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PlainFormat {
    public static String generatePlainOutputFormat(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keysSet = new TreeSet<>();

        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());


        StringBuilder builder = new StringBuilder();

        for (String key : keysSet) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                builder.append("Property '").append(key).append("' was removed").append("\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                builder.append("Property '").append(key).append("' was added with value: ")
                        .append(getMapValue(map2, key)).append("\n");
            } else if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key) == null || map2.get(key) == null) {
                    if (!(map1.get(key) == null && map2.get(key) == null)) {
                        builder.append("Property '").append(key).append("' was updated. From ")
                                .append(getMapValue(map1, key)).append(" to ").append(getMapValue(map2, key)).append("\n");
                    }

                } else if (!map1.get(key).equals(map2.get(key))) {
                    builder.append("Property '").append(key).append("' was updated. From ")
                            .append(getMapValue(map1, key)).append(" to ").append(getMapValue(map2, key)).append("\n");
                }
            }
        }

        return builder.toString().trim();
    }

    private static String getMapValue(Map<String, Object> map, String key) {
        String result;
        if (map.get(key) == null) {
            result = null;
        } else if (map.get(key) instanceof String) {
            result = "'" + map.get(key) + "'";
        } else if (map.get(key) instanceof Integer) {
            result = map.get(key).toString();
        } else if (map.get(key).equals(false) || map.get(key).equals(true)) {
            result = map.get(key).toString();
        } else {
            result = "[complex value]";
        }
        return result;
    }


}
