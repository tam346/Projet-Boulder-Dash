package model;

import contract.IModel;
import entity.element.IMap;
import entity.element.mobile.IMobile;
import entity.element.mobile.Player;

import java.io.IOException;

/**
 * The Class Main.
 *
 * @author Denise
 */

public class Model implements IModel {
    /** The map. */
    private IMap map;

    /** The player. */
    private IMobile myPlayer;

    /**
     * Instantiates a new boulder dash model.
     *
     * @param myPlayerStartX  my player start X
     * @param myPlayerStartY  my player start Y
     * @throws IOException ignals that an I/O exception has occurred.
     */
    public Model(final String mapFile, final int myPlayerStartX, final int myPlayerStartY) {
        try {
            this.setMap(new Map(mapFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.setMyPlayer(new Player(myPlayerStartX, myPlayerStartY, this.getMap()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the map
     */
    @Override
    public final IMap getMap() {
        return this.map;
    }

    /**
     * Sets the map.
     *
     * @param map the map to set
     */
    private void setMap(final IMap map) {
        this.map = map;
    }

    /**
     * gets the player
     */
    @Override
    public final IMobile getMyPlayer() {
        return this.myPlayer;
    }

    /**
     * Sets the player.
     *
     * @param myPlayer the player to set
     */
    private void setMyPlayer(final IMobile myPlayer) {
        this.myPlayer = myPlayer;
    }

}