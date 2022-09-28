package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String ext1 = Utils.getExtension(filepath1);
        String ext2 = Utils.getExtension(filepath2);

        String data1 = Utils.readFile(Utils.getAbsolutePath(filepath1));
        String data2 = Utils.readFile(Utils.getAbsolutePath(filepath2));

        Map<String, Object> map1 = Parser.parse(data1, ext1);
        Map<String, Object> map2 = Parser.parse(data2, ext2);

        List<Map<String, List<Object>>> diff = new ArrayList<>(DiffBuilder.buildDiff(map1, map2));

        return Formatter.format(diff, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

}
