package controller;


import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import entity.element.Permeability;
import entity.element.mobile.MobileElementFactory;
import entity.element.motionless.MotionlessElementFactory;

import java.util.Random;

/**
 @author Denise
 */
public final class Controller implements IController {
    /**
     * The speed
     */
    private static final int speed = 200;

    /**
     * The view
     */
    private IView view;

    /**
     * The model
     */
    private IModel model;

    /**
     * stakckOrder
     */
    private ControllerOrder stackOrder;

    /**
     * The diamonds counter
     */
    private final int diamondGoal = 4;

    /**
     * The diamonds counter
     */
    private boolean hasWon = false;

    /**
     * The random
     */
    private final Random rand = new Random();

    /**
     * The diretion for Red Monsters
     */
    private int rdirection;

    /**
     * The diretion for Green Monsters
     */
    private int gDirection;

    private boolean canChange = true;


    /**
     * @param view
     * @param model
     */
    public Controller(final IView view, final IModel model) {
        this.setView(view);
        this.setModel(model);
        this.clearStackOrder();
    }

    /**
     * Play method
     */
    @Override
    public final void play() {
        while (this.getModel().getMyPlayer().isAlive() && !hasWon) {

            // --- Game Speed ---
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // --- Player ---
            this.movePlayer();
            this.killPlayer();
            this.winPlayer();

            gDirection = gDirection + 1;
            rdirection = rand.nextInt(4);

            // --- Update Mobile Entities on Map
            for (int y = this.getModel().getMap().getHeight() - 1; y > 0; y--) {
                for (int x = this.getModel().getMap().getWidth() - 1; x > 0; x--) {

                    for (int g = 0; g < this.getModel().getMap().getHasChanged().size(); g = g + 2) {
                        if (this.getModel().getMap().getHasChanged().get(g) == x || this.getModel().getMap().getHasChanged().get(g + 1) == y) {
                            this.canChange = false;
                        }
                    }

                    if (canChange) {
                        // --- Boulders ---
                        this.gravityBoulder(x, y);
                        this.gravityDiagBoulder(x, y);

                        // --- Diamonds ---
                        this.gravityDiamond(x, y);
                        this.gravityDiagDiamond(x, y);

                        // --- Monsters ---
                        this.killMonster(x, y);
                        this.moveRMonster(x, y);
                    }
                    this.canChange = true;
                }
            }

            // --- Update View ---
            this.getModel().getMap().getHasChanged().clear();
            this.canChange = true;
            this.getView().followMyPlayer();
            this.getView().updateView();
            this.getView().getBoardFrame().setTitle("BoulderDash - Diamond Counter: " + this.getModel().getMyPlayer().getDiamonds());
        }
    }


    /**
     * movePlayer method
     */
    public void movePlayer() {
        switch (this.getStackOrder()) {
            case UP:
                this.getModel().getMyPlayer().moveUp();

                break;
            case DOWN:
                this.getModel().getMyPlayer().moveDown();

                break;
            case RIGHT:
                this.getModel().getMyPlayer().moveRight();

                break;
            case LEFT:
                this.getModel().getMyPlayer().moveLeft();

                break;
            case NOP:
            default:
                this.getModel().getMyPlayer().doNothing();
                break;
        }
        this.clearStackOrder();
    }

    /**
     * killPlayer method
     */
    public void killPlayer() {
        if ((this.getModel().getMap().getOnTheMapXY((this.getModel().getMyPlayer().getX()), ((this.getModel().getMyPlayer().getY()))).getPermeability() == Permeability.KILLABLE )) {
            // Mark the player as dead and display a game-over message
            this.getModel().getMyPlayer().die();
            this.getView().displayMessage("Game Over! Do you want to retry?");
        }
    }
    /**
     * winPlayer method
     */
    public void winPlayer() {
        if ((this.getModel().getMap().getOnTheMapXY((this.getModel().getMyPlayer().getX()), ((this.getModel().getMyPlayer().getY()))).getPermeability() == Permeability.GAMEOVER) && this.getModel().getMyPlayer().getDiamonds() >= diamondGoal) {
            this.hasWon = true;
            this.getModel().getMyPlayer().win();
            this.getView().displayMessage("You won, Congratulations! Do you to replay?");
        }
    }


