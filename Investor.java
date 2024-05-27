import java.util.HashMap;
import java.util.Map;

public class Investor {
    // 每个投资者都有一个感兴趣的公司集合，以及对应的策略和当前拥有的股票数量
    private final Map<Company, Strategy> interestMap;

    // 默认情况下，投资者对象被创建时不包含任何感兴趣的公司
    public Investor() {
        this.interestMap = new HashMap<>();
    }

    // 也可以在创建时指定感兴趣的公司及其对应的策略
    public Investor(Map<Company, Strategy> interestMap) {
        this.interestMap = interestMap;
    }

    // 投资者可以选择对其感兴趣的新公司，并指定相应的投资策略
    public void addInterest(Company company, Strategy strategy) {
        interestMap.put(company, strategy);
    }

    // 投资者可以决定不再对某个公司感兴趣，并卖掉当前拥有的股票
    public void removeInterest(Company company) {
        interestMap.remove(company);
    }

    // 更新投资
    public void updateInvestment() {
        for (Map.Entry<Company, Strategy> entry : interestMap.entrySet()) {
            Company company = entry.getKey();
            Strategy strategy = entry.getValue();

            // 更新公司的股价
            company.updateSharePrice();

            // 根据策略决定投资或卖出股票
            int sharesToBuyOrSell = strategy.invest(company);

            // 如果策略返回负值，说明需要卖出股票
            if (sharesToBuyOrSell < 0) {
                int sharesOwned = company.getSharesOwned();
                if (sharesOwned > 0) {
                    // 卖出所有已拥有的股票
                    company.sellShares(sharesOwned);
                }
            } else {
                // 否则买入股票
                company.buyShares(sharesToBuyOrSell);
            }
        }
    }

    // 获取投资者感兴趣的公司列表
    public Map<Company, Strategy> getCompaniesOfInterest() {
        return interestMap;
    }
}
