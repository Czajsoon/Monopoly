package Source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReaderCards {
    private ArrayList<BlueRedCards> propertiesList = new ArrayList<>();

    public ArrayList<BlueRedCards> getBlueRedCardslist() throws IOException, FileNotFoundException {
        String line = "";
        String splitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader("clientApplication/BlueRedCards.csv"));
        while ((line = br.readLine()) != null)
        {
            String[] Card = line.split(splitBy);
            BlueRedCards card = new BlueRedCards(Integer.parseInt(Card[0]),Card[1],Integer.parseInt(Card[2]),Integer.parseInt(Card[3]),Integer.parseInt(Card[4]),Integer.parseInt(Card[5]),Integer.parseInt(Card[6]));
            propertiesList.add(card);
        }
        return this.propertiesList;
    }
}
