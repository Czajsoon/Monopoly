package Server;

import Source.BestPlayers;
import Source.DataReaderProperties;
import Source.Player;
import Source.Properties;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Session extends Thread {
    private ArrayList<Player> BestPlayers;
    private ArrayList<Socket> socketPlayers;
    private ArrayList<Player> playersList;
    private ArrayList<Properties> propertiesList;
    private Socket client;
    private int playerNumber;
    private int numberOfPlayersInGame;
    private int playingPlayers;
    private int playersReady = 0;
    private int playersTourReady = 0;
    private int banktouptNumbers[] = {0,0,0,0};
    private int recentBankroupt = 0;
    private ArrayList<Player> bestPlayersArray;


    public Session(Socket client, ArrayList<Socket> socketArray,int np,int numberOfPlayers,ArrayList<Player> playersList,ArrayList<Properties> propertiesList){
        this.client = client;
        this.socketPlayers = socketArray;
        playerNumber = np;
        numberOfPlayersInGame = numberOfPlayers;
        playingPlayers = numberOfPlayers;
        this.playersList = playersList;

        this.propertiesList = propertiesList;
    }

    public void run(){
        try{
            DataInputStream socketIn = new DataInputStream(client.getInputStream());
            do {

                //czyta nickname gracza
                String playerName = socketIn.readUTF();
                System.out.println(playerNumber + " Player nickname is " + playerName);
                Player newPlayer = new Player();
                newPlayer.setPlayerName(playerName);
                newPlayer.setPlayerNumber(playerNumber);
                newPlayer.setCash(3000);
                newPlayer.setPropertyId(0);
                playersList.add(newPlayer);


                if(playerNumber>0 && playerNumber<numberOfPlayersInGame){
                    System.out.println("Server is waiting for clients connection :)");
                }
                else
                    System.out.println("All clients connected great!!!");

                String information = "Players " + playerName + " joined to your session!";

                //pisze do klientów informacje jeśli klient się polaczyl
                Broadcast(socketPlayers,information);

                //sprawdza czy wszyscy gracze sa gotowi do gry
                BroadcastReady(socketPlayers,numberOfPlayersInGame);

                //properties = new DataReaderProperties();
                //propertiesList = setAllProperties(propertiesList);
                sendSettingPropertiesArray(socketPlayers,propertiesList);

//                //wysyła informacje o iliści graczy w grze
                sendInformationOfNumberOfPlayers(socketPlayers);
//
                String readyClient = socketIn.readUTF();
                if(readyClient.startsWith("ReadyCheck")){
                    //wysyla informacje o tym ze gracz jest gotowy do gry
                    BroadcastReadyToOtherClients(socketPlayers,readyClient);
                }

                sendSettingsPlayerArray(socketPlayers,playersList);

            }while(false);

            boolean firstTour = true;
            int playerTourIndex = 1;
            int i = 1;

            while(numberOfPlayersInGame >= 2 && client.isConnected()){
                if(!client.isConnected()){
                    client.close();
                }
                else{
                    System.out.println("Tura numer: " + i);
                    if(firstTour){
                        sendStartGame(socketPlayers,playerTourIndex);
                        firstTour = false;
                    }
                    String clientResponse = socketIn.readUTF();
                    if(clientResponse.startsWith("PlayerTourReady")){
                        if(clientResponse.length() > 15){
                            int isBankroupt = Character.getNumericValue(clientResponse.charAt(20));
                            int otherBankroupt = Character.getNumericValue(clientResponse.charAt(18));
                            recentBankroupt = Character.getNumericValue(clientResponse.charAt(16));
                            if(otherBankroupt == 1){
                                playingPlayers--;
                                for(int s=0;s<4;s++){
                                    System.out.println(banktouptNumbers[s]);
                                }
                                banktouptNumbers[recentBankroupt-1] = recentBankroupt;
                                System.out.println("Playing players: " + playingPlayers);
                            }
                            if(isBankroupt != 0){
                                playingPlayers--;
                                banktouptNumbers[playerTourIndex-1] = playerTourIndex;
                                recentBankroupt = playerTourIndex;
                                for(int s=0;s<4;s++){
                                    System.out.println(banktouptNumbers[s]);
                                }
                                System.out.println("PlayerBankroupt is: " + playerTourIndex);
                                System.out.println("Playing players: " + playingPlayers);
                                sendPlayerIsBankroupt(socketPlayers);
                                System.out.println("Player " + playerTourIndex + " is now bankroupt!");
                                if(playingPlayers < 2)
                                    break;
                            }
                            int prisionDecion = Character.getNumericValue(clientResponse.charAt(28));
                            int prisionBuy = Character.getNumericValue(clientResponse.charAt(30));
                            int propBuy = Character.getNumericValue(clientResponse.charAt(32));
                            char cardId = clientResponse.charAt(26);
                            int passedStrat = Character.getNumericValue(clientResponse.charAt(24));
                            int boughtHouses = Character.getNumericValue(clientResponse.charAt(22));
                            System.out.println("Player Bought " + boughtHouses + " Houses!");
                            if(prisionDecion != 0){
                                prisonDecisionUpdate(socketPlayers,playerTourIndex);
                                System.out.println("Player is in jail!");
                            }
                            else{
                                prisonDecisionOutOfJail(socketPlayers,playerTourIndex);
                            }
                            if(passedStrat != 0){
                                System.out.println("Player passed start!");
                                SendPassedStart(socketPlayers);
                            }
                            if(prisionBuy != 0){
                                prisonPayFine(socketPlayers,playerTourIndex);
                            }
                            if(cardId != '0'){
                                System.out.println("Card " + cardId);
                                CardMessage(socketPlayers,cardId,passedStrat);
                            }
                            String playerMove = clientResponse.substring(36);
                            if (propBuy != 0){
                                System.out.println("Player bought this property");
                                BuyPorpertyMessage(socketPlayers,playerMove);
                            }
                            updateMove(socketPlayers,playerMove);
                            if(boughtHouses != 0)
                                BuyHouseMessage(socketPlayers,boughtHouses);
                        }
                        System.out.println(clientResponse);
                        BroadcastReadyToOtherClientsTour(socketPlayers);
                    }
                    playerTourIndex++;
                    if(playerTourIndex > numberOfPlayersInGame){
                        playerTourIndex = 1;
                    }
                    String clientResponse1 = socketIn.readUTF();
                    if(clientResponse1.equals("allPlayersTourReady")){
                        System.out.println(clientResponse1);
                        sendStartGame(socketPlayers,playerTourIndex);
                    }
                    i++;
                }
            }
            SendEndGame(socketPlayers);
            String getNameWonPlayer = socketIn.readUTF();
            String getCashWonPlayer = socketIn.readUTF();
            Player wonPlayer = new Player();
            wonPlayer.setCash(Integer.parseInt(getCashWonPlayer));
            wonPlayer.setPlayerName(getNameWonPlayer);
            BestPlayers bestPlayers = new BestPlayers();
            bestPlayersArray = bestPlayers.getBestPlayersList();
            bestPlayersArray.add(wonPlayer);
            bestPlayers.saveBestPlayers(bestPlayersArray);
            bestPlayersArray = bestPlayers.getBestPlayersList();
            boolean flag = true;
            if(flag){
                for(Player player : bestPlayersArray){
                    SendBestPlayersArray(player);
                }
                flag = false;
            }
            sendPlayerNumberWon(socketPlayers);

            //wysle liste najlepszych graczy


        }
        catch(IOException ex){
            System.out.println("Session error: " + ex.getMessage());
        }
        catch(Exception ex){
            System.out.println("Session error: " + ex.getMessage());
        }
    }

    public void SendPassedStart(ArrayList<Socket> socketPlayers){
        try{
            for (Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                if (s != client){
                    socketOut.writeUTF("PassedStart");
                }
            }
        }
        catch(Exception e){
            System.out.println("Error Won Player: " + e.getMessage());
        }
    }

    public void SendEndGame(ArrayList<Socket> socketPlayers){
        try{
            for (Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
//                if (s != client){
                socketOut.writeUTF("EndGame");
//                }
            }
        }
        catch(Exception e){
            System.out.println("Error Won Player: " + e.getMessage());
        }
    }

    public void SendBestPlayersArray(Player bPlayer){
        try{
            for (Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());

                    socketOut.writeUTF("BestPlayerNickname " + bPlayer.getPlayerName());
                    String cash = String.valueOf(bPlayer.getCash());
                    socketOut.writeUTF("BestPlayerCash " + cash);

            }
        }
        catch(Exception e){
            System.out.println("Error Won Player: " + e.getMessage());
        }
    }

    public int getWonPlayer(){
        int wonPlayer = 0;
        if(numberOfPlayersInGame >= 2){
            if(banktouptNumbers[0] == 0){
                wonPlayer = 1;
            }
            else if(banktouptNumbers[1] == 0){
                wonPlayer = 2;
            }
            if(numberOfPlayersInGame >= 3){
                if(banktouptNumbers[2] == 0){
                    wonPlayer = 3;
                }
                if(numberOfPlayersInGame == 4){
                    if(banktouptNumbers[3] != 0){
                        wonPlayer = 4;
                    }
                }
            }
        }
        return wonPlayer;
    }

    public void sendPlayerNumberWon(ArrayList<Socket> socketPlayers){
        int wonPlayer = 0;
        int playerNumber = 1;
        wonPlayer = getWonPlayer();
        System.out.println("Player Won " + wonPlayer);
        try{
            for (Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
//                if (s != client){
                    socketOut.writeUTF("WonPlayer " + wonPlayer);
//                }
            }
        }
        catch(Exception e){
            System.out.println("Error Won Player: " + e.getMessage());
        }
    }

    public void BroadcastReadyToOtherClientsTour(ArrayList<Socket> socketPlayers){
        playersTourReady++;
        try{
            for (Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
//                socketOut.writeUTF("PlayerTourReady");
                if (s != client){
                    socketOut.writeUTF("PlayerTourReady");
                }
            }
        }
        catch(Exception e){
            System.out.println("Error Ready to others: " + e.getMessage());
        }
    }

    public void sendPlayerIsBankroupt(ArrayList<Socket> socketPlayers){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("TourPlayerBankroupt");
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about Bankruption: " + ex.getMessage());
        }
    }

    public void prisonPayFine(ArrayList<Socket> socketPlayers,int playerindex){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("PrisonPayFine " + playerindex);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about start game: " + ex.getMessage());
        }
    }

    public void prisonDecisionOutOfJail(ArrayList<Socket> socketPlayers,int playerindex){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("GetOUTPRison " + playerindex );
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about start game: " + ex.getMessage());
        }
    }

    public void prisonDecisionUpdate(ArrayList<Socket> socketPlayers,int playerindex){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("InPrison " + playerindex );
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about start game: " + ex.getMessage());
        }
    }

    public void CardMessage(ArrayList<Socket> socketPlayers,char cardId,int passedStrat){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("BlueRedCard " + cardId + " " + passedStrat);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about start game: " + ex.getMessage());
        }
    }

    public void BuyPorpertyMessage(ArrayList<Socket> socketPlayers,String propertyID){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("Purchase Property " + propertyID);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about start game: " + ex.getMessage());
        }
    }

    public void BuyHouseMessage(ArrayList<Socket> socketPlayers,int numberOfHouses){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("HouseBought " + numberOfHouses);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about HousesBought: " + ex.getMessage());
        }
    }

    public void updateMove(ArrayList<Socket> socketPlayers,String move){
        try{
            for(Socket s : socketPlayers){
                if(s != client){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("UpdateMove " + move);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about start game: " + ex.getMessage());
        }
    }

    public void sendStartGame(ArrayList<Socket> socketPlayers,int playerTourIndex){
        try{
            for(Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                socketOut.writeUTF("StartGame " + playerTourIndex);
            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about start game: " + ex.getMessage());
        }
    }

    public void sendSettingPropertiesArray(ArrayList<Socket> socketPlayers,ArrayList<Properties> propertiesList){
        try{
            DataOutputStream socketOut = new DataOutputStream(client.getOutputStream());
            socketOut.writeUTF("PropertiesSettings ");
            for(int i = 0; i< propertiesList.size();i++){
                socketOut.writeUTF("PropertiesSettings Name" + propertiesList.get(i).getNameProperty());
                socketOut.writeUTF("PropertiesSettings payment4Stay" + String.valueOf(propertiesList.get(i).getPaymentForStay()));
                socketOut.writeUTF("PropertiesSettings id" + String.valueOf(propertiesList.get(i).getIDproperty()));
                socketOut.writeUTF("PropertiesSettings buyCost" + String.valueOf(propertiesList.get(i).getBuyCost()));
                socketOut.writeUTF("PropertiesSettings Country" + propertiesList.get(i).getCountryName());
                socketOut.writeUTF("PropertiesSettings Owner" + String.valueOf(propertiesList.get(i).getOwnerID()));
                socketOut.writeUTF("PropertiesSettings lvl1" + String.valueOf(propertiesList.get(i).getLvl1()));
                socketOut.writeUTF("PropertiesSettings lvl2" + String.valueOf(propertiesList.get(i).getLvl2()));
                socketOut.writeUTF("PropertiesSettings lvl3" + String.valueOf(propertiesList.get(i).getLvl3()));
                socketOut.writeUTF("PropertiesSettings lvl4" + String.valueOf(propertiesList.get(i).getLvl4()));
            }
        }
        catch(Exception e){
            System.out.println("Error sendingPropertiesArray: " + e.getMessage());
        }
    }

    public void sendSettingsPlayerArray(ArrayList<Socket> socketPlayers,ArrayList<Player> playersList){
        try{
            for(int i = 0 ; i<socketPlayers.size();i++){
                DataOutputStream socketOut = new DataOutputStream(socketPlayers.get(i).getOutputStream());
                socketOut.writeUTF("GameSettings");
                for( int j = 0 ; j<playersList.size();j++){
                    socketOut.writeUTF("Nickname" + playersList.get(j).getPlayerName());
                    socketOut.writeUTF("NumberPlayer" + String.valueOf(playersList.get(j).getPlayerNumber()));
                    socketOut.writeUTF("CashPlayer" + String.valueOf(playersList.get(j).getCash()));
                    socketOut.writeUTF("PropertyId" + String.valueOf(playersList.get(j).getPropertyId()));
                }

            }
        }
        catch(Exception ex){
            System.out.println("Error sendingPlayersArray: " + ex.getMessage());
        }
    }

    public void sendInformationOfNumberOfPlayers(ArrayList<Socket> socketPlayers){
        try{
            for(Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                socketOut.writeUTF("PlayersInGame " + numberOfPlayersInGame);

            }
        }
        catch(Exception ex){
            System.out.println("Error sending info about number of players in game: " + ex.getMessage());
        }
    }

    public void Broadcast(ArrayList<Socket> socketPlayers,String info){
        try{
            for(int i=0;i<socketPlayers.size();i++){
                DataOutputStream socketOut = new DataOutputStream(socketPlayers.get(i).getOutputStream());
                if(socketPlayers.get(i) == client){
                    socketOut.writeUTF("You have joined to the server!");
                }
                else{
                    socketOut.writeUTF(info);
                }
            }
        }
        catch(Exception e){
            System.out.println("Error broadcast info: " + e.getMessage());
        }
    }

    public void BroadcastReady(ArrayList<Socket> socketPlayers,int numberOfPlayers){
        try{
            int numberOfPlayersConnected = 0;
            for(Socket s : socketPlayers){
                numberOfPlayersConnected++;
            }
            if(numberOfPlayers == numberOfPlayersConnected){
                for(Socket s : socketPlayers){
                    DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                    socketOut.writeUTF("ReadyCheck");
                }
            }
        }
        catch (Exception e){
            System.out.println("Error broadcastReady: " + e.getMessage());
        }
    }

    public void BroadcastReadyToOtherClients(ArrayList<Socket> socketPlayers,String text){
        playersReady++;
        try{
            for (Socket s : socketPlayers){
                DataOutputStream socketOut = new DataOutputStream(s.getOutputStream());
                if (!(s == client)){
                    socketOut.writeUTF("PlayerReady" + text.substring(10));
                }
            }
        }
        catch(Exception e){
            System.out.println("Error Ready to others: " + e.getMessage());
        }
    }
}

