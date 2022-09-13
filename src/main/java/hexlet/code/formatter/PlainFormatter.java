package hexlet.code.formatter;

import hexlet.code.Value;
import java.util.Map;

public final class PlainFormatter extends Formatter {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public String format(Map<String, Value> valueMap) {
        generateFormat(valueMap);
        return builder.toString().trim();
    }

    @Override
    protected void valueWasAdded(Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was added with value: ")
                .append(getPlainValue(valueMap, key, true))
                .append("\n");
    }

    @Override
    protected void valueWasDeleted(Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was removed")
                .append("\n");
    }

    @Override
    protected void valueWasChanged(Map<String, Value> valueMap, String key) {
        builder.append("Property '")
                .append(key)
                .append("' was updated. From ")
                .append(getPlainValue(valueMap, key, false))
                .append(" to ")
                .append(getPlainValue(valueMap, key, true))
                .append("\n");
    }

    @Override
    protected void valueWasUnchanged(Map<String, Value> valueMap, String key) {
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

