package entity.element.mobile;
import entity.element.Permeability;
import entity.element.Sprite;

import java.awt.*;

public class Rock extends Mobile{
    private Permeability permeability;
    private static final Sprite SPRITE = new Sprite('R', "Rock.png");

    public Rock() {
        super(SPRITE,Permeability.MOVABLE  );
    }
    @Override
    public void moveRight() {
        super.moveRight();
    }

    @Override
    public void win() {
        // TODO Auto-generated method stub
        return;
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
    }

    @Override
    public void moveDown() {
        super.moveDown();
    }

    @Override
    public int getDiamonds() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     *Gets the diamonds
     */
    @Override
    public void grabDiamond() {
        return;
    }

    @Override
    public Boolean getHasChanged() {
        return null;
    }

    @Override
    public void setHasChanged(Boolean hasChanged) {

    }
}