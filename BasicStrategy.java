import java.util.List;
import java.util.Random;

public class BasicStrategy implements Strategy {
    private final int maxTransaction; // 最大交易额
    private final int movementNumber; // 移动数量
    private final int significance;   // 显著性

    public BasicStrategy(int maxTransaction, int movementNumber, int significance) {
        this.maxTransaction = maxTransaction;
        this.movementNumber = movementNumber;
        this.significance = significance;
    }

    @Override
    public double invest(Company company) {
        // 计算公司股价趋势
        List<String> trendHistory = company.getTrendHistory();
        String currentTrend = "uncertain";
        String previousTrend = "uncertain";
        if(trendHistory.size() > 1){
            currentTrend = trendHistory.getLast();
            previousTrend = trendHistory.get(trendHistory.size() - 2);
        }

        System.out.println("Previous Trend: " + previousTrend);
        System.out.println("Current Trend: " + currentTrend);
        System.out.println("Current shares owned: " + company.getSharesOwned());
        // 根据趋势决定买入或卖出
        if (currentTrend.equals("uncertain")) {
            // 买入股票
            if(previousTrend.equals("decreasing")){
                return buyShares(company);
            } else if (previousTrend.equals("increasing") ) {
            // 卖出股票
                System.out.println("Ready to sell");
                if (company.getSharesOwned()==0) {
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


    // 计算买入股票数量
    protected double buyShares(Company company) {
        double currentPrice = company.getCurrentSharePrice();
        Random random = new Random();
        int sharesToBuy = random.nextInt(maxTransaction + 1);
        double cost = sharesToBuy * currentPrice;
        company.buyShares(sharesToBuy);
        System.out.println("Current price: " + currentPrice);
        System.out.println("Buying " + "shares: " + sharesToBuy + ", Cost: " + cost);
        System.out.println("After action shares owned: " + company.getSharesOwned());
        return cost;
    }

    // 计算卖出股票数量
    protected double sellShares(Company company) {
        double currentPrice = company.getCurrentSharePrice();
        int sharesToSell = company.getSharesOwned();
        double earnings = sharesToSell * currentPrice;
        company.sellShares(sharesToSell);
        System.out.println("Current price: " + currentPrice);
        System.out.println("Selling " + "shares: " +sharesToSell + ", Earnings: " + earnings);
        System.out.println("After action shares owned: " + company.getSharesOwned());
        return earnings;
    }
}
