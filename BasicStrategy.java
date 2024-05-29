import java.util.Random;

public class BasicStrategy implements Strategy {
    private final int maxTransaction;
    private final int movementNumber;
    private final int significance;

    public BasicStrategy(int maxTransaction, int movementNumber, int significance) {
        this.maxTransaction = maxTransaction;
        this.movementNumber = movementNumber;
        this.significance = significance;
    }

    @Override
    public double invest(Company company) {

        String currentTrend = company.getCurrentTrend();
        String previousTrend = company.getPreviousTrend();

        System.out.println("Previous Trend: " + previousTrend);
        System.out.println("Current Trend: " + currentTrend);
        System.out.println("Current shares owned: " + company.getSharesOwned());

        // buy or sell logic
        if (currentTrend.equals("uncertain")) {
            // buy
            if (previousTrend.equals("decreasing")) {
                return buyShares(company);
            } else if (previousTrend.equals("increasing")) {
                // sell
                System.out.println("Ready to sell");
                if (company.getSharesOwned() == 0) {
                    System.out.println("No shares owned");
                } else {
                    return sellShares(company);
                }
            } else {
                System.out.println("No action taken");
                return 0;
            }
        }
        System.out.println("No action taken");
        return 0;
    }


    // buy logic
    protected double buyShares(Company company) {
        double currentPrice = company.getCurrentSharePrice();
        Random random = new Random();
        int sharesToBuy = random.nextInt(maxTransaction + 1);
        double cost = (-1)*sharesToBuy * currentPrice;
        company.buyShares(sharesToBuy);
        System.out.println("Current price: " + currentPrice);
        System.out.println("Buying " + "shares: " + sharesToBuy + ", Cost: " + cost);
        System.out.println("After action shares owned: " + company.getSharesOwned());
        return cost;
    }

    // sell logic
    protected double sellShares(Company company) {
        double currentPrice = company.getCurrentSharePrice();
        Random random = new Random();
        int sharesToSell = random.nextInt(company.getSharesOwned() + 1);
        double earnings = sharesToSell * currentPrice;
        company.sellShares(sharesToSell);
        System.out.println("Current price: " + currentPrice);
        System.out.println("Selling " + "shares: " + sharesToSell + ", Earnings: " + earnings);
        System.out.println("After action shares owned: " + company.getSharesOwned());
        return earnings;
    }
}
