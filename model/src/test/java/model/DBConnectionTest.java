package model;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConnectionTest extends TestCase {
    private static final String TEST_DB_URL = "jdbc:mysql://localhost/javaproject";
    private static final String TEST_DB_LOGIN = "root";
    private static final String TEST_DB_PASSWORD = "Or!gam!554";
    private static final String TEST_FILE_NAME = "niveau.txt";

    private DBConnection dbConnection;
    @Before
    public void setUp() throws Exception {
        dbConnection = new DBConnection();
        // Ensure that the connection is open before each test
        assertTrue(dbConnection.open());
    }
    @After
    public void tearDown() throws Exception {
        // Close the connection after each test
        dbConnection.close();
    }
    @Test
    public void testOpen() {
        // The connection is already opened in the setup method, so just assert true
        assertTrue(true);
    }

    @Test
    public void testLoadBD() {
        // The connection is already opened in the setup method
        List<String> mapLines = dbConnection.loadBD("map1");
        assertNotNull(mapLines);
        assertFalse(mapLines.isEmpty());
    }
    @Test
    public void testSaveToFile() {
        // Given
        List<String> mapLines = Arrays.asList("line1", "line2", "line3");

        // When
        dbConnection.saveToFile(mapLines, TEST_FILE_NAME);

        // Then
        List<String> loadedLines = readLinesFromFile(TEST_FILE_NAME);
        assertNotNull(loadedLines);
        assertEquals(mapLines.size(), loadedLines.size());

        for (int i = 0; i < mapLines.size(); i++) {
            assertEquals(mapLines.get(i), loadedLines.get(i));
        }
    }

    // Utility method to read lines from a file
    @Test
    private List<String> readLinesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<String> lines = new java.util.ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Utility method to delete the test file
    @Test
    private void deleteTestFile() {
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}