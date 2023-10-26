import java.util.Random;

import javax.swing.JLabel;

public class RouletteLogic {
    private int upperbound = 12;
    Random rand = new Random();
    private int int_random;
    private Balance balance;
    private Squares squares;
    private JLabel winningNumberLabel;

    public RouletteLogic(Balance balance, Squares squares, JLabel winningNumberLabel) {
        this.balance = balance;
        this.squares = squares;
        this.winningNumberLabel = winningNumberLabel;
    }

    public void spin() {
        int_random = rand.nextInt(upperbound);
        System.out.println("Number that won is: " + int_random);
        updateWinningNumberDisplay();
    }

    public void checkWinningSquareAndUpdateBalance() {
        int winningSquare = getWinningNumber();
        int[] sumSquares = squares.getSumSquares();
        if (sumSquares[winningSquare] > 0) {
            int winnings = sumSquares[winningSquare] * 13;
            balance.add(winnings);
        }
    }

    public int getWinningNumber() {
        return int_random;
    }

    public void updateWinningNumberDisplay() {
        winningNumberLabel.setText("Winning Number: "+int_random);
    }
}