package entity.element.mobile;

import entity.element.IMap;
import entity.element.Permeability;
import entity.element.Sprite;
import entity.element.motionless.MotionlessElementFactory;

import java.awt.*;
import java.io.IOException;

public class Player extends Mobile{
    private static final Sprite sprite = new Sprite('P', "Stable.png");
    private static final Sprite spriteTurnLeft = new Sprite('P', "Left1.png");
    private static final Sprite spriteTurnRight = new Sprite('P', "Right1.png");
    /** The Constant spriteTurnUp. */
    private static final Sprite spriteTurnUp = new Sprite('P', "Up1.png");
    /** The Constant spriteTurnDown. */
    private static final Sprite spriteTurnDown = new Sprite('P', "Down1.png");
    /** The Constant spriteDead. */
    private static final Sprite spriteDead = new Sprite('P', "Died.png");
    /** The Constant spriteWin. */
    private static final Sprite spriteWin = new Sprite('P', "Win.png");

    /** The Diamond counter. */
    private int nb_diamonds;

    /**
     * Instantiates a new my player.
     *
     * @param x   the x
     * @param y   the y
     * @param map the map
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public Player(final int x, final int y, final IMap map) throws IOException {
        super(x, y, sprite, map, Permeability.BLOCKING);
        spriteTurnLeft.loadImage();
        spriteTurnRight.loadImage();
        spriteTurnUp.loadImage();
        spriteTurnDown.loadImage();
        spriteDead.loadImage();
        spriteWin.loadImage();
    }


    /**
     * move left for player
     *
     */
    @Override
    public final void moveLeft() {
        if ((getMap().getOnTheMapXY((getX() - 1), ((getY()))).getPermeability() == Permeability.BLOCKING) || (getMap().getOnTheMapXY((getX() - 1), ((getY()))).getPermeability() == Permeability.MOVABLE)) {
            doNothing();
            pushBoulderLeft();
            this.setSprite(spriteTurnLeft);
        } else {
            super.moveLeft();
            this.setSprite(spriteTurnLeft);
            dig();
            grabDiamond();
            this.setHasMoved();
        }
    }

    /**
     * move right for player
     */
    @Override
    public final void moveRight() {
        if (getMap().getOnTheMapXY(getX() + 1, getY()).getPermeability() == Permeability.BLOCKING || (getMap().getOnTheMapXY((getX() + 1), ((getY()))).getPermeability() == Permeability.MOVABLE)) {
            doNothing();
            pushBoulderRight();
            this.setSprite(spriteTurnRight);
        } else {
            super.moveRight();
            this.setSprite(spriteTurnRight);
            dig();
            grabDiamond();
            this.setHasMoved();
        }
    }

    /**
     * move down for player
     */
    public final void moveDown() {
        if ((getMap().getOnTheMapXY((getX()), (getY() + 1)).getPermeability() != Permeability.BLOCKING) && (getMap().getOnTheMapXY((getX()), (getY() + 1))).getPermeability() != Permeability.MOVABLE) {
            super.moveDown();
            this.setSprite(spriteTurnDown);
            dig();
            grabDiamond();
            this.setHasMoved();
        } else {
            doNothing();
        }
    }

    /**
     * move up for the player
     */
    public final void moveUp() {
        if ((getMap().getOnTheMapXY((getX()), (getY() - 1)).getPermeability() != Permeability.BLOCKING) && (getMap().getOnTheMapXY((getX()), (getY() - 1))).getPermeability() != Permeability.MOVABLE) {
            grabDiamond();
            super.moveUp();
            this.setSprite(spriteTurnUp);
            dig();
            grabDiamond();
            this.setHasMoved();
        } else {
            doNothing();
        }
    }

    /**
     * player dies
     *
     */
    @Override
    public final void die() {
        super.die();
        this.setSprite(spriteDead);
    }

    /**
     * player wins
     *
     */
    @Override
    public final void win() {
        this.setSprite(spriteWin);
    }

    /**
     * player does nothing
     *
     */
    @Override
    public final void doNothing() {
        super.doNothing();
        this.setSprite(sprite);
        this.dig();
        this.grabDiamond();
    }

    /**
     * removes ground
     */
    public void dig() {
        if (this.getMap().getOnTheMapXY(this.getX(), this.getY()).getPermeability() == Permeability.DIGGABLE) {
            this.getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), this.getX(), this.getY());
            this.setHasMoved();
        }
    }


    /**
     * gets the diamond
     */
    public void grabDiamond() {
        if (this.getMap().getOnTheMapXY(this.getX(), this.getY()).getPermeability() == Permeability.DIAMOND) {
            this.getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), this.getX(), this.getY());
            nb_diamonds++;
        }
    }

    /**
     * push rock to right
     */
    public void pushBoulderRight() {
        if (this.getMap().getOnTheMapXY(this.getX() + 1, this.getY()).getPermeability() == Permeability.MOVABLE && this.getMap().getOnTheMapXY(this.getX() + 2, this.getY()).getPermeability() == Permeability.PENETRABLE) {
            this.getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), this.getX() + 1, this.getY());
            super.moveRight();
            this.getMap().setOnTheMapXY(MobileElementFactory.createRock(), this.getX() + 1, this.getY());
            this.setHasMoved();
        }
    }

    /**
     * push rock to left
     */
    public void pushBoulderLeft() {
        if (this.getMap().getOnTheMapXY(this.getX() - 1, this.getY()).getPermeability() == Permeability.MOVABLE && this.getMap().getOnTheMapXY(this.getX() - 2, this.getY()).getPermeability() == Permeability.PENETRABLE) {
            this.getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), this.getX() - 1, this.getY());
            super.moveLeft();
            this.getMap().setOnTheMapXY(MobileElementFactory.createRock(), this.getX() - 1, this.getY());
            this.setHasMoved();
        }
    }

    /**
     * gets the diamond
     */
    public int getDiamonds() {
        return nb_diamonds;
    }
}