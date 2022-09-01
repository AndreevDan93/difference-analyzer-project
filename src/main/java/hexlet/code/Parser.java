package hexlet.code;

import hexlet.code.formates.JsonFormat;
import hexlet.code.formates.YmlFormat;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Parser {
    public static Map<String, Object> fileToMap(String path) throws Exception {
        if (path.endsWith("json")) {
            return JsonFormat.fileToMap(getAbsolutePathToFile(path));
        }
        if (path.endsWith("yml")) {
            return YmlFormat.fileToMap(getAbsolutePathToFile(path));
        }
        throw new RuntimeException("this file type is not supported");
    }

    public static Path getAbsolutePathToFile(String inputPathString) {
        Path absolutePath;
        if (inputPathString.charAt(0) == '/') {
            absolutePath = Path.of(inputPathString);
        } else {
            File file = new File("src/main/resources");
            String path = file.getAbsolutePath();
            absolutePath = Path.of(path + "/" + inputPathString);
        }
        if (new File(absolutePath.toString()).exists()) {
            return absolutePath;
        } else {
            throw new RuntimeException("file " + inputPathString + " is not found");
        }
    }

    public static boolean compareValues(Object ob1, Object ob2) {
        if (ob1 == null || ob2 == null) {
            return ob1 == null && ob2 == null;
        } else {
            return ob1.equals(ob2);
        }
    }

    public static Set<String> getKeySetFromTwoMaps(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keysSet = new TreeSet<>();

        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());
        return keysSet;
    }
}
