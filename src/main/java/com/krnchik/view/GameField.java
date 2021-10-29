package com.krnchik.view;

import com.krnchik.model.Game;

import javax.swing.*;
import java.awt.*;

public class GameField {

    public static final int X_SIZE = 800;
    public static final int Y_SIZE = 500;

    private final Game game;

    private JFrame frame;
    private JPanel panel;
    private JPanel hangman;
    private AlphabetView alphabet;
    private JLabel word;


    public GameField() {
        game = new Game();
        new EnterWord(this);
        alphabet = new AlphabetView(this);
        hangman = new Hangman(this);
        initFrame();
    }

    private void initFrame() {
        frame = new JFrame("Hangman");
        frame.setSize(new Dimension(X_SIZE,Y_SIZE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initPanel();
        frame.add(panel);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setSize(new Dimension(X_SIZE,Y_SIZE));
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout());
        initWord();
        panel.add(word, BorderLayout.SOUTH);
        panel.add(alphabet.getPanel(), BorderLayout.EAST);
        panel.add(hangman, BorderLayout.CENTER);
    }

    private void initWord() {
        word = new JLabel(game.getHiddenWord(), SwingConstants.CENTER);
        word.setFont(new Font("ROMAN", Font.BOLD, 40));
        word.setForeground(Color.GREEN);
        word.setSize(new Dimension(X_SIZE, Y_SIZE / 3));
        word.setOpaque(false);
    }

    public void reset() {
        game.reset();
        new EnterWord(this);
        updateWord();
        hangman.repaint();
        alphabet.reset();
    }

    private void updateWord() {
        word.setText(game.getHiddenWord());
        word.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Game getGame() {
        return game;
    }

    public JLabel getWord() {
        return word;
    }

    public JPanel getHangman() {
        return hangman;
    }
}
