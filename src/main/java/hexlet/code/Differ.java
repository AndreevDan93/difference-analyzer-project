package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;

import java.nio.file.Path;
import java.util.Map;

public class Differ {

    public static String generate(String path1, String path2, String format) throws Exception {
        Path absolutePath1 = Utils.getAbsolutePathToFile(path1);
        Path absolutePath2 = Utils.getAbsolutePathToFile(path2);
        Map<String, Object> mapFromFile1 = Parser.fileToMap(absolutePath1);
        Map<String, Object> mapFromFile2 = Parser.fileToMap(absolutePath2);
        Map<String, Value> mapOfChanges = Utils.expectChangesFromTwoMap(mapFromFile1, mapFromFile2);
        return formatting(mapOfChanges, format);
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
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
