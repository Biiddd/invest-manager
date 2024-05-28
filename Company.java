import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Company {
    private final String name; // 公司名称
    private final double stabilityCoefficient; // 稳定性系数
    private final List<Double> sharePriceHistory; // 股价历史记录
    private String currentTrend; // 当前趋势
    private String previousTrend; // 上一个趋势
    private int sharesOwned; // 拥有的股票数量
    private final boolean everOwned;

    // 构造函数
    public Company(String name, double initialSharePrice, double stabilityCoefficient) {
        this.name = name;
        this.stabilityCoefficient = stabilityCoefficient;
        this.sharePriceHistory = new ArrayList<>();
        this.sharePriceHistory.add(initialSharePrice);
        this.currentTrend = "uncertain";
        this.previousTrend = "uncertain";
        this.sharesOwned = 0;
        this.everOwned = false;
    }

    // 获取公司名称
    public String getName() {
        return name;
    }

    // 获取初始股价
    public double getInitialSharePrice() {
        return sharePriceHistory.getFirst();
    }

    // 获取当前股价
    public double getCurrentSharePrice() {
        return sharePriceHistory.getLast();
    }

    // 获取股价历史记录
    public List<Double> getSharePriceHistory() {
        return sharePriceHistory;
    }

    // get 'currentTrend'
    public String getCurrentTrend() {
        return currentTrend;
    }

    // get 'previousTrend'
    public String getPreviousTrend() {
        return previousTrend;
    }

    // get 'everOwned'
    public boolean getEverOwned() {
        return everOwned;
    }

    // 更新股价
    public void updateSharePrice() {
        double lastSharePrice = getCurrentSharePrice();
        double u = new Random().nextDouble() * 2 - 1; // 生成区间[-1, 1]内的随机数
        double newSharePrice = lastSharePrice * (1 + (1 - stabilityCoefficient) * u);
        DecimalFormat df = new DecimalFormat("#.##");
        newSharePrice = Double.parseDouble(df.format(newSharePrice));
        sharePriceHistory.add(newSharePrice);
    }

    public String getTrend(int movementNumber, List<Double> priceHistory, int significance) {
        int countSignificantIncreases = 0;
        int countSignificantDecreases = 0;
        int countInsignificant = 0;
        double threshold = significance / 100.0;
        System.out.println("-------------------------------------------------------------------");

        if (priceHistory.size() < movementNumber + 1) {
            return "uncertain";
        }
        double overallMovement = priceHistory.getLast() - priceHistory.get(priceHistory.size() - movementNumber - 1);
        double overallMovementRatio = Math.abs(overallMovement) / Math.abs(priceHistory.get(priceHistory.size() - movementNumber - 1));

        // 计算趋势
        int startIndex = Math.max(1, priceHistory.size() - movementNumber);

        for (int i = priceHistory.size() - 1; i >= startIndex; i--) {
            double movement = priceHistory.get(i) - priceHistory.get(i - 1);
            double movementRatio;
            if (priceHistory.get(i - 1) == 0) {
                return "uncertain";
            }
            // ratio (s2-s1)/s1
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

        // 判断趋势
        previousTrend = currentTrend;
        if (countSignificantIncreases > movementNumber / 2 && overallMovementRatio >= threshold) {
            currentTrend = "increasing";
            return "increasing";
        } else if (countSignificantDecreases > movementNumber / 2 && overallMovementRatio >= threshold) {
            currentTrend = "decreasing";
            return "decreasing";
        } else if (countInsignificant > movementNumber / 2 && overallMovementRatio <= threshold) {
            currentTrend = "stable";
            return "stable";
        } else {
            currentTrend = "uncertain";
            return "uncertain";
        }
    }


    // 获取当前拥有的股票数量
    public int getSharesOwned() {
        return sharesOwned;
    }

    // 购买股票
    public void buyShares(int amount) {
        sharesOwned += amount;
    }

    // 出售股票
    public void sellShares(int amount) {
        sharesOwned -= amount;
    }
}
