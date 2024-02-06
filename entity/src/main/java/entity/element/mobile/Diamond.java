package entity.element.mobile;
import entity.element.Permeability;
import entity.element.Sprite;

import java.awt.*;
import java.io.IOException;

public class Diamond extends Mobile {
    private static final Sprite SPRITE = new Sprite('D',"Diamond.png");

    /**
     * Instantiates a new obstacle.
     */
    Diamond() {
        super(SPRITE, Permeability.DIAMOND);
    }


    @Override
    public void win() {
        // TODO Auto-generated method stub
        return;
    }

    /**
     * Gets the diamonds
     */
    @Override
    public int getDiamonds() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     *Gets the diamonds
     */
    @Override
    public void grabDiamond() {
        return;
    }

}
