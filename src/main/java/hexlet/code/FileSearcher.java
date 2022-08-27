package hexlet.code;

import java.io.File;
import java.nio.file.Path;

public class FileSearcher {
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
