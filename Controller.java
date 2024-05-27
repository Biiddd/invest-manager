import java.util.Map;

public class Controller {
    public static void main(String[] args) {
        // 添加一些公司到市场
        Market.add(new Company("Company A", 100, 0.2));
        Market.add(new Company("Company B", 120, 0.3));
        Market.add(new Company("Company C", 90, 0.1));
        Market.add(new Company("Company E", 80, 0.15));

        // 删除E公司
        Market.remove(Market.getCompanies().getLast());

        // 创建投资者
        Investor investor = new Investor();

        // 选择感兴趣的公司和投资策略
        investor.addInterest(Market.getCompanies().get(0), new BasicStrategy(1000, 3, 5));
        investor.addInterest(Market.getCompanies().get(1), new BasicStrategy(800, 3, 5));
        investor.addInterest(Market.getCompanies().get(2), new BasicStrategy(1200, 3, 5));

        // 模拟更新投资
        investor.updateInvestment();

        // 输出投资者当前持有的股票数量
        System.out.println("Investor's shares owned:");
        for (Map.Entry<Company, Strategy> entry : investor.getCompaniesOfInterest().entrySet()) {
            Company company = entry.getKey();
            System.out.println(company.getName() + ": " + company.getSharesOwned());
        }

        // 投资者不再对公司A感兴趣
        investor.removeInterest(Market.getCompanies().getFirst());

        // 模拟更新投资
        investor.updateInvestment();

        // 输出投资者当前持有的股票数量
        System.out.println("\nAfter removing interest in Company A:");
        for (Map.Entry<Company, Strategy> entry : investor.getCompaniesOfInterest().entrySet()) {
            Company company = entry.getKey();
            System.out.println(company.getName() + ": " + company.getSharesOwned());
        }

        // 选择新公司感兴趣
        Company companyD = new Company("Company D", 80, 0.15);
        investor.addInterest(companyD, new BasicStrategy(1500, 3, 5));

        // 模拟更新投资
        investor.updateInvestment();

        // 输出投资者当前持有的股票数量
        System.out.println("\nAfter adding interest in Company D:");
        for (Map.Entry<Company, Strategy> entry : investor.getCompaniesOfInterest().entrySet()) {
            Company company = entry.getKey();
            Strategy strategy = entry.getValue();
            System.out.println(company.getName() + ": " + company.getSharesOwned());
        }

        // 投资者持有的股票数量为0，购买一些股票
        companyD.buyShares(999);
        System.out.println("\nAfter buying shares in Company D:");
        System.out.println("Investor's shares owned:");
        System.out.println(companyD.getName() + ": " + companyD.getSharesOwned());
    }
}
