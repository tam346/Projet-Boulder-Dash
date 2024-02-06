package entity.element.motionless;

import entity.element.Permeability;
import entity.element.Sprite;

import javax.swing.*;
import java.io.IOException;

public class Ground extends MotionlessElement {
    private Permeability permeability;
    private static final Sprite SPRITE = new Sprite('G', "Ground.png");
    /**
     * Instantiates a new ground.
     */
    Ground() {
        super(SPRITE, Permeability.DIGGABLE);
    }
}
