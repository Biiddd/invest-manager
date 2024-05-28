import java.text.DecimalFormat;
import java.util.List;

public class CompanySimulation {

    public static void main(String[] args) {
        // 创建一个新公司，初始股价为100，稳定性系数为0.2
        Company company = new Company("Company X", 100, 0.73);
        // 创建一个投资者
        Investor investor = new Investor();
        investor.updateWallet(10000);

        // 加入投资者的感兴趣公司列表
        investor.addInterest(company, new BasicStrategy(1500, 3, 8));

        // 模拟更新股价和投资者的行为
        simulateCompanyTrend(company, investor, 50, 5);
    }

    // 模拟公司股价更新和投资者行为
    public static void simulateCompanyTrend(Company company, Investor investor, int updates, int significance) {

        double initialWallet = investor.getWallet();
        System.out.println("Initial Share Price: " + company.getCurrentSharePrice());

        // 进行若干次股价更新
        for (int i = 0; i < updates; i++) {
            company.updateSharePrice();
            String trend = company.getTrend(5, company.getSharePriceHistory(), 8);
            System.out.println("Share Price after update " + (i + 1) + ": " + company.getCurrentSharePrice());

            // 调用投资者的投资行为
            investor.updateInvestment();

            double profit = investor.getWallet() - initialWallet;

            // 打印投资者的钱包余额
            System.out.println("Investor's Wallet Balance: " + investor.getWallet());
        }
    }
}
