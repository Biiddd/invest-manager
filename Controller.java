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
        System.out.println(Market.getCompanies());

        // create an investor
        Investor investor = new Investor();

        // initialize wallet
        investor.updateWallet(10000);

        // create a strategy
        BasicStrategy strategy = new BasicStrategy(1500, 3, 8);

        // manually add interest in the company
        investor.addInterest(company_x, strategy);

        // get companies of interest
        System.out.println("I interest in " + investor.getCompaniesOfInterest() + " now");

        System.out.println("Initial Share Price: " + company_x.getCurrentSharePrice());

        // update 200 rounds
        int updates = 200;
        for (int i = 0; i < updates; i++) {
            company_x.updateSharePrice();
            company_x.updateTrend(strategy.getMovementNumber(), company_x.getSharePriceHistory(), strategy.getSignificance());
            System.out.println("Share Price after update " + (i + 1) + ": " + company_x.getCurrentSharePrice());

            // update investment, buy or sell
            investor.updateInvestment();
            if (company_x.getSharesOwned() == 0 && company_x.getEverOwned()) {
                investor.removeInterest(company_x);
            }

            System.out.println("Current Wallet: " + investor.getWallet());
        }

        // get initial and final share price
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Initial Share Price: " + company_x.getInitialSharePrice());
        System.out.println("Final Share Price: " + company_x.getCurrentSharePrice());
        System.out.println("-------------------------------------------------------------------");

    }
}
