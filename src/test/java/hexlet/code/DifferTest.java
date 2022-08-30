package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    
    @Test
    void generate() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        String result = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(Differ.generate(path1, path2, "format"), result);
    }

}
