import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Company {
    private final String name; // 公司名称
    private final double stabilityCoefficient; // 稳定性系数
    private final List<Double> sharePriceHistory; // 股价历史记录
    private int sharesOwned; // 拥有的股票数量

    // 构造函数
    public Company(String name, double initialSharePrice, double stabilityCoefficient) {
        this.name = name;
        this.stabilityCoefficient = stabilityCoefficient;
        this.sharePriceHistory = new ArrayList<>();
        this.sharePriceHistory.add(initialSharePrice); // 初始化股价历史记录
        this.sharesOwned = 0;
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

    // 更新股价
    public void updateSharePrice() {
        double lastSharePrice = getCurrentSharePrice();
        double u = new Random().nextDouble() * 2 - 1; // 生成区间[-1, 1]内的随机数
        double newSharePrice = lastSharePrice * (1 + (1 - stabilityCoefficient) * u);
        sharePriceHistory.add(newSharePrice);
    }

    public String getTrend(int movementNumber, List<Double> priceHistory, int significance) {
        DecimalFormat df = new DecimalFormat("#.###");
        int countSignificantIncreases = 0;
        int countSignificantDecreases = 0;
        int countInsignificant = 0;
        double threshold = significance/ 100.0;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("历史价格 " + priceHistory);
        if(priceHistory.size() < movementNumber + 1){
            return "uncertain";
        }
        double overallMovement = priceHistory.getLast() - priceHistory.get(priceHistory.size() - movementNumber - 1);
        double overallMovementRatio = Math.abs(overallMovement) / Math.abs(priceHistory.get(priceHistory.size() - movementNumber - 1));
        System.out.println("整体 " + df.format(overallMovement) + "  " + df.format(overallMovementRatio));

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
                    System.out.println("增" + df.format(movement) + "  " + df.format(movementRatio));
                } else if (movement < 0) {
                    countSignificantDecreases++;
                    System.out.println("降" + df.format(movement) + "  " + df.format(movementRatio));
                }
            } else {
                countInsignificant++;
                System.out.println("稳" + df.format(movement) + "  " + df.format(movementRatio));
            }
        }
        System.out.println("共增" + countSignificantIncreases + "共降" + countSignificantDecreases + "共稳" + countInsignificant);

        // 判断趋势
        if (countSignificantIncreases > movementNumber / 2 && overallMovementRatio >= threshold) {
            return "increasing";
        } else if (countSignificantDecreases > movementNumber / 2 && overallMovementRatio >= threshold) {
            return "decreasing";
        } else if (countInsignificant > movementNumber /2 && overallMovementRatio <= threshold) {
            return "stable";
        } else {
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
