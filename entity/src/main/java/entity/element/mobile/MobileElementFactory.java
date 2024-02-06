package entity.element.mobile;

import java.io.IOException;

public class MobileElementFactory {
    /**
     * Butterfly
     */
    private final static Butterfly monsterR = new Butterfly();
    /**
     * Diamond
     */
    private final static Diamond diamond = new Diamond();
    /**
     * The rock
     */
    private final static Rock boulder = new Rock();
    private final static Boom boom = new Boom();

    /**
     * The list of mobile elements
     */
    private static Mobile[] mobileElements = { monsterR, diamond, boulder, boom};

    /**
     * Gets char from file
     * @param fileSymbol
     * @return
     */
    public static Mobile getFromFileSymbol(final char fileSymbol) {
        for (final Mobile mobileElement : mobileElements) {
            if (mobileElement.getSprite().getConsoleImage() == fileSymbol) {
                return mobileElement;
            }
        }
        return boulder;
    }
    /**
     * creates Red Monster
     * @return Red Monster
     */
    public static Mobile createMonsterR() {
        return monsterR;
    }
    /**
     * creates diamond
     * @return
     */
    public static Mobile createDiamond() {
        return diamond;
    }

    /**
     * creates rock
     * @return
     */
    public static Mobile createRock() {
        return boulder;
    }
    /**
     * creates rock
     * @return
     */
    public static Mobile createBoom() {
        return boom;
    }

}
