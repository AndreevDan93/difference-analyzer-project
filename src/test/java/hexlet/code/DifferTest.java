package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DifferTest {

    @Test
    void generate() throws Exception {
        String path1 = "/home/andreevdan83/Hexlet/Project2/app/src/test/resources/file1.json";
        String path2 = "/home/andreevdan83/Hexlet/Project2/app/src/test/resources/file2.json";
        String result = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        assertEquals(Differ.generate(path1,path2,"format"),result);
    }
}