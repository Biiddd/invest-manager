import java.util.ArrayList;
import java.util.List;

public class Investor {
    // every investor has a map of companies they are interested in
    private final List<Company> interestList;

    // by default, the interest map is empty
    public Investor() {
        this.interestList = new ArrayList<>();
    }

    // add company to interest map
    public void addInterest(Company company) {
        interestList.add(company);
    }

    // remove company from interest map
    public void removeInterest(Company company) {
        interestList.remove(company);
    }

    // get companies of interest
    public List<Company> getCompaniesOfInterest() {
        return interestList;
    }

    // update investment
    public void updateInvestment(Company company, Strategy strategy) {
        strategy.invest(company);
    }
}
