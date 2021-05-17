package Source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReaderProperties {
    private ArrayList<Properties> propertiesList = new ArrayList<>();

    public ArrayList<Properties> getPropertieslist() throws IOException, FileNotFoundException {
        String line = "";
        String splitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader("clientApplication/properties.csv"));
        while ((line = br.readLine()) != null)
        {
            String[] propertyElement = line.split(splitBy);
            Properties property = new Properties(Integer.parseInt(propertyElement[0]),Integer.parseInt(propertyElement[1]),propertyElement[2],propertyElement[3],Integer.parseInt(propertyElement[4]),Integer.parseInt(propertyElement[5]),Integer.parseInt(propertyElement[6]),Integer.parseInt(propertyElement[7]),Integer.parseInt(propertyElement[8]));
            propertiesList.add(property);
        }
        return this.propertiesList;
    }
}

