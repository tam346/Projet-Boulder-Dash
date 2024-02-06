package entity.element.mobile;

import entity.element.IElement;
import fr.exia.showboard.*;

public interface IMobile extends IPawn, IElement {
    /**
     * Move up.
     */
    void moveUp();

    /**
     * Move left.
     */
    void moveLeft();

    /**
     * Move down.
     */
    void moveDown();

    /**
     * Move right.
     */
    void moveRight();

    /**
     * Do nothing.
     */
    void doNothing();

    /**
     * Checks if is alive.
     *
     * @return the alive
     */
    Boolean isAlive();


    /**
     * Die
     */
    public void die();

    /**
     * Win
     */
    public void win();

    /**
     * Gets diamonds
     *
     * @return int
     */
    public int getDiamonds();

    /**
     * Gets diamond
     *
     * @return int
     */
    public void grabDiamond();

    int getX();

    int getY();
}
