package leetcode.graphs.bfs.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestResourceUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private TestResourceUtils() {
        // prevent instantiation
    }

    /**
     * Loads a 2D int array from a JSON resource file on the test classpath.
     *
     * @param resourcePath path relative to classpath root (e.g. "/fixtures/case1.json")
     * @return 2D int array parsed from JSON
     */
    public static int[][] loadMatrix(String resourcePath) throws IOException {
        try (InputStream is = TestResourceUtils.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            return mapper.readValue(is, int[][].class);
        }
    }
}
