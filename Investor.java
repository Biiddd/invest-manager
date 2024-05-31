import java.util.HashMap;
import java.util.Map;

public class Investor {
    // every investor has a map of companies they are interested in
    private final Map<Company, Strategy> interestMap;

    // by default, the interest map is empty
    public Investor() {
        this.interestMap = new HashMap<>();
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

    // update investment
    public void updateInvestment() {
        for (Map.Entry<Company, Strategy> entry : interestMap.entrySet()) {
            Company company = entry.getKey();
            Strategy strategy = entry.getValue();

            strategy.invest(company);
        }
    }
}
