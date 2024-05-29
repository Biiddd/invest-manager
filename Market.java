import java.util.ArrayList;
import java.util.List;

public class Market {
    // static object to store all companies
    private static final List<Company> companies = new ArrayList<>();

    // add company to the market
    public static void add(Company company) {
        // check if company is already in the market
        if (!companies.contains(company)) {
            companies.add(company);
            System.out.println("Company " + company.getName() + " added to the market.");
        } else {
            System.out.println("Company " + company.getName() + " is already on the market.");
        }
    }

    // remove company from market
    public static void remove(Company company) {
        if (companies.contains(company)) {
            companies.remove(company);
            System.out.println("Company " + company.getName() + " removed from the market.");
        } else {
            System.out.println("Company " + company.getName() + " is not on the market.");
        }
    }

    // get all companies in the market
    public static List<Company> getCompanies() {
        return companies;
    }
}
