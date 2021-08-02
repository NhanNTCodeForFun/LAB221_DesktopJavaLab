/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import GUI.GameForm;
import Manager.GameManager;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
import javax.swing.JButton;

public class main {

    public static void main(String[] args) {
        GameForm game = new GameForm();
        game.setVisible(true);
        HashMap<Integer, JButton> listButton = new HashMap<>();
        GameManager manager = new GameManager();
        Thread time = manager.Time(game);
        time.start();
        manager.addAction(listButton, game);
    }

}
