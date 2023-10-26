import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class ChipRenderer extends JPanel {
    private List<Chip> chips;

    public ChipRenderer() {
        chips = new ArrayList<>();
    }

    public void addChip(Chip chip) {
        chips.add(chip);
    }

    public List<Chip> getChips() {
        return chips;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Chip chip : chips) {
            chip.draw(g);
        }
    }
}
