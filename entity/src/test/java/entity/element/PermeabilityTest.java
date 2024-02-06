package entity.element;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PermeabilityTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrdinal() {
        // Test the ordinal values of the enumeration
        assertEquals(0, Permeability.BLOCKING.ordinal());
        assertEquals(1, Permeability.PENETRABLE.ordinal());
        assertEquals(2, Permeability.KILLABLE.ordinal());
        assertEquals(3, Permeability.DIAMOND.ordinal());
        assertEquals(4, Permeability.DIGGABLE.ordinal());
        assertEquals(5, Permeability.MOVABLE.ordinal());
        assertEquals(6, Permeability.GAMEOVER.ordinal());
    }

    @Test
    public void testValues() {
        // Test the values of the enumeration
        assertEquals(7, Permeability.values().length);

        assertEquals(Permeability.BLOCKING, Permeability.valueOf("BLOCKING"));
        assertEquals(Permeability.PENETRABLE, Permeability.valueOf("PENETRABLE"));
        assertEquals(Permeability.KILLABLE, Permeability.valueOf("KILLABLE"));
        assertEquals(Permeability.DIAMOND, Permeability.valueOf("DIAMOND"));
        assertEquals(Permeability.DIGGABLE, Permeability.valueOf("DIGGABLE"));
        assertEquals(Permeability.MOVABLE, Permeability.valueOf("MOVABLE"));
        assertEquals(Permeability.GAMEOVER, Permeability.valueOf("GAMEOVER"));
    }

}