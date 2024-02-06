package model;

import entity.element.IMap;
import entity.element.mobile.IMobile;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class ModelTest {
    private Model model;

    @Before
    public void setUp() throws Exception {
        try {
            this.model = new Model("C:" + File.separator + "Users" + File.separator + "princesse" + File.separator + "Documents" +  File.separator + "niveau.txt", 0, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        // Clean up resources if needed
        model = null;
    }

    @Test
    public void testGetMap() {
        IMap map = model.getMap();
        Assert.assertNotNull(map);
    }
    @Test
    public void testGetMyPlayer() {
        IMobile player = model.getMyPlayer();
        Assert.assertNotNull(player);
    }
}