package entity.element.mobile;

import entity.element.IMap;
import entity.element.Permeability;
import entity.element.Sprite;

public class Boom extends Mobile{
    private static final Sprite SPRITE = new Sprite('F', "Boom.png");
    public Boom() {
        super(SPRITE, Permeability.GAMEOVER);
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
}
