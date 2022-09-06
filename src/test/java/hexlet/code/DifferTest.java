package hexlet.code;


import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void generateJsonFileInputStylishFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, Differ.generate(path1, path2, "stylish"));
    }

    @Test
    void generateYmlFileInputWithoutFormat() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, Differ.generate(path1, path2));
    }

    @Test
    void generateJsonFileInputPlainFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test3.txt")));
        assertEquals(expected, Differ.generate(path1, path2, "plain"));
    }

    @Test
    void generateJsonFileInputJsonFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, Differ.generate(path1, path2, "json"));
    }

}
