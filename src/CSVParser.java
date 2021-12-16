import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVParser {
    private final ArrayList<Country> countries;

    public CSVParser() {
        var file = new File(System.getProperty("user.dir") + "\\data\\countries_happines_2015.csv");
        countries = new ArrayList<Country>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (var line = reader.readLine();(line = reader.readLine()) != null;) {
                var parameters = line.split(",");
                var country = new Country(parameters[0], parameters[1], 
                    Integer.parseInt(parameters[2]), Float.parseFloat(parameters[3]), Float.parseFloat(parameters[4]), 
                    Float.parseFloat(parameters[5]), Float.parseFloat(parameters[6]), Float.parseFloat(parameters[7]), 
                    Float.parseFloat(parameters[8]), Float.parseFloat(parameters[9]), Float.parseFloat(parameters[10]), 
                    Float.parseFloat(parameters[11]));
                countries.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }
}
