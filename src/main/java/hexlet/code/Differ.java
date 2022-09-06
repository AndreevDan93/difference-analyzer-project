package hexlet.code;

import hexlet.code.formatter.JsonFormatter;
import hexlet.code.formatter.PlainFormatter;
import hexlet.code.formatter.StylishFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    private static final String LOCAL_PATH_INSIDE_PROJECT = "src/main/resources/";

    public static String generate(String path1, String path2, String format) throws Exception {
        Path filePath1 = getAbsolutePathToFile(path1);
        Path filePath2 = getAbsolutePathToFile(path2);
        Map<String, Value> dataFileMap = Parser.getDataFile(filePath1, filePath2);
        return switch (format) {
            case "json" -> JsonFormatter.format(dataFileMap);
            case "plain" -> PlainFormatter.format(dataFileMap);
            case "stylish" -> StylishFormatter.format(dataFileMap);
            default -> throw new RuntimeException(format + " this format is not supported");
        };
    }

    public static String generate(String path1, String path2) throws Exception {
        return generate(path1, path2, "stylish");
    }

    public static Path getAbsolutePathToFile(String inputPathString) throws IOException {
        Path inputPath = Path.of(inputPathString);
        Path absolutePath;
        if (inputPath.isAbsolute() || new File(inputPath.toString()).exists()) {
            return inputPath;
        } else {
            StringBuilder absolutePathSB = new StringBuilder(inputPath.toAbsolutePath().toAbsolutePath().toString());
            absolutePathSB.insert(absolutePathSB.length() - inputPathString.length(), LOCAL_PATH_INSIDE_PROJECT);
            absolutePath = Path.of(absolutePathSB.toString());
        }
        if (!(new File(absolutePath.toString()).exists())) {
            throw new NoSuchFileException("file " + inputPathString + " is not found");
        }
        return absolutePath;
    }

}
