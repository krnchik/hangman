package com.krnchik.view;

import javax.swing.*;

public class ResultMessage {

    private GameField field;

    public ResultMessage(GameField field) {
        this.field = field;
    }

    public void giveMessage(String message) {
        UIManager.put("OptionPane.cancelButtonText", "Exit");
        UIManager.put("OptionPane.okButtonText", "Try again");
        int flag = JOptionPane.showConfirmDialog(null,
                new String[] { message, " Word: " + field.getGame()
                        .getWord()
                        .getSearchWord()},
                "Result",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (flag == JOptionPane.OK_OPTION) {
            field.reset();
        }
        if (flag == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }
}
