package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    private static final String LOCAL_PATH_INSIDE_PROJECT = "src/main/resources/";

    public static String generate(String path1, String path2, String format) throws Exception {
        Path absolutePath1 = getAbsolutePathToFile(path1);
        Path absolutePath2 = getAbsolutePathToFile(path2);
        Map<String, Object> mapFromFile1 = Parser.fileToMap(absolutePath1);
        Map<String, Object> mapFromFile2 = Parser.fileToMap(absolutePath2);
        Map<String, Value> mapOfChanges = getMapOfChanges(mapFromFile1, mapFromFile2);
        return formatting(mapOfChanges, format);
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }

    private static Path getAbsolutePathToFile(String inputPathString) throws IOException {
        Path inputPath = Path.of(inputPathString);
        Path absolutePath;
        if (inputPath.isAbsolute() || new File(inputPath.toString()).exists()) {
            return inputPath;
        } else {
            StringBuilder absolutePathSB = new StringBuilder(inputPath.toAbsolutePath().toString());
            absolutePathSB.insert(absolutePathSB.length() - inputPathString.length(), LOCAL_PATH_INSIDE_PROJECT);
            absolutePath = Path.of(absolutePathSB.toString());
        }
        if (!(new File(absolutePath.toString()).exists())) {
            throw new NoSuchFileException("file " + inputPathString + " is not found");
        }
        return absolutePath;
    }

    //   need to work the next method...
    private static Map<String, Value> getMapOfChanges(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Value> mapOfChanges = new LinkedHashMap<>();
        Set<String> keysSet = new TreeSet<>();
        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());
        for (String key : keysSet) {
            if (!map1.containsKey(key)) {
                mapOfChanges.put(key, new Value(null, map2.get(key), Status.STATUS_ADDED));
            } else if (!map2.containsKey(key)) {
                mapOfChanges.put(key, new Value(map1.get(key), null, Status.STATUS_DELETED));
            } else if (compareTo(map1.get(key), map2.get(key))) {
                mapOfChanges.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_UNCHANGED));
            } else {
                mapOfChanges.put(key, new Value(map1.get(key), map2.get(key), Status.STATUS_CHANGED));
            }
        }
        return mapOfChanges;
    }

    private static boolean compareTo(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == null && value2 == null;
        } else {
            return value1.equals(value2);
        }
    }

    private static String formatting(Map<String, Value> mapOfChanges, String format) throws Exception {
        Formatter formatter = switch (format) {
            case "json" -> new JsonFormatter();
            case "plain" -> new PlainFormatter();
            case "stylish" -> new StylishFormatter();
            default -> throw new RuntimeException(format + " this format is not supported");
        };
        return formatter.format(mapOfChanges);
    }


}
