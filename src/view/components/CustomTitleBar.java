package view.components;

import control.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class CustomTitleBar extends JMenuBar implements MouseListener, MouseMotionListener {
    private int xMouse, yMouse;
    private JDialog dialog;
    public CustomTitleBar(JDialog dialog) {
        super();
        this.dialog=dialog;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setMinimumSize(new Dimension(getWidth(),20));
        setBorder(BorderFactory.createLineBorder(Constants.TINTEDGRAY));
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
        dialog.setLocation(x-xMouse, y-yMouse);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
