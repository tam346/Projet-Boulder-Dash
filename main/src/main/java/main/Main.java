package main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import contract.IController;
import contract.IModel;
import contract.IModel;
import controller.Controller;
import model.DBConnection;
import model.Model;
import model.Model;
//import view.Game;
import view.View;

import javax.swing.*;

/**
 * The Class Main.
 *
 * @author Denise, Sorel, Tyreel
 */
public abstract class Main {


    private static String[] filenames = new String[] {"map.txt", "Sprites/Space.png","Sprites/Boom.png", "Sprites/Rock.png", "Sprites/Diamond.png", "Sprites/Door.png", "Sprites/Butterfly.png", "Sprites/Ground.png", "Sprites/Died.png", "Sprites/Down1.png", "Sprites/Left1.png", "Sprites/Stable.png", "Sprites/Right1.png", "Sprites/Up1.png", "Sprites/Win.png", "Sprites/Wall.png"};

    private static View view;
    private static IModel model;
    private static IController controller;
    private static DBConnection dbConnection;

    /**
     * The main method.
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(final String[] args) {
        file();
        checkFiles();
        game();

        System.exit(0);
    }


    static void checkFiles() {
        File file;
        for (String filename : filenames) {
            file = new File(filename);
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "File \"" + filename + "\" is missing.\nPlease put the file back in its place and then restart.", "BoulderDash - Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                return;
            }
        }
    }

    static void file() {
        DBConnection db = new DBConnection();
        db.open();
        db.loadBD("map3");
        List<String> mapLines = db.loadBD("map3");
        db.saveToFile(mapLines,"map.txt");
        db.close();
    }

    static void game() {
        do {
            model = new Model(filenames[0], 1, 1);

            if (model.getMap().isCorrect()) {
                view = new View(model.getMap(), model.getMyPlayer());
                controller = new Controller(view, model);
                view.setOrderPerformer(controller.getOrderPerformer());

                controller.play();

            } else {
                JOptionPane.showMessageDialog(null, "The map isn't in the right format!\nCheck your map file (map.txt).", "BoulderDash - Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        while(true);
    }
}
