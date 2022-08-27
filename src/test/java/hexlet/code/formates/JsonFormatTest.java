package hexlet.code.formates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;

class JsonFormatTest {

    @Test
    void fileToMapTest() throws Exception {
        Path path1 = Path.of("/home/andreevdan83/Hexlet/Project2/app/src/test/resources/file1.json");
        Map<String,Object> result1 = new HashMap<>();
        result1.put("host","hexlet.io");
        result1.put("timeout",50);
        result1.put("proxy","123.234.53.22");
        result1.put("follow",false);
        assertEquals(JsonFormat.fileToMap(path1),result1);
    }
}