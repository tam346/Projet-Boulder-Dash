package model;
import java.io.BufferedReader;

import entity.element.IElement;
import entity.element.IMap;
import entity.element.mobile.MobileElementFactory;
import entity.element.motionless.MotionlessElementFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * <h1>The Map Class.</h1>
 *
 * @author Denise
 */

public class Map extends Observable implements IMap {

    /** The width. */
    private int width;

    /** The height. */
    private int height;

    /** The on the map. */
    private IElement[][] onTheMap;

    private boolean isCorrect = true;

    private List<Integer> hasChanged = new ArrayList<Integer>();

    /**
     * Instantiates a new map with the content of the file fileName.
     *
     * @param fileName the file name where the map is
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public Map(final String fileName) throws IOException {
        super();
        this.onTheMap = new IElement[2][2];
        this.loadFile(fileName);
    }

    /**
     * Loads file.
     *
     * @param fileName the file name
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void loadFile(final String fileName) throws IOException {
        final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;

        try {
            // Existing code...

            // Read and set the width from the file
            line = buffer.readLine();
            this.setWidth(Integer.parseInt(line.trim()));

            // Read and set the height from the file
            line = buffer.readLine();
            this.setHeight(Integer.parseInt(line.trim()));

            // Rest of the code...
        } catch (NumberFormatException e) {
            // Handle NumberFormatException by marking isCorrect as false
            isCorrect = false;
        } catch (IOException e) {
            // Handle IOException by marking isCorrect as false
            isCorrect = false;
        }


        if (isCorrect) {
            // Initialize the onTheMap array with the specified width and height
            this.onTheMap = new IElement[this.getWidth()][this.getHeight()];

            int y = 0;
            line = buffer.readLine();
            while (line != null) {
                // Check if the line length matches the specified width
                if (line.toCharArray().length == this.width) {
                    for (int x = 0; x < line.toCharArray().length; x++) {
                        // Populate the onTheMap array based on characters in the line
                        if (line.toCharArray()[x] == 'R' || line.toCharArray()[x] == 'B' || line.toCharArray()[x] == 'D') {
                            this.setOnTheMapXY(MobileElementFactory.getFromFileSymbol(line.toCharArray()[x]), x, y);
                        } else {
                            this.setOnTheMapXY(MotionlessElementFactory.getFromFileSymbol(line.toCharArray()[x]), x, y);
                        }
                    }
                } else {
                    // Handle invalid line length by printing a message and marking isCorrect as false
                    System.out.println("Invalid line length: " + line);
                    this.isCorrect = false;
                }
                line = buffer.readLine();
                y++;
            }
            buffer.close();

            if (y != this.height) {
                this.isCorrect = false;
            }
        }
    }
    /*
     * Gets element on the map
     *
     */
    @Override
    public final IElement getOnTheMapXY(final int x, final int y) {
        return this.onTheMap[x][y];
    }

    /**
     * Sets the on the map XY.
     *
     * @param element the element
     * @param x       the x
     * @param y       the y
     */
    public void setOnTheMapXY(IElement element, int x, int y) {
        this.onTheMap[x][y] = element;
        this.hasChanged.add(x);
        this.hasChanged.add(y);

    }


    /*
     * Sets the mobile has changed
     *
     */
    @Override
    public final void setMobileHasChanged() {
        this.setChanged();
        this.notifyObservers();
    }

    /*
     * Gets the width
     *
     */
    @Override
    public final int getWidth() {
        return this.width;
    }

    /**
     * Sets the width.
     *
     * @param width the new width
     */
    private void setWidth(final int width) {
        this.width = width;
    }

    /*
     * Gets the height
     *
     */
    @Override
    public final int getHeight() {
        return this.height;
    }

    /**
     * Sets the height.
     *
     * @param height the new height
     */
    void setHeight(final int height) {
        this.height = height;
    }

    /*
     * Gets the observable
     *
     */
    @Override
    public Observable getObservable() {
        return this;
    }

    public boolean isCorrect() {
        return isCorrect;
    }


    public List<Integer> getHasChanged() {
        return hasChanged;
    }

}