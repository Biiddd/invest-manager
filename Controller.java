public class Controller {

    public static void main(String[] args) {
        // create companies
        Company company_x = new Company("Company X", 100, 0.73);
        Company company_a = new Company("Company A", 200, 0.85);
        Company company_b = new Company("Company B", 150, 0.92);
        Company company_c = new Company("Company C", 300, 0.65);

        // add company to the market
        Market.add(company_x);
        Market.add(company_a);
        Market.add(company_b);
        Market.add(company_c);

        // add a duplicate company
        Market.add(company_c);

        //remove a company from the market
        Market.remove(company_c);

        // remove a company that is not in the market
        Market.remove(company_c);

        // get all companies in the market
        System.out.println("----------");
        System.out.println("Companies in the market:");
          for (Company company : Market.getCompanies()) {
            System.out.println(company.getName());
          }
        System.out.println("----------");

        // create an investor
        Investor investor = new Investor();

        // create a strategy
        BasicStrategy strategy = new BasicStrategy(1500, 3, 8);

        // manually add interest in the company
        investor.addInterest(company_x);
        investor.addInterest(company_a);
        System.out.println("I interest in " + investor.getCompaniesOfInterest() + " now");
        System.out.println("--------------------------------------------------------------");

        // remove interest in the company
        investor.removeInterest(company_a);

        // get companies of interest
        System.out.println("After remove CompanyA, I interest in " + investor.getCompaniesOfInterest() + " now");
        System.out.println("--------------------------------------------------------------");

        System.out.println("Initial Share Price: " + company_x.getCurrentSharePrice() + "$");
        System.out.println("The MaxTransaction is " + strategy.getMaxTransaction() + ", the cost or earnings will not beyond " + strategy.getMaxTransaction() + "$");

        // update 50 rounds
        int updates = 50;
        for (int i = 0; i < updates; i++) {
            company_x.updateSharePrice();
            company_x.updateTrend(strategy.getMovementNumber(), company_x.getSharePriceHistory(), strategy.getSignificance());
            System.out.println("Update Round " + (i + 1));
            System.out.println("Current Share Price: " + company_x.getCurrentSharePrice() + "$");

            // update investment, buy or sell
            investor.updateInvestment(company_x, strategy);
        }

        // get initial and final share price
        System.out.println("-------------------End of the simulation----------------------");
        System.out.println("Initial Share Price: " + company_x.getInitialSharePrice() + "$");
        System.out.println("Final Share Price: " + company_x.getCurrentSharePrice() + "$");
        System.out.println("Final Shares Owned: " + company_x.getSharesOwned());
        System.out.println("--------------------------------------------------------------");

    }
}
