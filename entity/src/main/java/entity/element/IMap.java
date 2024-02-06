package entity.element;


import java.util.List;
import java.util.Observable;

public interface IMap {
    /**
     * Gets the width.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Gets the height.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Gets on the map XY.
     *
     * @param x
     *            the x
     * @param y
     *            the y
     * @return the on the road XY
     */
    IElement getOnTheMapXY(int x, int y);

    /**
     * Sets on the Map XY
     * @param element
     * @param x
     * @param y
     */
    void setOnTheMapXY(IElement element, final int x, final int y);

    /**
     * Sets the mobile has changed.
     */
    void setMobileHasChanged();

    /**
     * Gets the observable.
     *
     * @return the observable
     */
    Observable getObservable();

    boolean isCorrect();

    List<Integer> getHasChanged();


}
