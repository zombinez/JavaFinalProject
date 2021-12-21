public class Country {
    private final String name;
    private final String region;
    private final int happinesRank;
    private final float happinesScore;
    private final float standartError;
    private final float economy;
    private final float family;
    private final float health;
    private final float freedom;
    private final float trust;
    private final float generosity;
    private final float dystopiaResidual;

    public Country(String name, String region, int happinesRank, float happinesScore, 
        float standartError, float economy, float family, float health, float freedom, 
        float trust, float generosity, float dystopiaResidual) {
            this.name = name;
            this.region = region;
            this.happinesRank = happinesRank;
            this.happinesScore = happinesScore;
            this.standartError = standartError;
            this.economy = economy;
            this.family = family;
            this.health = health;
            this.freedom = freedom;
            this.trust = trust;
            this.generosity = generosity;
            this.dystopiaResidual = dystopiaResidual;
    }
    
    public String getName() { return name; }
    public String getRegion() { return region; }
    public int getHappinesRank() { return happinesRank; }
    public float getHappinesScore() { return happinesScore; }
    public float getStandartError() { return standartError; }
    public float getEconomy() { return economy; }
    public float getFamily() { return family; }
    public float getHealth() { return health; }
    public float getFreedom() { return freedom; }
    public float getTrust() { return trust; }
    public float getGenerosity() { return generosity; }
    public float getDystopiaResidual() { return dystopiaResidual; }

    @Override
    public String toString() {
        return String.format("Name: %s \n   Region: %s \n   Happines Rank: %d \n   Happines Score: %f \n   Standart Error: %f \n   Economy (GDP per Capita): %f \n   Family: %f \n   Health (Life Expectancy): %f \n   Freedom: %f \n   Trust (Goverment Corruption): %f \n   Generosity: %f \n   Dystopia Residual: %f",
        name, region, happinesRank, happinesScore, standartError, economy, family, health, freedom, trust, generosity, dystopiaResidual).toString();
    }

    public Object[] toParametersArray() {
        return new Object[] { name, region, happinesRank, happinesScore, standartError, economy, 
            family, health, freedom, trust, generosity, dystopiaResidual };
    }
}
