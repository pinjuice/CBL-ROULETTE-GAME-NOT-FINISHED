// File: RouletteAnimation.java
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class RouletteAnimation extends JPanel {
    private Image rouletteImage;
    private double angle;

    public RouletteAnimation(Image rouletteImage) {
        this.rouletteImage = rouletteImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2);
        g2d.drawImage(rouletteImage, (getWidth() - rouletteImage.getWidth(null)) / 2, (getHeight() - rouletteImage.getHeight(null)) / 2, null);
        g2d.setTransform(oldTransform);
    }

    public void setAngle(double angle) {
        this.angle = angle;
        repaint();
    }

    public double getAngle() {
        return angle;
    }
}