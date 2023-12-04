package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ColoredMenuBar extends JMenuBar implements MouseListener, MouseMotionListener {
    private Color bgColor = Color.WHITE;
    private int xMouse, yMouse;
    private final JFrame frame;

    public ColoredMenuBar(JFrame frame) {
        super();
        this.frame=frame;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

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
    //listener methods - mouse: (some of them must be implemented but have no functionality in this component)
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    // takes mouse position when it presses the title bar
    @Override
    public void mousePressed(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    //mousemotion:
    //MAKES THE WINDOW DRAGGABLE BY THIS TITLE BAR
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        frame.setLocation(x-xMouse, y-yMouse);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
