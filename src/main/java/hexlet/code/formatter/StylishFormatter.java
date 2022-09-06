package hexlet.code.formatter;


import hexlet.code.Value;

import java.util.Map;

public class StylishFormatter {

    public static String format(Map<String, Value> valueMap) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        for (Map.Entry<String, Value> entry : valueMap.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case STATUS_ADDED -> valueWasAdded(builder, valueMap, entry.getKey());
                case STATUS_DELETED -> valueWasDeleted(builder, valueMap, entry.getKey());
                case STATUS_CHANGED -> valueWasChanged(builder, valueMap, entry.getKey());
                case STATUS_UNCHANGED -> valueWasUnchanged(builder, valueMap, entry.getKey());
                default -> {
                }
            }
        }
        builder.append("}");
        return builder.toString().trim();
    }

    private static void valueWasAdded(StringBuilder builder, Map<String, Value> valueMap, String key) {
        builder.append("  + ").append(key).append(": ")
                .append(valueMap.get(key).getSecondOb()).append("\n");
    }

    private static void valueWasDeleted(StringBuilder builder, Map<String, Value> valueMap, String key) {
        builder.append("  - ").append(key).append(": ")
                .append(valueMap.get(key).getFirstOb()).append("\n");
    }

    private static void valueWasChanged(StringBuilder builder, Map<String, Value> valueMap, String key) {
        builder.append("  - ").append(key).append(": ")
                .append(valueMap.get(key).getFirstOb()).append("\n");
        builder.append("  + ").append(key).append(": ")
                .append(valueMap.get(key).getSecondOb()).append("\n");
    }

    private static void valueWasUnchanged(StringBuilder builder, Map<String, Value> valueMap, String key) {
        builder.append("    ").append(key).append(": ")
                .append(valueMap.get(key).getFirstOb()).append("\n");
    }
}
