import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BallLogic {
    private ChipRenderer chipRenderer;
    private BettingTimer bettingTimer;
    private Ball selectedBall;
    private Point offset = new Point(0, 0);

    public BallLogic(ChipRenderer chipRenderer, BettingTimer bettingTimer) {
        this.chipRenderer = chipRenderer;
        this.bettingTimer = bettingTimer;

        chipRenderer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased();
            }
        });

        chipRenderer.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });
    }

    private void handleMousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
        System.out.println("Betting Timer Over: " + bettingTimer.isTimeOver());

        if (!bettingTimer.isTimeOver()) {
            return;
        }
        Point mousePoint = e.getPoint();
        for (Ball ball : chipRenderer.getBalls()) {
            if (ball.contains(mousePoint)) {
                selectedBall = ball;
                System.out.println("Ball Selected");
                offset = new Point(mousePoint.x - ball.getX(), mousePoint.y - ball.getY());
                break;
            }
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        System.out.println("Mouse Dragged");
        if (!bettingTimer.isTimeOver()) {
            return;
        }
        if (selectedBall != null) {
            Point mousePoint = e.getPoint();
            int newX = mousePoint.x - offset.x;
            int newY = mousePoint.y - offset.y;
            selectedBall.setPosition(newX, newY);
            chipRenderer.repaint();

            // Log the ball's position
            System.out.println("Ball Position: " + selectedBall.getX() + ", " + selectedBall.getY());
        }
    }

    private void handleMouseReleased() {
        System.out.println("Mouse Released");
        selectedBall = null;
    }
}
