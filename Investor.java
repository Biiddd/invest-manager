import java.util.HashMap;
import java.util.Map;

public class Investor {
    // 每个投资者都有一个感兴趣的公司集合，以及对应的策略和当前拥有的股票数量
    private final Map<Company, Strategy> interestMap;
    private double wallet;

    // 默认情况下，投资者对象被创建时不包含任何感兴趣的公司
    public Investor() {
        this.interestMap = new HashMap<>();
        this.wallet = 0;
    }

    // 投资者可以选择对其感兴趣的新公司，并指定相应的投资策略
    public void addInterest(Company company, Strategy strategy) {
        interestMap.put(company, strategy);
    }

    // 投资者可以决定不再对某个公司感兴趣，并卖掉当前拥有的股票
    public void removeInterest(Company company) {
        interestMap.remove(company);
    }

    // 获取钱包余额
    public double getWallet() {
        return wallet;
    }

    // 更新钱包余额
    public void updateWallet(double amount) {
        wallet += amount;
    }


    // 更新投资
    public void updateInvestment() {
        for (Map.Entry<Company, Strategy> entry : interestMap.entrySet()) {
            Company company = entry.getKey();
            Strategy strategy = entry.getValue();

            // 更新公司的股价
            company.updateSharePrice();
            updateWallet(strategy.invest(company));
            double profit = getWallet() - 10000;
            System.out.println("Profit: " + profit);
        }
    }

    // 获取投资者感兴趣的公司列表
    public Map<Company, Strategy> getCompaniesOfInterest() {
        return interestMap;
    }
}
