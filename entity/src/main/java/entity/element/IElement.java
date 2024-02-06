package entity.element;

//import fr.exia.showboard.*;


import fr.exia.showboard.ISquare;

import javax.swing.*;
import java.awt.*;

public interface IElement extends ISquare {
    /**
     * Gets the sprite.
     *
     * @return the sprite
     */
    Sprite getSprite();

    /**
     * Gets the permeability.
     *
     * @return the permeability
     */
    Permeability getPermeability();

    Boolean getHasChanged();

    void setHasChanged(Boolean hasChanged);
}