package Source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BestPlayers {
    public void saveBestPlayers(ArrayList<Player> playerArrayList){
        try{
            PrintWriter zapis = new PrintWriter("clientApplication/BestPlayers.csv");
            Collections.sort(playerArrayList, new Comparator<Player>() {
                @Override
                public int compare(Player o1, Player o2) {
                    return Integer.valueOf(o2.getCash()).compareTo(o1.getCash());
                }
            });
            int i = 0;
            for(Player pl : playerArrayList){
                if(i<5){
                    zapis.println((i+1) + "," + pl.getPlayerName() + "," + pl.getCash());
                }
                i++;
            }
            zapis.close();
        }
        catch(IOException e){
            System.out.println("Save error: " + e.getMessage());
        }
    }

    private ArrayList<Player> bestPlayersList = new ArrayList<>();

    public ArrayList<Player> getBestPlayersList() throws IOException {
        String line = "";
        String splitBy = ",";

        BufferedReader br = new BufferedReader(new FileReader("clientApplication/BestPlayers.csv"));
        while ((line = br.readLine()) != null)
        {
            String[] propertyElement = line.split(splitBy);
            Player player = new Player();
            player.setPlayerName(propertyElement[1]);
            player.setCash(Integer.parseInt(propertyElement[2]));
            bestPlayersList.add(player);
        }
        return this.bestPlayersList;
    }


}
