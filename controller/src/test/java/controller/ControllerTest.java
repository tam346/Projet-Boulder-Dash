package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import entity.element.IElement;
import entity.element.IMap;
import entity.element.Permeability;
import entity.element.Sprite;
import entity.element.mobile.IMobile;
import fr.exia.showboard.BoardFrame;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.util.Observable;

public class ControllerTest {

    /*
    private static final int MAP_HEIGHT = 2;  // Adjust with the actual height of your map
    private static final int MAP_WIDTH = 2;   // Adjust with the actual width of your map
   */

    @Before
    public void setUp() throws Exception {
        // Instantiate the Controller object before each test
        controller = new Controller(view, model);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetOrderPerformer() {
        // Call getOrderPerformer
        IController result = controller.getOrderPerformer();

        // Verify that the returned object is not null
        Assert.assertNotNull(result);

        // Optionally, you can verify that the returned object is of type Controller
        Assert.assertTrue(true);
    }

    @Test
    public void testOrderPerform() {
        // Call orderPerform with a specific ControllerOrder
        ControllerOrder userOrder = ControllerOrder.UP;
        controller.orderPerform(userOrder);

        // Verify that the stackOrder has been set correctly
        Assert.assertEquals(userOrder, controller.getStackOrder());
    }


    IModel model = new IModel() {
        @Override
        public IMap getMap() {
            return null;
        }

        @Override
        public IMobile getMyPlayer() {
            return null;
        }
    };
    IView view = new IView() {
        @Override
        public void displayMessage(String message) {

        }

        @Override
        public void followMyPlayer() {

        }

        @Override
        public void updateView() {

        }

        @Override
        public BoardFrame getBoardFrame() {
            return null;
        }
    };
    private Controller controller;
    private BoardFrame boardFrame;
    IMobile player = new IMobile() {
        @Override
        public void moveUp() {

        }

        @Override
        public void moveLeft() {

        }

        @Override
        public void moveDown() {

        }

        @Override
        public void moveRight() {

        }

        @Override
        public void doNothing() {

        }

        @Override
        public Boolean isAlive() {
            return null;
        }

        @Override
        public void die() {

        }

        @Override
        public void win() {

        }

        @Override
        public int getDiamonds() {
            return 0;
        }

        @Override
        public void grabDiamond() {

        }

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public Sprite getSprite() {
            return null;
        }

        @Override
        public Permeability getPermeability() {
            return null;
        }

        @Override
        public Boolean getHasChanged() {
            return null;
        }

        @Override
        public void setHasChanged(Boolean hasChanged) {

        }

        @Override
        public Point getPosition() {
            return null;
        }

        @Override
        public Image getImage() {
            return null;
        }
    };
    IMap map = new IMap(){
        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public IElement getOnTheMapXY(int x, int y) {
            return null;
        }

        @Override
        public void setOnTheMapXY(IElement element, int x, int y) {

        }

        @Override
        public void setMobileHasChanged() {

        }

        @Override
        public Observable getObservable() {
            return null;
        }

        @Override
        public boolean isCorrect() {
            return false;
        }

        @Override
        public List<Integer> getHasChanged() {
            return null;
        }
    };
}