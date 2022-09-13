package hexlet.code;

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
        Map<String, Value> mapOfChanges = getMapOfValue(mapFromFile1, mapFromFile2);
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

    private static Map<String, Value> getMapOfValue(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Value> mapOfChanges = new LinkedHashMap<>();
        Set<String> keysSet = new TreeSet<>();
        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());
        String isNotInitialized = Value.IS_NOT_INITIALIZED;
        for (String key : keysSet) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                mapOfChanges.put(key, new Value(map1.get(key), map2.get(key)));
            } else if (!map1.containsKey(key)) {
                mapOfChanges.put(key, new Value(isNotInitialized, map2.get(key)));
            } else {
                mapOfChanges.put(key, new Value(map1.get(key), isNotInitialized));
            }
        }
        return mapOfChanges;
    }

    private static String formatting(Map<String, Value> mapOfChanges, String format) throws Exception {
        String result;

        switch (format) {
            case "json" -> {
                JsonFormatter formatter = new JsonFormatter();
                result = formatter.format(mapOfChanges);
            }
            case "plain" -> {

                PlainFormatter formatter = new PlainFormatter();
                result = formatter.format(mapOfChanges);
            }
            case "stylish" -> {
                StylishFormatter formatter = new StylishFormatter();
                result = formatter.format(mapOfChanges);
            }
            default -> throw new RuntimeException(format + " this format is not supported");
        }
        return result;
    }


}
