package entity.element;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class ElementTest {
    // Create a Sprite for testing
    Sprite expectedSprite = new Sprite('W', "Wall.png");
    // Create an Element instance for testing
    Element element = new Element( expectedSprite, Permeability.BLOCKING);
    // Call the getSprite() method
    Sprite actualSprite = element.getSprite();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetSprite() {
        // Check if the returned Sprite is the same as the expected Sprite
        Assert.assertEquals(expectedSprite, actualSprite);
    }

    @Test
    public void testSetSprite() {
        // Create an initial Sprite for testing
        Sprite initialSprite = new Sprite('W', "Wall.png");

        // Create an Element instance for testing
        Element element = new Element(initialSprite, Permeability.BLOCKING);

        // Create a new Sprite for testing
        Sprite newSprite = new Sprite('G', "Grass.png");

        // Set the new Sprite using setSprite
        element.setSprite(newSprite);

        // Check if getSprite returns the new Sprite
        Assert.assertEquals(newSprite, element.getSprite());
    }


    @Test
    public void testGetPermeability() {
        // Specify the expected Permeability
        Permeability expectedPermeability = Permeability.BLOCKING;

        // Create a Sprite for testing
        Sprite sprite = new Sprite('W', "Wall.png");

        // Create an Element instance with the specified Permeability
        Element element = new Element(sprite, expectedPermeability);

        // Check if getPermeability returns the expected Permeability
        Assert.assertEquals(expectedPermeability, element.getPermeability());
    }

    @Test
    public void testGetImage() {
            // Create a Sprite for testing
            Sprite sprite = new Sprite('W', "Wall.png");

            // Create an Element instance with the specified Sprite
            Element element = new Element(sprite, Permeability.BLOCKING);

            // Get the expected Image from the Sprite
            Image expectedImage = sprite.getImage();

            // Check if getImage() returns the expected Image
            Assert.assertEquals(expectedImage, element.getImage());
    }

    @Test
    public void testGetHasChanged() {
        // Create a Sprite for testing
        Sprite sprite = new Sprite('W', "Wall.png");

        // Create an Element instance for testing
        Element element = new Element(sprite, Permeability.BLOCKING);

        // Check if getHasChanged() initially returns false
        Assert.assertFalse(element.getHasChanged());

        // Set hasChanged to true
        element.setHasChanged(true);

        // Check if getHasChanged() returns true after setting it to true
        Assert.assertTrue(element.getHasChanged());
    }

    @Test
    public void testSetHasChanged() {
        // Create a Sprite for testing
        Sprite sprite = new Sprite('W', "Wall.png");

        // Create an Element instance for testing
        Element element = new Element(sprite, Permeability.BLOCKING);

        // Set hasChanged to true
        element.setHasChanged(true);

        // Check if getHasChanged() returns true after setting it to true
        Assert.assertTrue(element.getHasChanged());

        // Set hasChanged to false
        element.setHasChanged(false);

        // Check if getHasChanged() returns false after setting it to false
        Assert.assertFalse(element.getHasChanged());
    }
}