import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import org.sqlite.JDBC;

public class DatabaseManager {
    private static final String databasePath = "jdbc:sqlite:" + System.getProperty("user.dir") + 
        "\\data\\countries_happines_2015.sqlite";
    private static DatabaseManager instance = null;
    private Connection connection;

    private DatabaseManager() throws SQLException{
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(databasePath);
    }

    public static synchronized DatabaseManager getInstance() throws SQLException {
        if (instance == null) instance = new DatabaseManager();
        return instance;
    }

    public void CreateCountriesTable(ArrayList<Country> countries) throws SQLException {
        if (!(new File(System.getProperty("user.dir") + "\\data\\countries_happines_2015.sqlite").exists())) {
            try(var statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE Countries_happines_2015 " +
                    "(name TEXT PRIMARY KEY, " +
                    " region TEXT NOT NULL, " +
                    " happinesRank INTEGER NOT NULL, " +
                    " happinesScore REAL NOT NULL, " +
                    " standartError REAL NOT NULL, " +
                    " economy REAL NOT NULL, " + 
                    " family REAL NOT NULL, " +
                    " health REAL NOT NULL, " +
                    " freedom REAL NOT NULL, " +
                    " trust REAL NOT NULL, " +
                    " generosity REAL NOT NULL, " +
                    " dystopiaResidual REAL NOT NULL)");
                for(var country : countries) AddCountry(country);
            }
        }
    }

    private void AddCountry(Country country) throws SQLException {
        try(var statement = connection.prepareStatement(
            "INSERT INTO Countries_happines_2015(`name`, `region`, `happinesRank`, `happinesScore`, " + 
            "`standartError`, `economy`, `family`, `health`, `freedom`, `trust`, `generosity`, " +
            "`dystopiaResidual`) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                var parameters = country.toParametersArray();
                for(var i = 1; i <= parameters.length; i++) statement.setObject(i, parameters[i - 1]);
                statement.execute();
            }
    }

    public ArrayList<Country> ExecuteRequest(String request) throws SQLException {
        try(var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(request);
            var countries = new ArrayList<Country>();
            while(resultSet.next()) 
                countries.add(new Country(resultSet.getString("name"), 
                    resultSet.getString("region"), 
                    resultSet.getInt("happinesRank"), 
                    resultSet.getFloat("happinesScore"), 
                    resultSet.getFloat("standartError"), 
                    resultSet.getFloat("economy"), 
                    resultSet.getFloat("family"), 
                    resultSet.getFloat("health"), 
                    resultSet.getFloat("freedom"), 
                    resultSet.getFloat("trust"), 
                    resultSet.getFloat("generosity"), 
                    resultSet.getFloat("dystopiaResidual")));
            return countries;
        }
    }
}