    /**
     * Boulders Gravity
     */
    public void gravityBoulder(int x, int y) {
        // Check if the cell below the boulder is movable and the cell two below is penetrable
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.MOVABLE &&
                this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.PENETRABLE) {
            // Check if the boulder is two cells above the player
            if (x == this.getModel().getMyPlayer().getX() && y + 2 == this.getModel().getMyPlayer().getY()) {
                // The Rock is two case above the player
                try {
                    Thread.sleep(1000); // Delay before he dies
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Mark the player as dead and display a game-over message
                this.getModel().getMyPlayer().die();
                this.getView().displayMessage("Game Over! Do you want to retry?");
            } else {
                // Move the boulder down by one cell
                if (x != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                    this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
                    this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createRock(), x, y + 1);
                }
            }
        }
    }

    /**
     * Boulders Diagonal Gravity
     */
    public void gravityDiagBoulder(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.MOVABLE && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.MOVABLE) {
            if (this.getModel().getMap().getOnTheMapXY(x + 1, y + 1).getPermeability() == Permeability.PENETRABLE && this.getModel().getMap().getOnTheMapXY(x + 1, y).getPermeability() == Permeability.PENETRABLE) {
                if (x + 1 != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                    if (x + 1 != this.getModel().getMyPlayer().getX() || y != this.getModel().getMyPlayer().getY()) {
                        this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
                        this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createRock(), x + 1, y);
                        // The Rock is falling diagonally
                        try {
                            Thread.sleep(1000); // Delay before he dies
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.MOVABLE && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.MOVABLE) {
            if (this.getModel().getMap().getOnTheMapXY(x - 1, y + 1).getPermeability() == Permeability.PENETRABLE && this.getModel().getMap().getOnTheMapXY(x - 1, y).getPermeability() == Permeability.PENETRABLE) {
                if (x - 1 != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                    if (x - 1 != this.getModel().getMyPlayer().getX() || y != this.getModel().getMyPlayer().getY()) {
                        this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
                        this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createRock(), x - 1, y);
                        // The Rock is falling diagonally
                        try {
                            Thread.sleep(1000); // Delay before he dies
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * Diamonds Gravity
     */
    public void gravityDiamond(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.DIAMOND && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.PENETRABLE) {
            if (x == this.getModel().getMyPlayer().getX() && y + 2 == this.getModel().getMyPlayer().getY()) {
                // The Diamond is two case above the player
                try {
                    Thread.sleep(1000); // Delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.getModel().getMyPlayer().die();
                this.getView().displayMessage("Game Over! Do you want to retry?");
            } else {
                if (x != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                    this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
                    this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createDiamond(), x, y + 1);
                }
            }
        }
    }

    /**
     * Diamonds Diagonal Gravity
     */
    public void gravityDiagDiamond(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.DIAMOND && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.DIAMOND) {
            if (this.getModel().getMap().getOnTheMapXY(x + 1, y + 1).getPermeability() == Permeability.PENETRABLE && this.getModel().getMap().getOnTheMapXY(x + 1, y).getPermeability() == Permeability.PENETRABLE) {
                this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
                this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createDiamond(), x + 1, y);
                // The Diamond is falling diagonally
                try {
                    Thread.sleep(1000); // Delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.DIAMOND && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.DIAMOND) {
            if (this.getModel().getMap().getOnTheMapXY(x - 1, y + 1).getPermeability() == Permeability.PENETRABLE && this.getModel().getMap().getOnTheMapXY(x - 1, y).getPermeability() == Permeability.PENETRABLE) {
                this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
                this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createDiamond(), x - 1, y);
                // The Diamond is falling diagonally
                try {
                    Thread.sleep(1000); // Delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * killMonster method
     */
    public void killMonster(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.KILLABLE && this.getModel().getMap().getOnTheMapXY(x, y - 1).getPermeability() == Permeability.MOVABLE || this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.KILLABLE && this.getModel().getMap().getOnTheMapXY(x, y - 1)
                .getPermeability() == Permeability.DIAMOND) {
            this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createDiamond(), x, y);

            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (getModel().getMap().getOnTheMapXY(i, j).getPermeability() == Permeability.PENETRABLE) {
                        this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createDiamond(), i, j);
                    }
                }
            }
        }
    }

    /**
     * Butterfly Move method
     *
     */
    public void moveRMonster(int x, int y) {
        switch (rdirection) {
            case 1:
                MRMoveRight(x, y);
                break;
            case 0:
                MRMoveLeft(x, y);
                break;
            case 2:
                MRMoveUp(x, y);
                break;
            case 3:
                MRMoveDown(x, y);
                break;

            default:
                break;
        }
    }

    /**
     * Monster Move Right method
     */
    public void MRMoveRight(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'B' && this.getModel().getMap().getOnTheMapXY(x + 1, y).getPermeability() == Permeability.PENETRABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createMonsterR(), x + 1, y);
        }
    }

    /**
     * MonsterR Move Left method
     */
    public void MRMoveLeft(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'B' && this.getModel().getMap().getOnTheMapXY(x - 1, y).getPermeability() == Permeability.PENETRABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createMonsterR(), x - 1, y);
        }
    }

    /**
     * MonsterR Move Up method
     */
    public void MRMoveUp(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'B' && this.getModel().getMap().getOnTheMapXY(x, y - 1).getPermeability() == Permeability.PENETRABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createMonsterR(), x, y - 1);
        }
    }

    /**
     * MonsterR Move Down method
     */
    public void MRMoveDown(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'B' && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.PENETRABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementFactory.createMonsterR(), x, y + 1);
        }
    }


    /**
     * @return this.view
     */
    private IView getView() {
        return this.view;
    }

    /**
     * @param view
     */
    private void setView(final IView view) {
        this.view = view;
    }

    /**
     * @return this.model
     */
    private IModel getModel() {
        return this.model;
    }

    /**
     * @param model
     */
    private void setModel(final IModel model) {
        this.model = model;
    }

    /**
     * @return this.stackOrder
     */
    ControllerOrder getStackOrder() {
        return this.stackOrder;
    }

    /**
     * @param stackOrder
     */
    private void setStackOrder(final ControllerOrder stackOrder) {
        this.stackOrder = stackOrder;
    }

    /**
     * clearStackOrder method
     */
    private void clearStackOrder() {
        this.stackOrder = ControllerOrder.NOP;
    }

    /**
     * getOrderPerformer method
     */
    @Override
    public IController getOrderPerformer() {
        return this;
    }

    /**
     * orderPerform method
     */
    @Override
    public final void orderPerform(final ControllerOrder userOrder) {
        this.setStackOrder(userOrder);
    }


}
