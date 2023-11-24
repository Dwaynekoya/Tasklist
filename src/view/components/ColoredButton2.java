package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
/*creates a capsule shaped button
* uses code from https://github.com/lukakralj/Snippets/blob/master/Java/ovalButton/OvalButton.java
* simplified since we only need 1 shape*/
public class ColoredButton2 extends JButton {
    private Color mainColor;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        paintCapsule(g2d);
        super.paintComponent(g);
    }

    private void paintCapsule(Graphics2D g2d) {
        Shape capsule = createCapsule(0,0,getWidth(),getHeight());
        setMainColorG2D(g2d);
        g2d.fill(capsule);
        g2d.setClip(0,0,getWidth(),getHeight());
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }

    private void setMainColorG2D(Graphics g) {
        if (isEnabled()) g.setColor(mainColor);
        else g.setColor(Color.yellow);
    }

    private Shape createCapsule(double x, double y, double width, double height) {
        double r;
        double radius =0.5;
        Ellipse2D upper;
        Rectangle2D middle;
        Ellipse2D lower;

        r = 0.5 * radius * width;
        upper = new Ellipse2D.Double(x, y, 2 * r, height);
        middle = new Rectangle2D.Double(x + r, y, width - 2 * r, height);
        lower = new Ellipse2D.Double(x + (width - 2 * r), y, 2 * r, height);

        Area capsule = new Area(upper);
        capsule.add(new Area(middle));
        capsule.add(new Area(lower));

        return capsule;
    }

}
