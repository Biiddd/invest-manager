public class BasicStrategy implements Strategy {
    private final int maxTransaction;
    private final int movementNumber;
    private final int significance;

    public BasicStrategy(int maxTransaction, int movementNumber, int significance) {
        this.maxTransaction = maxTransaction;
        this.movementNumber = movementNumber;
        this.significance = significance;
    }

    public int getMaxTransaction() {
        return maxTransaction;
    }

    public int getMovementNumber() {
        return movementNumber;
    }

    public int getSignificance() {
        return significance;
    }

    @Override
    public void invest(Company company) {

        String currentTrend = company.getCurrentTrend();

        System.out.println("Current Trend: " + currentTrend);
        System.out.println("Current shares owned: " + company.getSharesOwned());

        // buy or sell logic
        switch (currentTrend) {
            case "uncertain", "stable" ->
                // no action
                    System.out.println("--------No action taken--------");

            case "increasing" -> {
                // buy shares
                System.out.println("--------Ready to buy--------");
                buyShares(company);
            }
            case "decreasing" -> {
                // sell shares
                System.out.println("--------Ready to sell--------");
                sellShares(company);
            }
        }
    }

    // buy logic
    protected void buyShares(Company company) {
        double currentPrice = company.getCurrentSharePrice();
        int sharesToBuy = (int) (getMaxTransaction() / currentPrice);
        double cost = sharesToBuy * currentPrice;
        company.buyShares(sharesToBuy);
        System.out.println("Current price: " + currentPrice + "$");
        System.out.println("Buying " + "shares: " + sharesToBuy + ", Cost: " + cost + "$");
        System.out.println("After buying shares owned: " + company.getSharesOwned());
        System.out.println("----------------------------");
    }

    // sell logic
    protected void sellShares(Company company) {
        if (company.getSharesOwned() == 0) {
            System.out.println("No shares to sell");
        }
        double currentPrice = company.getCurrentSharePrice();
        int sharesToSell = (int) (getMaxTransaction() / currentPrice);
        if(sharesToSell > company.getSharesOwned()) {
            sharesToSell = company.getSharesOwned();
        }
        double earnings = sharesToSell * currentPrice;
        company.sellShares(sharesToSell);
        System.out.println("Current price: " + currentPrice + "$");
        System.out.println("Selling " + "shares: " + sharesToSell + ", Earnings: " + earnings + "$");
        System.out.println("After selling shares owned: " + company.getSharesOwned());
        System.out.println("-----------------------------");
    }
}
