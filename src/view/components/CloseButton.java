package view.components;

import javax.swing.*;

public class CloseButton extends JButton {
    public CloseButton() {
        setText("x");
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setActionCommand("close");
    }
}
