import java.text.DecimalFormat;

public class CompanySimulation {

    public static void main(String[] args) {
        // 创建一个新公司，初始股价为100，稳定性系数为0.2
        Company company = new Company("Company X", 100, 0.73);
        // 创建一个投资者
        Investor investor = new Investor();

        // 加入投资者的感兴趣公司列表
        investor.addInterest(company, new BasicStrategy(1500, 3, 8));

        // 模拟更新股价和投资者的行为
        simulateCompanyTrend(company, 50, 5);
    }

    // 模拟公司股价更新和投资者行为
    public static void simulateCompanyTrend(Company company, int updates, int significance ) {
        DecimalFormat df = new DecimalFormat("#.##"); // 控制小数点精度为2

        System.out.println("Initial Share Price: " + df.format(company.getCurrentSharePrice()));

        // 进行若干次股价更新
        for (int i = 0; i < updates; i++) {
            company.updateSharePrice();
            String trend = company.getTrend(5, company.getSharePriceHistory(), 8);
            System.out.println("Share Price after update " + (i + 1) + ": " + df.format(company.getCurrentSharePrice()) + ", Trend: " + trend);

        }
    }
}
