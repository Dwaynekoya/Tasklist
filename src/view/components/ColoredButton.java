package view.components;

import javax.swing.*;
import java.awt.*;

public class ColoredButton extends JLabel {
    //creating a custom button using JLabel to have an easier time setting the look
    //must implement button functionality
    private Color mainColor;
    private String text;

    public ColoredButton(String text) {
        this.text = text;
        setText(text);
    }
}
