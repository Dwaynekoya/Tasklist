package view.components;

import javax.swing.*;
import java.awt.*;

public class ColoredMenuBar extends JMenuBar {
    Color bgColor = Color.WHITE;
    public void setBgColor(Color color){
        bgColor=color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0,0, getWidth() -1, getHeight() -1);
    }
}
