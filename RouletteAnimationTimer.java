import javax.swing.*;

public class RouletteAnimationTimer {
    private Timer spinningTimer;
    private RouletteAnimation rouletteAnimation;

    public RouletteAnimationTimer(RouletteAnimation rouletteAnimation) {
        this.rouletteAnimation = rouletteAnimation;
    }

    public void startSpinning() {
        // Start the spinning animation
        spinningTimer = new Timer(1000 / 60, e -> {  // Update 60 times per second
            double angle = rouletteAnimation.getAngle() + 6;  // Rotate 6 degrees per frame
            if (angle >= 360) {
                angle -= 360;
            }
            rouletteAnimation.setAngle(angle);
        });
        spinningTimer.start();

        // Stop the spinning animation after 10 seconds
        new Timer(10000, e -> spinningTimer.stop()).start();
    }
}