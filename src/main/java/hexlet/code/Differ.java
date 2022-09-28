package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String ext1 = Utils.getExtension(filepath1);
        String ext2 = Utils.getExtension(filepath2);

        String data1 = Utils.readFile(Utils.getAbsolutePath(filepath1));
        String data2 = Utils.readFile(Utils.getAbsolutePath(filepath2));

        Map<String, Object> map1 = Parser.parse(data1, ext1);
        Map<String, Object> map2 = Parser.parse(data2, ext2);

        Map<String, Value> diff = DiffBuilder.getDiff(map1, map2);

        Formatter formatter = chooseFormatter(format);
        return formatter.format(diff);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Formatter chooseFormatter(String format) {
        return switch (format) {
            case "json" -> new JsonFormatter();
            case "plain" -> new PlainFormatter();
            case "stylish" -> new StylishFormatter();
            default -> throw new RuntimeException(format + " this format is not supported");
        };
    }

}
