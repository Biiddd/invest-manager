import java.util.HashMap;
import java.util.Map;

public class Investor {
    // every investor has a wallet and a map of companies they are interested in
    private final Map<Company, Strategy> interestMap;
    private double wallet;

    // by default, the wallet and interest map are empty
    public Investor() {
        this.interestMap = new HashMap<>();
        this.wallet = 0;
    }

    // add company to interest map
    public void addInterest(Company company, Strategy strategy) {
        interestMap.put(company, strategy);
    }

    // remove company from interest map
    public void removeInterest(Company company) {
        interestMap.remove(company);
    }

    // get companies of interest
    public Map<Company, Strategy> getCompaniesOfInterest() {
        return interestMap;
    }

    // get wallet balance
    public double getWallet() {
        return wallet;
    }

    // update wallet balance
    public void updateWallet(double amount) {
        wallet += amount;
    }

    // update investment
    public void updateInvestment() {
        for (Map.Entry<Company, Strategy> entry : interestMap.entrySet()) {
            Company company = entry.getKey();
            Strategy strategy = entry.getValue();

            updateWallet(strategy.invest(company));
        }
    }
}
