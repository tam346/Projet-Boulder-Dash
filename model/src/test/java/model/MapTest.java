package model;

import entity.element.IElement;
import entity.element.mobile.MobileElementFactory;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import static org.junit.Assert.assertNotEquals;

public class MapTest extends TestCase {
private Map map;
@Before
    public void setUp() throws Exception {
        // Provide a valid map file path for testing
        try {
            map = new Map("C:\\Users\\princesse\\Documents\\niveau.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() throws Exception {
        // Clean up resources if needed
        map = null;
    }
    
    @Test
    public void testGetOnTheMapXY() {
        IElement element = map.getOnTheMapXY(0, 0);
        assertNotNull(element);
    }
    @Test
    public void testSetOnTheMapXY() {
        IElement element = MobileElementFactory.getFromFileSymbol('R');
        map.setOnTheMapXY(element, 0, 0);
        assertEquals(element, map.getOnTheMapXY(0, 0));
        assertTrue(map.getHasChanged().contains(0));
        assertTrue(map.getHasChanged().contains(0));
    }

    public void testGetWidth() {
        assertEquals( 21, map.getWidth());
    }
    @Test
    public void testGetHeight() {
        assertEquals( 11, map.getHeight());
    }
    @Test
    public void testGetObservable() {
        Observable observable = map.getObservable();
        assertNotNull(observable);
    }
    @Test
    public void testIsCorrect() {
        assertTrue(map.isCorrect());
    }
    @Test
    public void testSetHeight() {
        // Get the initial height
        int initialHeight = map.getHeight();

        // Set a new height
        int newHeight = 10;
        map.setHeight(newHeight);

        // Check if the height is updated
        assertEquals(newHeight, map.getHeight());

        // Check if the height is not equal to the initial height
        assertNotEquals(initialHeight, map.getHeight());
    }
    @Test
    public void testSetWidth() {
        // Get the initial width
        int initialwidth = map.getWidth();

        // Set a new height
        int newwidth = 10;
        map.setHeight(newwidth);

        // Check if the height is updated
        assertEquals(newwidth, map.getHeight());

        // Check if the height is not equal to the initial height
        assertNotEquals(initialwidth, map.getHeight());
    }
}