package com.krnchik.view;


import com.krnchik.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Alphabet implements ActionListener {

    private final GameField field;
    private final Game game;

    private JPanel panel;
    private java.util.List<JButton> buttons;

    public Alphabet(GameField field) {
        this.field = field;
        this.game = field.getGame();
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
        String newWord = game.tryGuess(button.getText().charAt(0));
        String oldWord = field.getWord().getText();
        if (game.isGuessed(newWord, oldWord)) {
            field.getWord().setText(newWord);
            button.setBackground(Color.GREEN);
            field.getWord().repaint();
            if (game.isWin()) {
                openMessage("You winner!!!");
            }
        } else {
            field.getHangman().repaint();
            button.setBackground(Color.RED);
            if (game.isFail()) {
                openMessage("You loser!!!");
            }
        }
        button.setEnabled(false);
    }

    private void openMessage(String message) {
        int flag = JOptionPane.showConfirmDialog(null,
                new String[] { message, " Word: " + game.getWord().getSearchWord()},
                "Result",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (flag == 0) {
            System.exit(0);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
