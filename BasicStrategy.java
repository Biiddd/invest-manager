public class BasicStrategy implements Strategy {
    private int maxTransaction; // 最大交易额
    private int movementNumber; // 移动数量
    private final int significance;   // 显著性

    public BasicStrategy(int maxTransaction, int movementNumber, int significance) {
        this.maxTransaction = maxTransaction;
        this.movementNumber = movementNumber;
        this.significance = significance;
    }

    @Override
    public int invest(Company company) {
        // 计算公司股价趋势
        String trend = calculateTrend(company);

        // 根据趋势决定买入或卖出
        if (trend.equals("increasing")) {
            // 买入股票
            return buyShares(company);
        } else if (trend.equals("decreasing")) {
            // 卖出股票
            return sellShares(company);
        } else {
            // 不进行投资或卖出
            return 0;
        }
    }

    // 计算股价趋势
    public String calculateTrend(Company company) {
        // 根据移动数量和显著性计算趋势
        // 返回"increasing"、"decreasing"、"stable"或"uncertain"
        return ""; // 返回值待完善
    }

    // 计算买入股票数量
    protected int buyShares(Company company) {
        // 确定要购买的股票数量，使投资不超过最大交易额
        return 0; // 返回值待完善
    }

    // 计算卖出股票数量
    protected int sellShares(Company company) {
        // 确定要卖出的股票数量，使减持不超过最大交易额
        return 0; // 返回值待完善
    }
}
