package view.components;

import control.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ColoredButton extends JButton {
    private Color mainColor;
    private String text;

    public ColoredButton(String text) {
        this.text = text;
        setText(text);
        mainColor= Constants.MAINCOLOR;
        //setBorder(new EmptyBorder(20,20,20,20));
        setBackground(mainColor);
        setBorderPainted(false);
        //setBorder(BorderFactory.createLineBorder(Color.WHITE));

    }
}
