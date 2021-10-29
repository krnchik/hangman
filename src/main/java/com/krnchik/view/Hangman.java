package com.krnchik.view;

import com.krnchik.model.Game;

import javax.swing.*;
import java.awt.*;

public class Hangman extends JPanel {

    private static int X_SIZE = GameField.X_SIZE / 2;
    private static int Y_SIZE = 2 * (GameField.Y_SIZE / 3);

    private final Game game;

    public Hangman(GameField field) {
        super();
        this.game = field.getGame();
        initPanel();
    }

    private void initPanel() {
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setSize(new Dimension(X_SIZE, Y_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        if (game.getCurrentAttempt() > 1)
            drawDownBeam(g2d);

        if (game.getCurrentAttempt() > 2)
            drawPole(g2d);

        if (game.getCurrentAttempt() > 3)
            drawUpBeam(g2d);

        if (game.getCurrentAttempt() > 4)
            drawRope(g2d);

        if (game.getCurrentAttempt() > 5)
            drawHead(g2d);

        if (game.getCurrentAttempt() > 6)
            drawBody(g2d);

        if (game.getCurrentAttempt() > 7)
            drawLeftLeg(g2d);

        if (game.getCurrentAttempt() > 8)
            drawRightLeg(g2d);

        if (game.getCurrentAttempt() > 9)
            drawLeftArm(g2d);

        if (game.getCurrentAttempt() > 10)
            drawRightArm(g2d);
    }

    private void drawRightArm(Graphics2D g2d) {
        beamPerson(g2d);
        g2d.drawLine(257, 140, 297, 165);
    }

    private void drawLeftArm(Graphics2D g2d) {
        beamPerson(g2d);
        g2d.drawLine(257, 140, 217, 165);
    }

    private void drawRightLeg(Graphics2D g2d) {
        beamPerson(g2d);
        g2d.drawLine(257, 205, 297, 250);
    }

    private void drawLeftLeg(Graphics2D g2d) {
        beamPerson(g2d);
        g2d.drawLine(257, 205, 217, 250);
    }

    private void drawBody(Graphics2D g2d) {
        beamPerson(g2d);
        g2d.drawLine(257, 135, 257, 200);
    }

    private void drawHead(Graphics2D g2d) {
        beamPerson(g2d);
        g2d.drawOval(237, 95, 40, 40);
    }

    private void drawRope(Graphics2D g2d) {
        beamRope(g2d);
        g2d.drawLine(257, 60, 257, 95);
    }

    private void drawUpBeam(Graphics2D g2d) {
        beamStroke(g2d);
        g2d.drawLine(76, 50, 250, 50);
    }

    private void drawPole(Graphics2D g2d) {
        beamStroke(g2d);
        g2d.drawLine(76, 350, 76, 50);
    }

    private void drawDownBeam(Graphics2D g2d) {
        beamStroke(g2d);
        g2d.drawLine(20, 350, 300, 350);
    }

    private void beamStroke(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(20));
    }

    private void beamRope(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(5));
    }

    private void beamPerson(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(7));
    }
}
