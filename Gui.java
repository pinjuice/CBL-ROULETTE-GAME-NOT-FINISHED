import javax.swing.*;

public class Gui {
    
    JFrame frame = new JFrame("Cheating Roulette");
    JLabel labelBoard = new JLabel();
    JLabel timerLabel = new JLabel();
    JLabel balanceDisplay = new JLabel();
    JLabel winningNumberLabel = new JLabel();

    ChipRenderer chipRenderer;
    ChipLogic chipLogic;
    BettingTimer bettingTimer;
    BettingMechanic bettingMechanic;
    Squares squares;
    Balance balance = new Balance(1000, balanceDisplay);
    RouletteLogic rouletteLogic;

    // Add BetSlider member variable
    BetSlider betSlider;

    public Gui() {
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        timerLabel.setBounds(870, 10, 300, 50);
        frame.add(timerLabel);

        balanceDisplay.setBounds(700, 10, 300, 50);
        frame.add(balanceDisplay);
        
        squares = new Squares();
        
        winningNumberLabel.setBounds(870, 40, 300, 50);
        frame.add(winningNumberLabel);

        rouletteLogic = new RouletteLogic(balance, squares, winningNumberLabel);
        bettingTimer = new BettingTimer(timerLabel,rouletteLogic);
        chipRenderer = new ChipRenderer();
        
        chipRenderer.setBounds(0, 0, 1920, 1080);
        chipRenderer.setOpaque(false);

        // Initialize and position BetSlider before BettingMechanic
        betSlider = new BetSlider(frame);
        betSlider.getSlider().setBounds(940, 620, 300,  50);
        betSlider.getButton().setBounds(940, 680, 120, 30);
        frame.add(betSlider.getSlider());
        frame.add(betSlider.getButton());
        
        // Initialize BettingMechanic after BetSlider
        bettingMechanic = new BettingMechanic(squares, chipRenderer, betSlider, bettingTimer, balance);
        chipLogic = new ChipLogic(chipRenderer, bettingTimer);

        frame.add(chipRenderer);

        
        ImageIcon board = new ImageIcon("bettingboardCBL.jpg");
        labelBoard.setIcon(board);
        labelBoard.setBounds(870, 80, 454, 503);
        frame.add(labelBoard);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Gui();
    }
}