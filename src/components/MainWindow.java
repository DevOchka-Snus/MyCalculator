package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements WindowListener, ActionListener {
    private TextField textField;
    private List<JButton> buttonList;
    private JButton clear;
    private JButton delete;
    private JButton result;
    private Font ex;
    private StringBuilder text = new StringBuilder();
    public MainWindow() {
        ex = new Font("Impact", Font.BOLD, 40);
        setLayout(null);
        setSize(400, 500);
        setVisible(true);
        setLocation(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initTextButton();

        initButtonList();

        initClearButton();

        initDeleteButton();

        initResultButton();
    }

    private void initTextButton() {
        textField = new TextField();
        textField.setSize(330, 60);
        textField.setLocation(25, 25);
        textField.setVisible(true);
        add(textField);
    }

    private void initButtonList() {
        buttonList = new ArrayList<>();
        Symbol symbol = Symbol.ONE;
        int digit = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonList.add(initDigitButton(symbol, i, j));
                symbol.getNext();
            }
        }
    }

    private JButton initDigitButton(Symbol digit, int i, int j) {
        JButton button = new JButton(String.valueOf(digit));
        button.setFont(ex);
        button.setSize(60, 60);
        button.setLocation(25 + 80 * i, 150 + 80 * j);
        button.setVisible(true);
        add(button);
        button.addActionListener(this);
        return button;
    }

    private void initClearButton() {
        clear = new JButton("C");
        clear.setFont(ex);
        clear.setSize(60, 60);
        clear.setLocation(265, 150);
        clear.setVisible(true);
        add(clear);
        clear.addActionListener(this);
    }

    private void initDeleteButton() {
        delete = new JButton("D");
        delete.setFont(ex);
        delete.setSize(60, 60);
        delete.setLocation(265, 230);
        delete.setVisible(true);
        add(delete);
        delete.addActionListener(this);
    }

    private void initResultButton() {
        result = new JButton("=");
        result.setFont(ex);
        result.setSize(60, 60);
        result.setLocation(265, 310);
        result.setVisible(true);
        add(result);
        result.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            text.delete(0, text.length());
            textField.setText(text.toString());
            return;
        }
        if (e.getSource() == delete) {
            if (!text.isEmpty())
                text.deleteCharAt(text.length() - 1);
            textField.setText(text.toString());
            return;
        }
        if (e.getSource() == result) {
            try {
                int answer = Calculator.getResult(text.toString());
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, "Error");
            }
        }
        for (JButton digit : buttonList) {
            if (e.getSource() == digit) {
                text.append(digit.getText());
                textField.setText(text.toString());
                return;
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
