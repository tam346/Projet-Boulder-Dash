package entity.element.motionless;

import entity.element.Element;
import entity.element.Permeability;
import entity.element.Sprite;

import javax.swing.*;

public class MotionlessElement extends Element {
    /**
     * Instantiates a new motionless element.
     *
     * @param sprite
     *            the sprite
     * @param permeability
     *            the permeability
     */
    MotionlessElement(final Sprite sprite, final Permeability permeability) {
        super(sprite, permeability);
    }

}