package view;

import contract.ControllerOrder;
import contract.IController;
import entity.element.IMap;
import entity.element.mobile.Mobile;
import entity.element.mobile.IMobile;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ViewTest {

    private View view;
    private IMap map;
    private IMobile player;
    private IController orderPerformer;

    @Before
    public void setUp() {
        //map = createMockMap(); // Créez une implémentation de test pour IMap
        //player = createMockPlayer(); // Créez une implémentation de test pour IMobile
       // orderPerformer = createMockController(); // Créez une implémentation de test pour IController
        //view = new View(map, player);
        //view.setOrderPerformer(orderPerformer);

    }

    @Test
    public void testKeyCodeToUserOrder() {
        int keyCode = KeyEvent.VK_RIGHT;
        ControllerOrder expectedOrder = ControllerOrder.RIGHT;

        ControllerOrder result = view.keyCodeToUserOrder(keyCode);

        assertEquals(expectedOrder, result);
    }

    // Méthodes utilitaires de création de mocks pour les dépendances

    private IMap createMockMap() {
        // Implémentation de test de l'interface IMap
        return map;
    }

    private IMobile createMockPlayer() {
        // Implémentation de test de l'interface IMobile
        return null;
    }

    private IController createMockController() {
        // Implémentation de test de l'interface IController
        return null;
    }
}