package entity.element.motionless;

import entity.element.Permeability;
import entity.element.Sprite;

public class Space extends MotionlessElement{
    private static final Sprite SPRITE = new Sprite('V', "Space.png");

    /**
     * Instantiates a new background.
     */
    public Space() {
        super(SPRITE, Permeability.PENETRABLE);
    }

}
