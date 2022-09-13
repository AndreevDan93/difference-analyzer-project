package hexlet.code.formatter;

import hexlet.code.Value;

import java.util.Map;


public final class StylishFormatter extends Formatter {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public String format(Map<String, Value> valueMap) {
        generateFormat(valueMap);
        return "{\n" + builder + "}";
    }

    @Override
    protected void valueWasAdded(Map<String, Value> valueMap, String key) {
        builder.append("  + ").append(key).append(": ")
                .append(valueMap.get(key).getSecondOb()).append("\n");
    }

    @Override
    protected void valueWasDeleted(Map<String, Value> valueMap, String key) {
        builder.append("  - ").append(key).append(": ")
                .append(valueMap.get(key).getFirstOb()).append("\n");

    }

    @Override
    protected void valueWasChanged(Map<String, Value> valueMap, String key) {
        builder.append("  - ").append(key).append(": ")
                .append(valueMap.get(key).getFirstOb()).append("\n");
        builder.append("  + ").append(key).append(": ")
                .append(valueMap.get(key).getSecondOb()).append("\n");
    }

    @Override
    protected void valueWasUnchanged(Map<String, Value> valueMap, String key) {
        builder.append("    ").append(key).append(": ")
                .append(valueMap.get(key).getFirstOb()).append("\n");
    }

}
