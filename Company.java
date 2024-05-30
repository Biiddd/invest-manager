import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Company {
    private final String name; // company name
    private final double stabilityCoefficient;
    private final List<Double> sharePriceHistory; // share price history
    private String currentTrend;
    private int sharesOwned; // own shares

    // constructor
    public Company(String name, double initialSharePrice, double stabilityCoefficient) {
        this.name = name;
        this.stabilityCoefficient = stabilityCoefficient;
        this.sharePriceHistory = new ArrayList<>();
        this.sharePriceHistory.add(initialSharePrice);
        this.currentTrend = "uncertain";
        this.sharesOwned = 0;
    }

    // get company name
    public String getName() {
        return name;
    }

    // get initial share price
    public double getInitialSharePrice() {
        return sharePriceHistory.getFirst();
    }

    // get first share price
    public double getFirstSharePrice() {
        return sharePriceHistory.getLast();
    }

    // get 'currentSharePrice'
    public double getCurrentSharePrice() {
        return sharePriceHistory.getLast();
    }

    // get 'sharePriceHistory'
    public List<Double> getSharePriceHistory() {
        return sharePriceHistory;
    }

    // get 'currentTrend'
    public String getCurrentTrend() {
        return currentTrend;
    }

    // update share price
    public void updateSharePrice() {
        double lastSharePrice = getCurrentSharePrice();
        double u = new Random().nextDouble() * 2 - 1; // random number between -1 and 1
        double newSharePrice = lastSharePrice * (1 + (1 - stabilityCoefficient) * u);
        DecimalFormat df = new DecimalFormat("#.##");
        newSharePrice = Double.parseDouble(df.format(newSharePrice));
        sharePriceHistory.add(newSharePrice);
    }

    // don't use void because I need a return to end the function
    public void updateTrend(int movementNumber, List<Double> priceHistory, int significance) {
        int countSignificantIncreases = 0;
        int countSignificantDecreases = 0;
        int countInsignificant = 0;
        double threshold = significance / 100.0;
        System.out.println("-------------------------------------------------------------------");

        if (priceHistory.size() < movementNumber + 1) {
            return;
        }
        double overallMovement = priceHistory.getLast() - priceHistory.get(priceHistory.size() - movementNumber - 1);
        double overallMovementRatio = Math.abs(overallMovement) / Math.abs(priceHistory.get(priceHistory.size() - movementNumber - 1));

        // set the first movement index
        int startIndex = Math.max(1, priceHistory.size() - movementNumber);

        for (int i = priceHistory.size() - 1; i >= startIndex; i--) {
            // movement = s2 - s1
            double movement = priceHistory.get(i) - priceHistory.get(i - 1);

            // movement ratio =  (s2-s1)/s1
            double movementRatio;
            // prevent division by zero
            if (priceHistory.get(i - 1) == 0) {
                return;
            }
            movementRatio = Math.abs(movement) / Math.abs(priceHistory.get(i - 1));
            if (movementRatio >= significance / 100.0) {
                if (movement > 0) {
                    countSignificantIncreases++;
                } else if (movement < 0) {
                    countSignificantDecreases++;
                }
            } else {
                countInsignificant++;
            }
        }

        // update the trend
        if (countSignificantIncreases > movementNumber / 2 && overallMovementRatio >= threshold) {
            currentTrend = "increasing";
        } else if (countSignificantDecreases > movementNumber / 2 && overallMovementRatio >= threshold) {
            currentTrend = "decreasing";
        } else if (countInsignificant > movementNumber / 2 && overallMovementRatio <= threshold) {
            currentTrend = "stable";
        } else {
            currentTrend = "uncertain";
        }
    }

    // get shares owned
    public int getSharesOwned() {
        return sharesOwned;
    }

    // buy shares
    public void buyShares(int amount) {
        sharesOwned += amount;
    }

    // sell shares
    public void sellShares(int amount) {
        sharesOwned -= amount;
    }
}
