package entity.element.motionless;

import entity.element.Permeability;
import entity.element.Sprite;

public class Door extends MotionlessElement {
    private static final Sprite SPRITE = new Sprite('E', "Door.png");


    public Door() {
        super(SPRITE, Permeability.GAMEOVER);
    }
}
