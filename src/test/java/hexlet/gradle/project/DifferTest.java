package hexlet.gradle.project;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DifferTest {
    private String stylishExpected;
    private String plainExpected;
    private String jsonExpected;

    @BeforeEach
    void beforeEach() throws IOException {
        stylishExpected = Utils.readFile("src/test/resources/stylishExpected.txt");
        plainExpected = Utils.readFile("src/test/resources/plainExpected.txt");
        jsonExpected = Utils.readFile("src/test/resources/jsonExpected.txt");
    }

    @Test
    void generateJsonFileInputStylishFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        assertEquals(stylishExpected, Differ.generate(path1, path2, "stylish"));
    }

    @Test
    void generateYmlFileInputWithoutFormat() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";
        assertEquals(stylishExpected, Differ.generate(path1, path2));
    }

    @Test
    void generateJsonFileInputPlainFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        assertEquals(plainExpected, Differ.generate(path1, path2, "plain"));
    }

    @Test
    void generateJsonFileInputJsonFormatOutput() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        assertEquals(jsonExpected, Differ.generate(path1, path2, "json"));
    }

    @Test
    void generateJsonFileWithAbsolutePath() throws Exception {
        String path1 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file1.json";
        String path2 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file2.json";
        assertEquals(jsonExpected, Differ.generate(path1, path2, "json"));
    }

    @Test
    void exceptionsNoFileTest() {
        Throwable thrown = catchThrowable(() -> Differ.generate("json1", "file2"));
        assertThat(thrown).isInstanceOf(IOException.class);
    }

    @Test
    void exceptionsUnsupportedFormatTest() {
        Throwable thrown = catchThrowable(() -> Differ.generate("file1.json", "file2.json", "yml"));
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void exceptionsInputFormatIsNotSupported() {
        Throwable thrown = catchThrowable(() -> Differ.generate("file.txt", "file.txt"));
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

}
