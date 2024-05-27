import java.util.ArrayList;
import java.util.List;

public class Market {
    // 静态成员，用于存储所有公司对象
    private static final List<Company> companies = new ArrayList<>();

    // 添加公司到市场
    public static void add(Company company) {
        // 检查公司是否已经在市场上
        if (!companies.contains(company)) {
            companies.add(company);
            System.out.println("Company " + company.getName() + " added to the market.");
        } else {
            System.out.println("Company " + company.getName() + " is already on the market.");
        }
    }

    // 从市场移除公司
    public static void remove(Company company) {
        if (companies.contains(company)) {
            companies.remove(company);
            System.out.println("Company " + company.getName() + " removed from the market.");
        } else {
            System.out.println("Company " + company.getName() + " is not on the market.");
        }
    }

    // 获取市场上的所有公司
    public static List<Company> getCompanies() {
        return companies;
    }
}
