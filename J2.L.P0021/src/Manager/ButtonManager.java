/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import GUI.GameForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ButtonManager {

    public void addButtonAction(JButton btn, HashMap<Integer, JButton> listButton, GameForm game) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setFlag(true);
                String txt = btn.getText();
                if (swappable(txt, listButton, game)) {
                    swapNumber(listButton, game);
                    updateCount(game);
                    Win(listButton, game);
                }
            }
        });
    }

    // Khi người chơi thắng, vô hiệu hóa acction của button
    public void removeButtonAction(HashMap<Integer, JButton> listButton) {

        for (Map.Entry<Integer, JButton> entry : listButton.entrySet()) {
            JButton value = entry.getValue();

            for (ActionListener e : value.getActionListeners()) {
                value.removeActionListener(e);
            }
        }
    }

    // check xem có phải ô trên ô trống
    public boolean isTop(String text, HashMap<Integer, JButton> listButton, GameForm game) {
        int size = game.getSizeGame();
        int empty = game.getEmptyPos();
        int posi = empty - size;
        if (posi >= 0) {
            String txt = listButton.get(posi).getText();
            if (txt.equals(text)) {
                game.setCurrentPos(posi);
                return true;
            }
        }
        return false;
    }

    // check xem có phải ô dưới ô trống
    public boolean isBottom(String text, HashMap<Integer, JButton> listButton, GameForm game) {
        int size = game.getSizeGame();
        int empty = game.getEmptyPos();
        int posi = empty + size;
        if (posi < size * size) {
            String txt = listButton.get(posi).getText();
            if (txt.equals(text)) {
                game.setCurrentPos(posi);
                return true;
            }
        }
        return false;
    }

    // check xem có phải ô bên phải ô trống
    public boolean isRight(String text, HashMap<Integer, JButton> listButton, GameForm game) {
        int size = game.getSizeGame();
        int empty = game.getEmptyPos();
        int posi = empty + 1;
        if (posi % size != 0) {
            String txt = listButton.get(posi).getText();
            if (txt.equals(text)) {
                game.setCurrentPos(posi);
                return true;
            }
        }
        return false;
    }

    // check xem có phải ô bên trái ô trống
    public boolean isLeft(String text, HashMap<Integer, JButton> listButton, GameForm game) {
        int size = game.getSizeGame();
        int empty = game.getEmptyPos();
        int posi = empty - 1;
        if (posi >= 0 && posi % size != size - 1) {
            String txt = listButton.get(posi).getText();
            if (txt.equals(text)) {
                game.setCurrentPos(posi);
                return true;
            }
        }
        return false;
    }

    // check xem có kế bên ô trống không , nếu phải thì có thể đổi chổ với ô trống
    public boolean swappable(String text, HashMap<Integer, JButton> listButton, GameForm game) {
        return isTop(text, listButton, game) || isBottom(text, listButton, game)
                || isLeft(text, listButton, game) || isRight(text, listButton, game);
    }

    // đếm số lần move, 
    public void updateCount(GameForm game) {
        int current = game.getMoveCount();
        game.setMoveCount(++current);
        game.getLbCount().setText(current + "");
    }

    // đổi vị trí vói ô trống
    public void swapNumber(HashMap<Integer, JButton> listButton, GameForm game) {
        int current = game.getCurrentPos();
        int empty = game.getEmptyPos();
        String txt = listButton.get(current).getText();
        listButton.get(empty).setText(txt);
        listButton.get(current).setText("");
        game.setEmptyPos(current);
    }

    // Kiểm tra win
    public boolean checkWin(HashMap<Integer, JButton> listButton) {

        for (Map.Entry<Integer, JButton> entry : listButton.entrySet()) {
            Integer key = entry.getKey();
            JButton value = entry.getValue();
            String txt = value.getText();
            if (!txt.equals("")) {
                int num = 0;
                try {
                    num = Integer.parseInt(txt);
                } catch (NumberFormatException ex) {
                    System.out.println("Convert number checkWin error");
                }
                if (num - 1 != key) {
                    return false;
                }
            }
        }
        return true;
    }

    // xử lí khi người chơi win
    // thông báo trên màn hình
    // vô hiệu các nút bấm
    public void Win(HashMap<Integer, JButton> listButton, GameForm game) {
        if (checkWin(listButton)) {
            game.setFlag(false);
            JOptionPane.showMessageDialog(game, "You Win !!!");
            removeButtonAction(listButton);
        }
    }

}
