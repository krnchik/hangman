package com.krnchik.view;

import com.krnchik.model.WordStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnterWord extends JDialog implements ActionListener, KeyListener {

    private static final int X_SIZE = 500;
    private static final int Y_SIZE = 200;

    private final GameField field;
    private JPanel panel;
    private JPasswordField word;
    private JButton ok;

    public EnterWord(GameField field) {
        super(field.getFrame(), "Enter word", true);
        this.field = field;
        init();
    }

    private void init() {
        this.setSize(X_SIZE, Y_SIZE);
        this.getContentPane().setBackground(Color.darkGray);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        initPanel();
        this.add(panel);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    private void initPanel() {
        this.panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(2, 1));
        initButton();
        initTextField();
        panel.add(word, BorderLayout.NORTH);
        panel.add(ok, BorderLayout.CENTER);
    }

    private void initTextField() {
        word = new JPasswordField();
        word.setFont(new Font("ROMAN", Font.BOLD, 40));
        word.setBackground(Color.BLACK);
        word.setForeground(Color.GREEN);
        word.setSize(new Dimension(500, 100));
        word.setCaretColor(Color.WHITE);
        word.setEchoChar('*');
        word.addKeyListener(this);
    }

    private void initButton() {
        ok = new JButton("OK");
        ok.setFocusable(false);
        ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ok.setBorderPainted(false);
        ok.setBackground(Color.BLACK);
        ok.setForeground(Color.WHITE);
        ok.setSize(new Dimension(500, 100));
        ok.setFont(new Font("MV Boli", Font.BOLD, 20));
        ok.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ok)) {
            okAction();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            okAction();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void okAction() {
        try {
            field.getGame().setSearchWord(new String(word.getPassword()));
            this.dispose();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, new String[] {
                    "ERROR",
                    "  - only english letters",
                    "  - length word less " + WordStorage.MAX_WORD_LENGTH
            }, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
}
