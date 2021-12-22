import org.jfree.ui.RefineryUtilities;

public class Main {
    public static void main(String[] args) throws Exception {
        var p = new CSVParser();
        var m = DatabaseManager.getInstance();
        m.CreateCountriesTable(p.getCountries());
        var secondTask = "SELECT name, region, happinesRank, happinesScore, standartError, " + 
            "economy, family, health, freedom, trust, generosity, dystopiaResidual " + 
            "FROM Countries_happines_2015 WHERE economy = (SELECT MAX(economy) " + 
            "FROM Countries_happines_2015 WHERE region = 'Latin America and Caribbean' OR region = 'Eastern Asia')";
        System.out.println("Task 2: country with the biggest economy value among Latin America, Caribean and Eastern Asia.");
        System.out.println(m.ExecuteRequest(secondTask).get(0));
        var thirdTask = "SELECT name, region, happinesRank, happinesScore, standartError, economy, family, health, freedom, trust, generosity, dystopiaResidual, MIN(" +
            "ABS((happinesScore - (SELECT AVG(happinesScore) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(happinesScore) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((standartError - (SELECT AVG(standartError) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(standartError) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((economy - (SELECT AVG(economy) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(economy) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((family - (SELECT AVG(family) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(family) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((health - (SELECT AVG(health) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(health) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((freedom - (SELECT AVG(freedom) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(freedom) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((trust - (SELECT AVG(trust) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(trust) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((generosity - (SELECT AVG(generosity) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(generosity) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) + " +
            "ABS((dystopiaResidual - (SELECT AVG(dystopiaResidual) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America')) * 100 / (SELECT AVG(dystopiaResidual) FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America'))) " +
            "FROM Countries_happines_2015 WHERE region = 'Western Europe' OR region = 'North America'";
        System.out.println("-----------------------------------------");
        System.out.println("Task 3: country with the most \"average\" values among Western Europe and North America.");
        System.out.println(m.ExecuteRequest(thirdTask).get(0));
        var chartRequest = "SELECT name, region, happinesRank, happinesScore, standartError, economy, family, " + 
            "health, freedom, trust, generosity, dystopiaResidual " + 
            "FROM Countries_happines_2015";
        var countries = m.ExecuteRequest(chartRequest);
        var countriesLabels = new String[countries.size()];
        var economyValues = new float[countries.size()];
        for (var i = 0;i < countries.size(); i++) {
            var country = countries.get(i);
            countriesLabels[i] = country.getName();
            economyValues[i] = country.getEconomy();
        }

        var chartManager = new ChartManager(countriesLabels, economyValues, "Countries", "Economy Value");
        chartManager.pack();
        RefineryUtilities.centerFrameOnScreen(chartManager);
        chartManager.setVisible(true);
    }
}
