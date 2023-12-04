package view.components;

import control.Constants;

import javax.swing.*;
import java.awt.*;

public class ColoredButton extends JButton {
    private Color mainColor;

    public ColoredButton(String text) {
        setText(text);
        mainColor= Constants.MAINCOLOR;
        //setBorder(new EmptyBorder(20,20,20,20));
        setBackground(mainColor);
        setBorderPainted(false);
        //setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public void changeColor(Color darkcontrastcolor) {
        setBackground(darkcontrastcolor);
    }
}
