package com.krnchik.view;


import com.krnchik.controller.AlphabetController;
import com.krnchik.controller.Controller;
import com.krnchik.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AlphabetView implements ActionListener {

    private final GameField field;
    private final Game game;
    private final Controller controller;

    private JPanel panel;
    private java.util.List<JButton> buttons;

    public AlphabetView(GameField field) {
        this.field = field;
        this.game = field.getGame();
        this.controller = new AlphabetController(game);
        initPanel();
    }

    private void initPanel() {
        this.panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 7));
        panel.setPreferredSize(new Dimension(GameField.X_SIZE / 2,
                GameField.Y_SIZE / 3));
        initButtons();
        for (JButton button : buttons) {
            panel.add(button);
        }
    }

    private void initButtons() {
        buttons = new ArrayList<>();
        List<String> alphabet = createAlphabet();
        for (String letter : alphabet) {
            buttons.add(createButton(letter));
        }
    }

    private JButton createButton(String letter) {
        JButton button = new JButton(letter);
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(60, 60));
        button.setFont(new Font("ROMAN", Font.BOLD, 20));
        button.addActionListener(this);
        return button;
    }

    private List<String> createAlphabet() {
        List<String> alphabet = new ArrayList<>();
        for (int i = 65; i < 91; i++) {
            char letter = (char) i;
            alphabet.add(new String(new char[]{letter}));
        }
        return alphabet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton button : buttons) {
            if (e.getSource().equals(button)) {
                buttonAction(button);
            }
        }
    }

    private void buttonAction(JButton button) {
        button.setEnabled(false);
        if (controller.action(button.getText().charAt(0))) {
            updateWord();
            button.setBackground(Color.GREEN);
            actionWin();
        } else {
            updateHangman();
            button.setBackground(Color.RED);
            actionFail();
        }
    }

    private void updateWord() {
        field.getWord().setText(game.getHiddenWord());
        field.getWord().repaint();
    }

    private void updateHangman() {
        field.getHangman().repaint();
    }

    private void actionFail() {
        if (game.isFail()) {
            openMessage("You loser!!!");
        }
    }

    private void actionWin() {
        if (game.isWin()) {
            openMessage("You winner!!!");
        }
    }

    private void openMessage(String message) {
        new ResultMessage(field).giveMessage(message);
    }

    public void reset() {
        for (JButton button : buttons) {
            button.setEnabled(true);
            button.setBackground(Color.BLACK);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
