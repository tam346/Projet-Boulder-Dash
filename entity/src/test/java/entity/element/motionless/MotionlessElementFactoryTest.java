package entity.element.motionless;

import junit.framework.TestCase;

public class MotionlessElementFactoryTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testCreateWall() {
        MotionlessElement wall = MotionlessElementFactory.createWall();
        assertNotNull(wall);
        assertTrue(wall instanceof Wall);
    }

    public void testCreateDoor() {
            MotionlessElement door = MotionlessElementFactory.createDoor();
            assertNotNull(door);
            assertTrue(door instanceof Door);
        }

    public void testCreateBackground() {
        MotionlessElement background = MotionlessElementFactory.createBackground();
        assertNotNull(background);
        assertTrue(background instanceof Space);
    }

    public void testCreateGround() {
        MotionlessElement ground = MotionlessElementFactory.createGround();
        assertNotNull(ground);
        assertTrue(ground instanceof Ground);
    }
}