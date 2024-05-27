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

        System.out.println("Current Trend: " + currentTrend);
        System.out.println("Previous Trend: " + previousTrend);
        // 根据趋势决定买入或卖出
        if (currentTrend.equals("uncertain")) {
            // 买入股票
            if(previousTrend.equals("increasing")){
                return buyShares(company);
            } else if (previousTrend.equals("decreasing")) {
            // 卖出股票
                return sellShares(company);
            } else {
            // 不进行投资或卖出
            System.out.println("No action taken");
            return 0;
            }
        }
        return 0;
    }


    // 计算买入股票数量
    protected double buyShares(Company company) {
        double currentPrice = company.getCurrentSharePrice();
        Random random = new Random();
        int sharesToBuy = random.nextInt(maxTransaction + 1);
        double cost = sharesToBuy * currentPrice;
        System.out.println("Buying: " + "shares" + sharesToBuy + ", Cost: " + cost);
        company.buyShares(sharesToBuy);
        return cost;
    }

    // 计算卖出股票数量
    protected double sellShares(Company company) {
        double currentPrice = company.getCurrentSharePrice();
        int sharesToSell = company.getSharesOwned();
        double earnings = sharesToSell * currentPrice;
        company.sellShares(sharesToSell);
        System.out.println("Selling " + " shares: " +sharesToSell + ", Earnings: " + earnings);
        return earnings;
    }
}
