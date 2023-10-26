import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChipLogic {
    private ChipRenderer chipRenderer;
    private BettingTimer bettingTimer;
    private Chip selectedChip;
    private Point offset = new Point(0, 0);

    public ChipLogic(ChipRenderer chipRenderer, BettingTimer bettingTimer) {
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
        if (!bettingTimer.isTimeOver()) {
            return;
        }
        Point mousePoint = e.getPoint();
        for (Chip chip : chipRenderer.getChips()) {
            if (chip.contains(mousePoint)) {
                selectedChip = chip;
                offset = new Point(mousePoint.x - chip.getX(), mousePoint.y - chip.getY());
                break;
            }
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (!bettingTimer.isTimeOver()) {
            return;
        }
        if (selectedChip != null) {
            Point mousePoint = e.getPoint();
            int newX = mousePoint.x - offset.x;
            int newY = mousePoint.y - offset.y;
            selectedChip.setPosition(newX, newY);
            chipRenderer.repaint();
        }
    }

    private void handleMouseReleased() {
        selectedChip = null;
    }
}
