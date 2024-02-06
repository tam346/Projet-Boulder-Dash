package entity.element.motionless;

import entity.element.Permeability;
import entity.element.Sprite;

public class Wall extends MotionlessElement{
    /** The Constant SPRITE. */
    private static final Sprite SPRITE = new Sprite('W', "Wall.png");

    /**
     * Instantiates a new wall.
     */
    public Wall() {
        super(SPRITE, Permeability.BLOCKING );
    }
}
