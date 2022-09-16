package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.parser.Parser;
import hexlet.code.parser.JsonParser;
import hexlet.code.parser.YmlParser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {
    private final Parser parser;
    private final Formatter formatter;

    private final Map<String, Value> map;

    public Differ(String filepath1, String filepath2, String format) throws IOException {
        this.parser = chooseParser(filepath1, filepath2);
        this.formatter = chooseFormatter(format);
        this.map = getDiff(filepath1, filepath2);
    }

    public Differ(String filepath1, String filepath2) throws IOException {
        this(filepath1, filepath2, "stylish");

    }

    public String generate() throws IOException {
        return this.formatter.format(this.map);
    }

    private Parser chooseParser(String filePath1, String filePath2) throws IOException {
        String extension1 = Utils.getExtension(filePath1);
        String extension2 = Utils.getExtension(filePath2);
        if (!extension1.equals(extension2)) {
            throw new RuntimeException("input file formats are different");
        }
        return switch (extension1) {
            case "json" -> new JsonParser();
            case "yml" -> new YmlParser();
            default -> throw new RuntimeException("this file type is not supported");
        };
    }

    private Formatter chooseFormatter(String format) {
        return switch (format) {
            case "json" -> new JsonFormatter();
            case "plain" -> new PlainFormatter();
            case "stylish" -> new StylishFormatter();
            default -> throw new RuntimeException(format + " this format is not supported");
        };
    }


    private Map<String, Value> getDiff(String filePath1, String filePath2) throws IOException {
        Map<String, Object> map1 = this.parser.parse(filePath1);
        Map<String, Object> map2 = this.parser.parse(filePath2);

        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());

        Map<String, Value> diff = new LinkedHashMap<>();
        for (String key : keysSet) {
            if (!map1.containsKey(key)) {
                diff.put(key, new Value(null, map2.get(key), Status.STATUS_ADDED));
            } else if (!map2.containsKey(key)) {
                diff.put(key, new Value(map1.get(key), null, Status.STATUS_DELETED));
            } else if (compare(map1.get(key), map2.get(key))) {
                diff.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_UNCHANGED));
            } else {
                diff.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_CHANGED));
            }
        }
        return diff;
    }

    private boolean compare(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == null && value2 == null;
        } else {
            return value1.equals(value2);
        }
    }

}
