package hexlet.code;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    @BeforeAll
    static void testGet() throws IOException {
        expectedStylish = Utils.getDataFile("src/test/resources/stylishResult.txt");
        expectedPlain = Utils.getDataFile("src/test/resources/plainResult.txt");
        expectedJson = Utils.getDataFile("src/test/resources/jsonResult.json");
    }


    @Test
    void jsonInputTest() throws Exception {
        String path1 = "file1.json";
        String path2 = "file2.json";
        assertEquals(expectedStylish, Differ.generate(path1, path2, "stylish"));
        assertEquals(expectedStylish, Differ.generate(path1, path2));
        assertEquals(expectedPlain, Differ.generate(path1, path2, "plain"));
        assertEquals(expectedJson, Differ.generate(path1, path2, "json"));
    }

    @Test
    void ymlInputTest() throws Exception {
        String path1 = "file1.yml";
        String path2 = "file2.yml";
        assertEquals(expectedStylish, Differ.generate(path1, path2, "stylish"));
        assertEquals(expectedStylish, Differ.generate(path1, path2));
        assertEquals(expectedPlain, Differ.generate(path1, path2, "plain"));
        assertEquals(expectedJson, Differ.generate(path1, path2, "json"));
    }



    @Test
    void generateJsonFileWithAbsolutePath() throws Exception {
        String path1 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file1.json";
        String path2 = Utils.LOCAL_PATH_INSIDE_PROJECT + "file2.json";
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/jsonResult.json")));
        assertEquals(expected, Differ.generate(path1, path2, "json"));
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
