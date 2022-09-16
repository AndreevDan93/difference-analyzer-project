package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DifferTest {

    @Test
    void generateJsonFileInputStylishFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        Differ differ = new Differ(path1, path2, "stylish");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void generateYmlFileInputWithoutFormat() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";
        Differ differ = new Differ(path1, path2);
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test1.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void generateJsonFileInputPlainFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        Differ differ = new Differ(path1, path2, "plain");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test3.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void generateJsonFileInputJsonFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        Differ differ = new Differ(path1, path2, "json");
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/test4.txt")));
        assertEquals(expected, differ.generate());
    }

    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> {
            Differ differ = new Differ("json1", "file2");
            differ.generate();
        });
        assertThat(thrown).isInstanceOf(IOException.class);
    }
    @Test
    void exceptionsDifferentExtensionTest() {
        Throwable thrown = catchThrowable(() -> {
            Differ differ = new Differ("file1.json", "file2.yml");
            differ.generate();
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
