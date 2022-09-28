
package hexlet.gradle.project;


import hexlet.gradle.project.formatter.JsonFormatter;
import hexlet.gradle.project.formatter.PlainFormatter;
import hexlet.gradle.project.formatter.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, List<Object>>> diffList, String format) throws Exception {
        return switch (format) {
            case "stylish" -> StylishFormatter.format(diffList);
            case "plain" -> PlainFormatter.format(diffList);
            case "json" -> JsonFormatter.format(diffList);
            default -> throw new RuntimeException("incorrect format: " + format);
        };
    }

}
