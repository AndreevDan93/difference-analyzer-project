package hexlet.code.formatter;

import hexlet.code.Value;

import java.util.Map;

public class PlainFormatter {
    public static String format(Map<String, Value> valueMap) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Value> entry : valueMap.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case STATUS_ADDED -> valueWasAdded(builder, valueMap, entry.getKey());
                case STATUS_DELETED -> valueWasDeleted(builder, entry.getKey());
                case STATUS_CHANGED -> valueWasChanged(builder, valueMap, entry.getKey());
                default -> {
                }
            }
        }
        return builder.toString().trim();
    }

    private static void valueWasAdded(StringBuilder builder, Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was added with value: ")
                .append(getPlainValue(valueMap, key, true))
                .append("\n");
    }

    private static void valueWasDeleted(StringBuilder builder, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was removed")
                .append("\n");
    }

    private static void valueWasChanged(StringBuilder builder, Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was updated. From ")
                .append(getPlainValue(valueMap, key, false))
                .append(" to ")
                .append(getPlainValue(valueMap, key, true))
                .append("\n");
    }

    private static String getPlainValue(Map<String, Value> map, String key, boolean isAdded) {
        Object value = isAdded ? map.get(key).getSecondOb() : map.get(key).getFirstOb();

        String result;

        if (value == null) {
            result = null;
        } else if (value instanceof String) {
            result = "'" + value + "'";
        } else if (value instanceof Integer) {
            result = value.toString();
        } else if (value.equals(false) || value.equals(true)) {
            result = value.toString();
        } else {
            result = "[complex value]";
        }
        return result;
    }


}
