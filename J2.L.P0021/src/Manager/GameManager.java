/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import GUI.GameForm;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class GameManager extends ButtonManager {

    final int SPACE_BUTTON = 30;
    final int SIZE_BUTTON = 70;

    public List<Integer> randomNumber(int size) {
        ArrayList<Integer> listNumber = new ArrayList<>();
        for (int i = 1; i <= size * size; i++) {
            listNumber.add(i);
        }
        shuffle(size, listNumber);
        return listNumber;
    }

    // xáo trộn list number
    public List<Integer> shuffle(int size, List<Integer> listNumber) {
        do {
            Collections.shuffle(listNumber);
        } while (!checkShuffle(size, listNumber));
        return listNumber;
    }

    // Kiểm tra thuật toán n-puzzle
    // Đảm bảo có thể đưa về trạng thái đích
    public boolean checkShuffle(int size, List<Integer> listNumber) {

        int count = 0;
        int posiEmty = 0;
        int max = listNumber.size();

        for (int i = 0; i < max; i++) {
            // đánh dấu vị trí trống (number = max)
            if (listNumber.get(i) == max) {
                posiEmty = i;
                continue;
            }

            for (int j = i + 1; j < max; j++) {

                if (listNumber.get(j) == max) {
                    continue;
                }

                if (listNumber.get(i) > listNumber.get(j)) {
                    count++;
                }
            }
        }
        // nếu size lẻ thì count phải là số chắn 
        if (size % 2 == 1) {
            return count % 2 == 0;
        } else {
            // nếu size chẳn thì count phải lẻ khi vị trí trống nằm trên dòng chẵn
            // count phải chẳn khi vị trí trống nằm trên dòng lẻ
            return ((posiEmty / size + 1) % 2 == 0 && count % 2 == 0)
                    || ((posiEmty / size + 1) % 2 == 1 && count % 2 == 1);
        }
    }

    public void createGameArea(HashMap<Integer, JButton> listButton, GameForm game) {
        int size = game.getSizeGame();
        ArrayList<Integer> listNumber = (ArrayList) randomNumber(size);
        game.getPnlAreaGame().removeAll();
        game.getPnlAreaGame().setLayout(new GridLayout(size, size, SPACE_BUTTON, SPACE_BUTTON));
        for (int i = 0; i < size * size; i++) {
            int num = listNumber.get(i);
            String txt = num % (size * size) != 0 ? num + "" : "";
            if (txt.equals("")) {
                game.setEmptyPos(i);
            }
            JButton btn = new JButton(txt);
            btn.setPreferredSize(new Dimension(SIZE_BUTTON, SIZE_BUTTON));
            addButtonAction(btn, listButton, game);
            listButton.put(i, btn);
            game.getPnlAreaGame().add(btn);
        }
        game.pack();
    }

    // Set sizeGame dựa vào size người chơi chọn.
    public void setSizeGame(GameForm game) {
        String sizeString = game.getCmbSize().getSelectedItem().toString();
        String[] sizeSquare = sizeString.split("x");
        try {
            int num = Integer.parseInt(sizeSquare[0]);
            game.setSizeGame(num);
        } catch (NumberFormatException nfe) {
            System.out.println("Size illegal");
        }
    }

    // Thêm acction cho nút NewGame và combobox
    public void addAction(HashMap<Integer, JButton> listButton, GameForm game) {
        game.getBtnNewGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (game.isFlag()) {
                    game.setFlag(false);
                    int confirm = JOptionPane.showConfirmDialog(game, "Do you really want to start new game?",
                            "Confirm Dialog", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        game.setFlag(true);
                        game.setTime(0);
                        game.getLbTime().setText(game.getTime() + " second");
                        game.setMoveCount(0);
                        game.getLbCount().setText(Integer.toString(game.getMoveCount()));
                        createGameArea(listButton, game);
                    } else {
                        game.setFlag(true);
                    }
                } else {
                    game.setFlag(true);
                    game.setTime(0);
                    game.getLbTime().setText(game.getTime() + " second");
                    game.setMoveCount(0);
                    game.getLbCount().setText(Integer.toString(game.getMoveCount()));
                    createGameArea(listButton, game);
                }

            }
        });
        game.getCmbSize().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSizeGame(game);
            }
        });
    }

    // Thread đếm thời gian
    public Thread Time(GameForm game) {
        Thread Time = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (game.isFlag()) {
                        int time = game.getTime();
                        game.getLbTime().setText(++time + " second");
                        game.setTime(time);
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("Thread time error");
                    }
                }
            }
        };
        return Time;
    }
}
