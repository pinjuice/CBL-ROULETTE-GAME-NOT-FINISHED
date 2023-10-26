import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BettingTimer {
    private Timer timer;
    private int secondsLeft;
    private JLabel displayLabel;
    private boolean timeOver;
    private RouletteLogic rouletteLogic;  // New field

    public BettingTimer(JLabel displayLabel, RouletteLogic rouletteLogic) {
        this.displayLabel = displayLabel;
        this.rouletteLogic = rouletteLogic;
        this.secondsLeft = 30;  // Initial countdown time.
        this.timeOver = false;

        displayLabel.setText("Time left: "+secondsLeft +" seconds");

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 0) {
                    secondsLeft--;
                    updateDisplay();
                } else {
                    endBetting();
                }
            }
        });
        timer.start();
    }

    private void updateDisplay() {
        displayLabel.setText("Time left: " + secondsLeft + " seconds");
    }

    private void endBetting() {
        timer.stop();
        displayLabel.setText("Time for betting is over");
        timeOver = true;
        rouletteLogic.spin();  // Spin the roulette wheel
        rouletteLogic.checkWinningSquareAndUpdateBalance();
    }

    public boolean isTimeOver() {
        return timeOver;
    }
}
