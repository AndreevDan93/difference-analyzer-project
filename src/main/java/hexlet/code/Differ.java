package hexlet.code;

import hexlet.code.formates.JsonFormat;
import hexlet.code.formates.PlainFormat;
import hexlet.code.formates.StylishFormat;

import java.util.Map;

public class Differ {
    static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> map1 = Parser.fileToMap(filePath1);
        Map<String, Object> map2 = Parser.fileToMap(filePath2);
        return switch (format) {
            case "json" -> JsonFormat.generateJsonOutputFormat(map1, map2);
            case "plain" -> PlainFormat.generatePlainOutputFormat(map1, map2);
            case "stylish" -> StylishFormat.generateStylishOutputFormat(map1, map2);
            default -> throw new RuntimeException(format + "this format is not supported");
        };
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }

}
