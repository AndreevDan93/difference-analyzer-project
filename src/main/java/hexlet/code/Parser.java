package hexlet.code;

import hexlet.code.formates.JsonFormat;
import hexlet.code.formates.YmlFormat;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, Object> fileToMap(String path) throws Exception {
        if (path.endsWith("json")) {
            Map<String, Object> map = JsonFormat.fileToMap(getAbsolutePathToFile(path));
            return map;
        }
        if (path.endsWith("yml")) {
            Map<String, Object> map = YmlFormat.fileToMap(getAbsolutePathToFile(path));
            return map;
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
}
