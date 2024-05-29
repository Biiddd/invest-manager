public class Controller {

    public static void main(String[] args) {
        // create a company
        Company company = new Company("Company X", 100, 0.73);

        // create an investor
        Investor investor = new Investor();

        // initialize wallet
        investor.updateWallet(10000);

        // manually add interest in the company
        investor.addInterest(company, new BasicStrategy(1500, 3, 8));

        // simulate company trend 200 rounds
        simulateCompanyTrend(company, investor, 200, 5);
    }

    // simulate logic
    public static void simulateCompanyTrend(Company company, Investor investor, int updates, int significance) {

        System.out.println("Initial Share Price: " + company.getCurrentSharePrice());

        // update 200 rounds
        for (int i = 0; i < updates; i++) {
            company.updateSharePrice();
            company.getTrend(5, company.getSharePriceHistory(), 8);
            System.out.println("Share Price after update " + (i + 1) + ": " + company.getCurrentSharePrice());

            // update investment, buy or sell
            investor.updateInvestment();
            if (company.getSharesOwned() == 0 && company.getEverOwned()) {
                investor.removeInterest(company);
            }

            System.out.println("Current Wallet: " + investor.getWallet());
        }
    }
}
