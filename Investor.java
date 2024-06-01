import java.util.ArrayList;
import java.util.List;

public class Investor {
    // every investor has a map of companies they are interested in
    private final List<Company> interestMap;

    // by default, the interest map is empty
    public Investor() {
        this.interestMap = new ArrayList<>();
    }

    // add company to interest map
    public void addInterest(Company company) {
        interestMap.add(company);
    }

    // remove company from interest map
    public void removeInterest(Company company) {
        interestMap.remove(company);
    }

    // get companies of interest
    public List<Company> getCompaniesOfInterest() {
        return interestMap;
    }

    // update investment
    public void updateInvestment(Company company, Strategy strategy) {
        strategy.invest(company);
    }
}
