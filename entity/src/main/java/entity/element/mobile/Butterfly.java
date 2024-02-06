package entity.element.mobile;

import entity.element.Permeability;
import entity.element.Sprite;

import java.awt.*;

public class Butterfly extends Mobile{
    private Permeability permeability;
    private static final Sprite SPRITE = new Sprite('B',"Butterfly.png");

    public Butterfly() {
        super(SPRITE, Permeability.KILLABLE);
    }

    @Override
    public void win() {
        // TODO Auto-generated method stub
        return;
    }

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

