package Client;

import Source.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


public class Client extends Application {
    private ArrayList<Player> playersList = new ArrayList<>();
    private ArrayList<Properties> propertiesList = new ArrayList<>();
    public static DataInputStream dIn;
    public static DataOutputStream dOut;
    private static Socket socket = null;
    public String playerNickname;
    private int NubmerOfPlayersInGame;
    public int playersReady = 0;
    public int PlayerToursReady = 0;
    private Player player = new Player();
    private Properties property = new Properties();
    private String respondServer = new String();
    private Button readyButton = new Button("Jestem gotowy!");
    private Label readyCheckInfo = new Label("Kliknij jestem gotowy kiedy bedziesz gotowy");
    private Label clientConnected = new Label();
    private Label player1 = new Label();
    private Label player2 = new Label();
    private Label player3 = new Label();
    private Label player4 = new Label();
    private  Label p1 = new Label();
    private Label p2 = new Label();
    private Label p3 = new Label();
    private Label p4 = new Label();
    private Image pawnPlayer1 = new Image("/images/Pawns/pawn-1.png");
    private Image pawnPlayer2 = new Image("/images/Pawns/pawn-2.png");
    private Image pawnPlayer3 = new Image("/images/Pawns/pawn-3.png");
    private Image pawnPlayer4 = new Image("/images/Pawns/pawn-4.png");
    private ImageView pawnView1 = new ImageView(pawnPlayer1);
    private ImageView pawnView2 = new ImageView(pawnPlayer2);
    private ImageView pawnView3 = new ImageView(pawnPlayer3);
    private ImageView pawnView4 = new ImageView(pawnPlayer4);
    private VBox panelOponents = new VBox();
    private VBox panelTourPlayer = new VBox();
    private VBox panelTourPlayerInJail = new VBox();
    private VBox box = new VBox();
    private Button dicing = new Button("Rzuć kośćmi!");
    private VBox BuyingHousesBox = new VBox();
    private HBox HousesBox = new HBox();
    private Label buyingHousesinfo = new Label("Ile domków chcesz kupić?");
    private Button BuyHouse1 = new Button("1");
    private Button BuyHouse2 = new Button("2");
    private Button BuyHouse3 = new Button("3");
    private Button BuyHouse4 = new Button("4");
    private Button noBuyHouse = new Button("Nie kupuję!");
    private Button readyTour = new Button("Jestem gotowy!");
    private Label info = new Label("Click button to dice");
    private Label diceedInfo = new Label();
    private Button GetReadyTourButton = new Button("Jestem gotowy!");
    private Label informationOponentPanel = new Label();
    private HBox DiceBox = new HBox();
    private Image blankDice = new Image("images/Dice/dice" + 0 + ".png");
    private ImageView viewDice1 = new ImageView(blankDice);
    private ImageView viewDice2 = new ImageView(blankDice);
    private Dice dice = new Dice();
    private PawnCords cordsPlayersMap = new PawnCords();
    private Player TourPlayerProfile;
    private int position = 1;
    private VBox propertyInfo = new VBox();
    private DataReaderCards dataReaderCards = new DataReaderCards();
    private ArrayList <BlueRedCards> questionMarkList;
    private int buyPropertyMessage = 0;
    private int isInPrisonMessage = 0;
    private int playerPayedFinePrison = 0;
    private Button diceButtonPrison = new Button("Rzuć kośćmi!");
    private Button payButton = new Button("Zapłać!");
    private Button PrisonReady = new Button("Jestem gotowy!");
    private Image dice1image = new Image("/images/Dice/dice0.png");
    private Image dice2image = new Image("/images/Dice/dice0.png");
    private ImageView dice1ViewPrison = new ImageView(dice1image);
    private ImageView dice2ViewPrison = new ImageView(dice2image);
    private Label prisonInfo5 = new Label("");
    private Label prisonInfo6 = new Label("");
    private int bankrupt = 0;
    private boolean propertiesMapFlag = true;
    private boolean diceFlag = true;
    private int passedStart = 0;
    private char cardNumber = '0';
    private HousesOnPropertiesVertical hv;
    private HousesOnPropertiesHorizontal hh;
    private ArrayList<String> countries_player;
    private Boolean standingOnCountry = false;
    private int previousPosition;
    private int boughtHouses = 0;
    private VBox propertiesMap = new VBox();
    private PropertyMapImages player1Map;
    private PropertyMapImages player2Map;
    private PropertyMapImages player3Map;
    private PropertyMapImages player4Map;
    private VBox PlayerBankroupt = new VBox();
    private int isBankroupt;
    private int PlayerSesionBankroupt = 0;
    private Label endGameInfoLabel = new Label();
    private VBox endGameBox = new VBox();
    private int getBankuptOtherPlayer = 0;
    private int recentBankrouptPlayer = 0;
    private boolean sendOnceReadyBankroupt = true;
    private boolean sendOnceReadyTour = false;
    private boolean sendOnceReadyALLTour = false;
    private boolean endGameEvaluete = false;
    private ArrayList<Player> bestPlayers = new ArrayList<>();
    private boolean fikusnaFlaga = true;
    private Player bPlayer = new Player();
    private Label bestPlayer = new Label("Najlepsi gracze: ");
    private Label bestPlayer1 = new Label();
    private Label bestPlayer2 = new Label();
    private Label bestPlayer3 = new Label();
    private Label bestPlayer4 = new Label();
    private Label bestPlayer5 = new Label();
    private Label Autor = new Label("Autorzy:");
    private Label Autor1 = new Label("Jakub Czajkowski");
    private Label Autor2 = new Label("Bartosz Kuta");
    private String bPlayername = new String();
    private Hyperlink githubLink = new Hyperlink("GithubProjekt.com");
    private TextField ipTFServer = new TextField("localhost");
    private TextField portTFServer = new TextField("2115");
    private boolean BlueredcardFlag = false;

    public void startTask(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                runTask();
            }
        };
        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    public void runTask(){
        try
        {
            dOut.writeUTF(playerNickname);
            while(true){
                String respond = dIn.readUTF();
                Platform.runLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if(respond.startsWith("ReadyCheck")){
                            readyButton.setVisible(true);
                            readyCheckInfo.setVisible(true);
                            try{
                                questionMarkList = dataReaderCards.getBlueRedCardslist();
                            }
                            catch (IOException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        else if(respond.startsWith("You"))
                            clientConnected.setText(respond);
                        else if(respond.startsWith("BestPlayerNickname ")){
                            bPlayername = respond.substring(19);
                            //System.out.println("player " + bPlayername);
                        }
                        else if(respond.startsWith("BestPlayerCash ")){
                            bPlayer.setPlayerName(bPlayername);
                            //System.out.println("Player " + bPlayer.getPlayerName());
                            bPlayer.setCash(Integer.parseInt(respond.substring(15)));
                            //System.out.println("his cash " + bPlayer.getCash());
                            bestPlayers.add(bPlayer);
                            bPlayer = null;
                            bPlayer = new Player();
                        }
                        else if(respond.startsWith("EndGame")){
                            box.setVisible(false);
                            panelOponents.setVisible(false);
                            panelTourPlayer.setVisible(false);
                            panelTourPlayerInJail.setVisible(false);
                            PlayerBankroupt.setVisible(false);
                            int playerWonNumber = 0;
                            for(Player pl : playersList){
                                if(!pl.isBankroupt()){
                                    playerWonNumber = pl.getPlayerNumber();
                                }
                            }
                            String cash = String.valueOf(playersList.get(playerWonNumber-1).getCash());
                            try{
                                dOut.writeUTF(playersList.get(playerWonNumber-1).getPlayerName());
                                dOut.writeUTF(cash);
                            }
                            catch (IOException ex){
                                System.out.println("ErrorSending wonPlayer info: " + ex.getMessage());
                            }

                        }
                        else if(respond.startsWith("WonPlayer ")){

                            int playerWonNumber = 0;
                            for(Player pl : playersList){
                                if(!pl.isBankroupt()){
                                    playerWonNumber = pl.getPlayerNumber();
                                }
                            }

                            bestPlayer1.setText("1. " + bestPlayers.get(0).getPlayerName() + " jego Pieniądze: " + bestPlayers.get(0).getCash()+"$");
                            bestPlayer2.setText("2. " + bestPlayers.get(1).getPlayerName() + " jego Pieniądze: " + bestPlayers.get(1).getCash()+"$");
                            bestPlayer3.setText("3. " + bestPlayers.get(2).getPlayerName() + " jego Pieniądze: " + bestPlayers.get(2).getCash()+"$");
                            bestPlayer4.setText("4. " + bestPlayers.get(3).getPlayerName() + " jego Pieniądze: " + bestPlayers.get(3).getCash()+"$");
                            bestPlayer5.setText("5. " + bestPlayers.get(4).getPlayerName() + " jego Pieniądze: " + bestPlayers.get(4).getCash()+"$");
                            endGameBox.getChildren().add(bestPlayer);
                            endGameBox.getChildren().add(bestPlayer1);
                            endGameBox.getChildren().add(bestPlayer2);
                            endGameBox.getChildren().add(bestPlayer3);
                            endGameBox.getChildren().add(bestPlayer4);
                            endGameBox.getChildren().add(bestPlayer5);
                            endGameBox.getChildren().add(Autor);
                            endGameBox.getChildren().add(Autor1);
                            endGameBox.getChildren().add(Autor2);
                            endGameBox.getChildren().add(githubLink);
                            endGameBox.setSpacing(10);
                            endGameInfoLabel.setText("Gracz " + playersList.get(playerWonNumber-1).getPlayerName() + " wygrał grę Gratulacje!!");
                            endGameBox.setVisible(true);

                        }
                        else if(respond.startsWith("TourPlayerBankroupt")){
                            playersReady--;
                            PlayerToursReady--;
                            NubmerOfPlayersInGame--;
                            hh.updateHousesOnMap(propertiesList);
                            hv.updateHousesOnMap(propertiesList);
                            TourPlayerProfile.makeBankropt();
                            getBankuptOtherPlayer = 1;
                            recentBankrouptPlayer = TourPlayerProfile.getPlayerNumber();
                            if(TourPlayerProfile.getPlayerNumber() == 1){
                                p1.setText("BANKRUT!!!");
                                p1.setTextFill(Color.RED);
                                pawnView1.setVisible(false);
                                player1Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 2){
                                p2.setText("BANKRUT!!!");
                                p2.setTextFill(Color.RED);
                                pawnView2.setVisible(false);
                                player2Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 3){
                                p3.setText("BANKRUT!!!");
                                p3.setTextFill(Color.RED);
                                pawnView3.setVisible(false);
                                player3Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 4){
                                p4.setText("BANKRUT!!!");
                                p4.setTextFill(Color.RED);
                                pawnView4.setVisible(false);
                                player4Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                            }
                            UpdatePropetiesWhenBankroupt(propertiesList,TourPlayerProfile.getPlayerNumber());
                        }
                        else if(respond.startsWith("HouseBought")){
                            int houseBought = Character.getNumericValue(respond.charAt(12));
                            switch (houseBought){
                                case 1:{
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - propertiesList.get(previousPosition-1).getHouseCost());
                                    break;
                                }
                                case 2:{
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - (2*propertiesList.get(previousPosition-1).getHouseCost()));
                                    break;
                                }
                                case 3:{
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - (3*propertiesList.get(previousPosition-1).getHouseCost()));
                                    break;
                                }
                                case 4:{
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    propertiesList.get(previousPosition-1).buildHouseOnProperty();
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - (4*propertiesList.get(previousPosition-1).getHouseCost()));
                                    break;
                                }

                            }
                            hv.updateHousesOnMap(propertiesList);
                            hh.updateHousesOnMap(propertiesList);
                        }
                        else if(respond.startsWith("BlueRedCard ")){
                            int cardNumber = card4ClientFromServer(respond.charAt(12));
                            int passedStart = Character.getNumericValue(respond.charAt(14));
                            if(cardNumber == 2){
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 100);
                            }
                            else if(cardNumber == 4){
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 300);
                            }
                            else if(cardNumber == 6){
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 40);
                            }
                            else if(cardNumber == 8){
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 300);
                            }
                            else if(cardNumber == 10){
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 200);
                            }
                            else if(cardNumber == 11){
                                BlueredcardFlag = true;
                            }
                            else if(cardNumber == 14){
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 30);
                            }
                            else if(cardNumber == 15){
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                            }
                        }
                        else if(respond.startsWith("PassedStart")){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                        }
                        else if(respond.startsWith("Purchase Property ")){
                            int propId = Integer.parseInt(respond.substring(18));
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - propertiesList.get(propId-1).getBuyCost());
                            System.out.println("Gracz: " + TourPlayerProfile.getPlayerName() + " kupił posiadłość: " + propertiesList.get(propId-1).getNameProperty());
                            propertiesList.get(propId-1).setOwnerID(TourPlayerProfile.getPlayerNumber());
                            if(playersList.size() >= 2){
                                player1Map.updatePropertiesMap(propertiesList,playersList.get(0));
                                player2Map.updatePropertiesMap(propertiesList,playersList.get(1));
                                if(playersList.size() >= 3){
                                    player3Map.updatePropertiesMap(propertiesList,playersList.get(2));
                                    if(playersList.size() == 4){
                                        player4Map.updatePropertiesMap(propertiesList,playersList.get(3));
                                    }
                                }
                            }
//                            gridMapImages.setOwnerGrid(propertiesList,playersList);
                        }
                        else if(respond.startsWith("InPrison ")){
                            //Player oponent = playersList.get(Integer.parseInt(respond.substring(9)));
                            Player oponent = TourPlayerProfile;
                            oponent.setInJail(true);
                            System.out.println(oponent.getPlayerName() + " jest w więzieniu więc nie musisz mu płacić");
                        }
                        else if(respond.startsWith("GetOUTPRison")){
                            Player oponent = TourPlayerProfile;
                            oponent.setInJail(false);
                        }
                        else if(respond.startsWith("PrisonPayFine ")){
                            //Player oponent = playersList.get(Integer.parseInt(respond.substring(14)));
                            Player oponent = TourPlayerProfile;
                            oponent.setCash(oponent.getCash() - 400);
                            oponent.setInJail(false);
                            System.out.println(oponent.getPlayerName() + " zapłacił 400$ aby wyjść z więzienia!");
                        }
                        else if(respond.startsWith("UpdateMove ")){
                            int newPositionPlayer = Integer.parseInt(respond.substring(11));
                            //System.out.println(TourPlayerProfile.getPlayerName() + " movead to property ID: " + newPositionPlayer);
                            previousPosition = TourPlayerProfile.getPropertyId();
                            TourPlayerProfile.setPropertyId(newPositionPlayer);
                            Properties actualProperty = propertiesList.get(newPositionPlayer-1);
                            //System.out.println(TourPlayerProfile.getPlayerName() + "standing on: " + actualProperty.getNameProperty());

//                            if(previousPosition > newPositionPlayer){
//                                if(!TourPlayerProfile.getIsInJail()){
//                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
//                                    info.setText(TourPlayerProfile.getPlayerName() + " przeszedł przez start i otrzymał 400$!");
//                                }
//                            }

                            if (propertiesList.get(TourPlayerProfile.getPropertyId() - 1).getOwnerID() != 0
                                    && propertiesList.get(TourPlayerProfile.getPropertyId() - 1).getOwnerID() != TourPlayerProfile.getPlayerNumber()) {
                                if(!playersList.get(propertiesList.get(newPositionPlayer-1).getOwnerID()-1).getIsInJail()){
                                    Player oponent = playersList.get(actualProperty.getOwnerID()-1);
                                    Properties actualPropertyPlayer = propertiesList.get(newPositionPlayer-1);
                                    if(newPositionPlayer == 6 || newPositionPlayer == 16 || newPositionPlayer == 26 || newPositionPlayer == 36) {
                                        int ownedStations = 0;
                                        for(Properties prop : propertiesList){
                                            if(prop.getIDproperty() == 6 || prop.getIDproperty() == 16 ||prop.getIDproperty() == 26 ||prop.getIDproperty() == 36){
                                                if(prop.getOwnerID() == oponent.getPlayerNumber()){
                                                    ownedStations++;
                                                }
                                            }
                                        }
                                        if (ownedStations == 1) {
                                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getPaymentForStay());
                                            oponent.setCash(oponent.getCash() + actualPropertyPlayer.getPaymentForStay());
                                            info.setText(TourPlayerProfile.getPlayerName() + " Płaci " + actualPropertyPlayer.getPaymentForStay() + "$ za postój dla gracza " + oponent.getPlayerName());
                                        } else if (ownedStations == 2) {
                                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getLvl1());
                                            oponent.setCash(oponent.getCash() + actualPropertyPlayer.getLvl1());
                                            info.setText(TourPlayerProfile.getPlayerName() + " Płaci " + actualPropertyPlayer.getLvl1() + "$ za postój dla gracza " + oponent.getPlayerName());
                                        } else if (ownedStations == 3) {
                                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getLvl2());
                                            oponent.setCash(oponent.getCash() + actualPropertyPlayer.getLvl2());
                                            info.setText(TourPlayerProfile.getPlayerName() + " Płaci " + actualPropertyPlayer.getLvl2() + "$ za postój dla gracza " + oponent.getPlayerName());
                                        } else if (ownedStations == 4) {
                                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getLvl3());
                                            oponent.setCash(oponent.getCash() + actualPropertyPlayer.getLvl3());
                                            info.setText(TourPlayerProfile.getPlayerName() + " Płaci " + actualPropertyPlayer.getLvl3() + "$ za postój dla gracza " + oponent.getPlayerName());
                                        }
                                    }
                                    else if(newPositionPlayer == 13 || newPositionPlayer == 29){
                                        int sumDices = newPositionPlayer - previousPosition;
                                        //System.out.println(sumDices);
                                        int ownedPW = 0;
                                        for(Properties prop : propertiesList){
                                            if(prop.getIDproperty() == 13 || prop.getIDproperty() == 29){
                                                if(prop.getOwnerID() == oponent.getPlayerNumber()){
                                                    ownedPW++;
                                                }
                                            }
                                        }
                                        if(ownedPW == 1){
                                            int fine = sumDices * 10;
                                            info.setText(TourPlayerProfile.getPlayerName() + " Płaci " + fine + "$ za postój dla gracza " + oponent.getPlayerName());
                                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - fine);
                                            oponent.setCash(oponent.getCash() + fine);
                                        }
                                        else if(ownedPW == 2){
                                            int fine = sumDices * 20;
                                            info.setText(TourPlayerProfile.getPlayerName() + " Płaci " + fine + "$ za postój dla gracza " + oponent.getPlayerName());
                                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - fine);
                                            oponent.setCash(oponent.getCash() + fine);
                                        }
                                    }
                                    else{
                                        info.setText(TourPlayerProfile.getPlayerName() + " płaci graczowi " + playersList.get(propertiesList.get(newPositionPlayer-1).getOwnerID()-1).getPlayerName());
                                        System.out.println( TourPlayerProfile.getPlayerName() + " płaci graczowi  " + playersList.get(propertiesList.get(newPositionPlayer-1).getOwnerID()-1).getPlayerName());
                                        TourPlayerProfile.setCash(TourPlayerProfile.getCash() - propertiesList.get(newPositionPlayer-1).getPaymentForStay());
                                        playersList.get(propertiesList.get(newPositionPlayer-1).getOwnerID()-1).setCash(playersList.get(propertiesList.get(newPositionPlayer-1).getOwnerID()-1).getCash() + propertiesList.get(newPositionPlayer-1).getPaymentForStay());

                                    }
                                }
                            }

                            if(newPositionPlayer == 5){
                                if(TourPlayerProfile.getCash() >= 400){
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 400);
                                }
                                else{
                                    TourPlayerProfile.setCash(0);
                                    TourPlayerProfile.makeBankropt();
                                }
                            }
                            else if(newPositionPlayer == 39){
                                if(TourPlayerProfile.getCash() >= 200){
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 200);
                                }
                                else{
                                    TourPlayerProfile.setCash(0);
                                    TourPlayerProfile.makeBankropt();
                                }
                            }
                            makeUpdateMapANdTables();
                        }
                        else if(respond.startsWith("PlayersInGame")){
                            NubmerOfPlayersInGame = Integer.parseInt(respond.substring(14));

                            if(NubmerOfPlayersInGame >= 2){
                                pawnView1.setVisible(true);
                                pawnView1.setFitWidth(20);
                                pawnView1.setFitHeight(20);
                                pawnView1.setX(cordsPlayersMap.getCorXProperty(1,1));
                                pawnView1.setY(cordsPlayersMap.getCorYProperty(1,1));

                                pawnView2.setVisible(true);
                                pawnView2.setFitWidth(20);
                                pawnView2.setFitHeight(20);
                                pawnView2.setX(cordsPlayersMap.getCorXProperty(1,2));
                                pawnView2.setY(cordsPlayersMap.getCorYProperty(1,2));

                                if(NubmerOfPlayersInGame >= 3){
                                    pawnView3.setVisible(true);
                                    pawnView3.setFitWidth(20);
                                    pawnView3.setFitHeight(20);
                                    pawnView3.setX(cordsPlayersMap.getCorXProperty(1,3));
                                    pawnView3.setY(cordsPlayersMap.getCorYProperty(1,3));
                                    if(NubmerOfPlayersInGame == 4){
                                        pawnView4.setVisible(true);
                                        pawnView4.setFitWidth(20);
                                        pawnView4.setFitHeight(20);
                                        pawnView4.setX(cordsPlayersMap.getCorXProperty(1,4));
                                        pawnView4.setY(cordsPlayersMap.getCorYProperty(1,4));
                                    }
                                }
                            }
                        }
                        else if(respond.startsWith("StartGame") && playersReady == NubmerOfPlayersInGame){

                            sendOnceReadyBankroupt = true;
                            sendOnceReadyTour = true;
                            sendOnceReadyALLTour = true;

                            if(propertiesMapFlag){
                                if(playersList.size() >= 2){
                                    //System.out.println("elo");
                                    player1Map = new PropertyMapImages(playersList.get(0).getPlayerName());
                                    player2Map = new PropertyMapImages(playersList.get(1).getPlayerName());
                                    propertiesMap.getChildren().add(player1Map.getMainBox());
                                    propertiesMap.getChildren().add(player2Map.getMainBox());
                                    if(playersList.size() >= 3){
                                        player3Map = new PropertyMapImages(playersList.get(2).getPlayerName());
                                        propertiesMap.getChildren().add(player3Map.getMainBox());
                                        if(playersList.size() == 4){
                                            player4Map = new PropertyMapImages(playersList.get(3).getPlayerName());
                                            propertiesMap.getChildren().add(player4Map.getMainBox());
                                        }
                                    }
                                }
                                propertiesMapFlag = false;
                            }

                            int playerId = Integer.parseInt(respond.substring(10));
                            TourPlayerProfile = playersList.get(playerId-1);
                            String tplayerNick = playersList.get(playerId-1).getPlayerName();

                            box.setVisible(false);
                            panelOponents.setVisible(false);
                            panelTourPlayer.setVisible(false);
                            panelTourPlayerInJail.setVisible(false);
                            PlayerBankroupt.setVisible(false);



                            if(true){
                                countries_player = allCountriesPlayers(propertiesList,TourPlayerProfile);
                                if(playerNickname.equals(tplayerNick)){
                                    if(PlayerSesionBankroupt == 1){
                                        PlayerBankroupt.setVisible(true);
                                        SendPlayerTourReadyCurrentPlayer();
                                    }
                                    else{
                                        if(TourPlayerProfile.getIsInJail()){
                                            prisonInfo5.setVisible(true);
                                            prisonInfo6.setVisible(true);
                                            PrisonReady.setVisible(false);
                                            payButton.setVisible(true);
                                            diceButtonPrison.setVisible(true);
                                            panelTourPlayerInJail.setVisible(true);
                                            readyTour.setVisible(false);
                                        }
                                        else{
                                            viewDice1.setImage(blankDice);
                                            viewDice2.setImage(blankDice);
                                            diceedInfo.setText("");
                                            panelTourPlayer.setVisible(true);
                                            readyTour.setVisible(false);
                                            standingOnCountry = false;
                                            dicing.setVisible(false);
                                            for(String s : countries_player){
                                                if(propertiesList.get(position-1).getCountryName().equals(s)) {
                                                    standingOnCountry = true;
                                                }
                                            }
                                            if(standingOnCountry){
                                                dicing.setVisible(false);
                                                BuyingHousesBox.setVisible(true);
                                                BuyHouse1.setVisible(false);
                                                BuyHouse2.setVisible(false);
                                                BuyHouse3.setVisible(false);
                                                BuyHouse4.setVisible(false);
                                                noBuyHouse.setVisible(true);
                                                System.out.println(TourPlayerProfile.getCash());
                                                if(TourPlayerProfile.getCash() >= (propertiesList.get(position-1).getHouseCost()) && 4-propertiesList.get(position-1).getActualLvlProperty() >= 1){
                                                    BuyHouse1.setVisible(true);
                                                }
                                                if(TourPlayerProfile.getCash() >= (2*(propertiesList.get(position-1).getHouseCost())) && 4-propertiesList.get(position-1).getActualLvlProperty() >= 2){
                                                    BuyHouse2.setVisible(true);
                                                }
                                                if(TourPlayerProfile.getCash() >= (3*propertiesList.get(position-1).getHouseCost()) && 4-propertiesList.get(position-1).getActualLvlProperty() >= 3){
                                                    BuyHouse3.setVisible(true);
                                                }
                                                if(TourPlayerProfile.getCash() >= (4*propertiesList.get(position-1).getHouseCost()) && 4-propertiesList.get(position-1).getActualLvlProperty() >= 4){
                                                    BuyHouse4.setVisible(true);
                                                }
                                                if(TourPlayerProfile.getCash() < (propertiesList.get(position-1).getHouseCost())){
                                                    BuyingHousesBox.setVisible(false);
                                                    dicing.setVisible(true);
                                                }
                                                BuyHouse1.setText("1 -" + propertiesList.get(position-1).getHouseCost() + "$");
                                                BuyHouse2.setText("2 -" + (2*propertiesList.get(position-1).getHouseCost()) + "$");
                                                BuyHouse3.setText("3 -" + (3*propertiesList.get(position-1).getHouseCost()) + "$");
                                                BuyHouse4.setText("4 -" + (4*propertiesList.get(position-1).getHouseCost()) + "$");
                                            }
                                            else{
                                                dicing.setVisible(true);
                                                BuyingHousesBox.setVisible(false);
                                            }
                                        }

                                    }
                                }
                                else{
                                    if(PlayerSesionBankroupt == 1){
                                        if(sendOnceReadyBankroupt){
                                            PlayerBankroupt.setVisible(true);
//                                            SendPlayerTourReady();
//                                            sendOnceReadyBankroupt =false;
                                        }

                                    }
                                    else{
                                        panelOponents.setVisible(true);
                                        GetReadyTourButton.setVisible(true);
                                        informationOponentPanel.setText("");
                                    }
                                }
                            }
                        }
                        else if(respond.startsWith("PlayerTourReady")){
                            PlayerToursReady++;
                            if(PlayerToursReady == NubmerOfPlayersInGame){
                                box.setVisible(false);
                                try{
                                    dOut.writeUTF("allPlayersTourReady");
                                }
                                catch(Exception ex){

                                }
                                PlayerToursReady = 0;
                            }
                        }
                        else if(respond.startsWith("PlayerReady")){
                            String respondPlayerReady = respond.substring(11) + " Jest gotowy!";
                            playersReady++;
                            clientConnected.setText(respondPlayerReady);
                            if(playersReady == NubmerOfPlayersInGame){
                                clientConnected.setVisible(false);
                                readyCheckInfo.setText("Wszyscy gracze są gotowi do gry...");
                                readyCheckInfo.setTextFill(Color.GREEN);
                            }
                        }
                        else if(respond.startsWith("Players")){
                            clientConnected.setText(respond);
                        }
                        else if(respond.startsWith("Nickname") && NubmerOfPlayersInGame == playersReady){
                            player.setPlayerName(respond.substring(8));
                            //System.out.println("nick: " + player.getPlayerName());
                        }
                        else if(respond.startsWith("NumberPlayer") && NubmerOfPlayersInGame == playersReady){
                            player.setPlayerNumber(Integer.parseInt(respond.substring(12)));
                            //System.out.println("playernumber: " + player.getPlayerNumber());
                        }
                        else if(respond.startsWith("CashPlayer") && NubmerOfPlayersInGame == playersReady){
                            player.setCash(Integer.parseInt(respond.substring(10)));
                            //System.out.println("cash: " + player.getCash());
                        }
                        else if(respond.startsWith("PropertyId") && NubmerOfPlayersInGame == playersReady){
                            player.setPropertyId(Integer.parseInt(respond.substring(10)));
                            String propertyName = propertiesList.get(player.getPropertyId()).getNameProperty();

                            if(player.getPlayerNumber() == 1){
                                String text = "Gracz 1 : " + player.getPlayerName() + " jego pieniądze: " + player.getCash() + "$" + " stoi na: " + propertyName;
                                //System.out.println(text);
                                player1.setText(text);
                                p1.setText(player1.getText());
                                player1.setVisible(true);
                            }

                            if(player.getPlayerNumber() == 2){
                                String text = "Gracz 2 : " + player.getPlayerName() + " jego pieniądze: " + player.getCash() + "$" + " stoi na: " + propertyName;
                                //System.out.println(text);
                                player2.setText(text);
                                p2.setText(player2.getText());
                                player2.setVisible(true);
                            }

                            if(player.getPlayerNumber() == 3){
                                String text = "Gracz 3 : " + player.getPlayerName() + " jego pieniądze: " + player.getCash() + "$" + " stoi na: " + propertyName;
                                //System.out.println(text);
                                player3.setText(text);
                                p3.setText(player3.getText());
                                player3.setVisible(true);
                            }

                            if(player.getPlayerNumber() == 4){
                                String text = "Gracz 4 : " + player.getPlayerName() + " jego pieniądze: " + player.getCash() + "$" + " stoi na: " + propertyName;
                                //System.out.println(text);
                                player4.setText(text);
                                p4.setText(player4.getText());
                                player4.setVisible(true);
                            }

                            playersList.add(player);
                            player = null;
                            player = new Player();
                            //System.out.println("property: " + respond.substring(10));
                        }
                        else if(respond.startsWith("PropertiesSettings ")){
                            if(respond.startsWith("PropertiesSettings Name")){
                                String convertedRespond = respond.substring(23);
                                property.setNameProperty(convertedRespond);
                            }
                            else if(respond.startsWith("PropertiesSettings payment4Stay")){
                                int convertedRespond = Integer.parseInt(respond.substring(31));
                                property.setPaymentForStay(convertedRespond);
                            }

                            else if(respond.startsWith("PropertiesSettings id")){
                                int convertedRespond = Integer.parseInt(respond.substring(21));
                                property.setIDproperty(convertedRespond);
                            }

                            else if(respond.startsWith("PropertiesSettings buyCost")){
                                int convertedRespond = Integer.parseInt(respond.substring(26));
                                property.setBuyCost(convertedRespond);
                            }
                            else if(respond.startsWith("PropertiesSettings Country")){
                                String convertedRespond = respond.substring(26);
                                property.setCountryName(convertedRespond);
                            }

                            else if(respond.startsWith("PropertiesSettings Owner")){
                                int convertedRespond = Integer.parseInt(respond.substring(24));
                                property.setOwnerID(convertedRespond);
                            }

                            else if(respond.startsWith("PropertiesSettings lvl1")){
                                int convertedRespond = Integer.parseInt(respond.substring(23));
                                property.setLvl1(convertedRespond);
                            }

                            else if(respond.startsWith("PropertiesSettings lvl2")){
                                int convertedRespond = Integer.parseInt(respond.substring(23));
                                property.setLvl2(convertedRespond);
                            }

                            else if(respond.startsWith("PropertiesSettings lvl3")){
                                int convertedRespond = Integer.parseInt(respond.substring(23));
                                property.setLvl3(convertedRespond);
                            }

                            else if(respond.startsWith("PropertiesSettings lvl4")){
                                int convertedRespond = Integer.parseInt(respond.substring(23));
                                property.setLvl4(convertedRespond);
                                propertiesList.add(property);
                                //System.out.println(property.getIDproperty() + " name: " + property.getNameProperty());
                                property = null;
                                property = new Properties();
                                ifMorePropertiesThan40DeleteUnsessesery();
                            }
                        }

                    }
                });
                System.out.println(respond);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        showLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showLogin(){

        Stage stage = new Stage();
        stage.setTitle("DolarBussines Client");
        stage.getIcons().add(new Image("images/DolarBussines.png"));

        TextField tx = new TextField("Gracz 1");
        tx.setAlignment(Pos.CENTER);
        tx.setMaxWidth(200);
        tx.setPadding(new Insets(10,0,10,0));
        Label ipInfo = new Label("Podaj IP serwera");
        ipInfo.setAlignment(Pos.CENTER);
        ipInfo.setMaxWidth(200);
        ipInfo.setPadding(new Insets(10,0,10,0));
        Label portInfo = new Label("Podaj port serwera");
        portInfo.setAlignment(Pos.CENTER);
        portInfo.setMaxWidth(200);
        portInfo.setPadding(new Insets(10,0,10,0));

        Label info = new Label("");
        info.setTextFill(Color.RED);
        Label label = new Label("Wpisz swój pseudonim");

        Button b = new Button("Podsumuj i połącz!");
        b.setAlignment(Pos.CENTER);

        b.setOnAction(e->{
            createConnection();
            if(socket != null){
                this.playerNickname = tx.getText();
                info.setText("Wpisałeś: " + this.playerNickname + " i połączenie udało się!");
                showGame();
                stage.close();
            }
            else{
                info.setText("Połączenie nie udało się\nSprawdź czy serwer działa.");
            }

        });


        VBox vBox = new VBox();
        vBox.setSpacing(20);

        ObservableList list = vBox.getChildren();


        list.addAll(ipInfo,ipTFServer,portInfo,portTFServer,label, tx, b,info);

        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,40,0,40));

        Scene sc = new Scene(vBox, 600, 400);

        stage.setScene(sc);
        stage.show();
    }

    public void endGame(){
        endGameBox.getChildren().add(endGameInfoLabel);
        endGameBox.setAlignment(Pos.CENTER);
    }

    public void showGame(){
        readyButton.setVisible(false);
        readyCheckInfo.setVisible(false);

        startTask();
        Stage stage = new Stage();
        stage.setTitle("DolarBussines Game");
        stage.getIcons().add(new Image("images/DolarBussines.png"));
        GridPane grp = new GridPane();

        BuyingHousesBox.getChildren().add(buyingHousesinfo);
        HousesBox.getChildren().add(BuyHouse1);
        HousesBox.getChildren().add(BuyHouse2);
        HousesBox.getChildren().add(BuyHouse3);
        HousesBox.getChildren().add(BuyHouse4);
        HousesBox.getChildren().add(noBuyHouse);
        HousesBox.setAlignment(Pos.CENTER);
        HousesBox.setSpacing(20);
        BuyingHousesBox.setSpacing(10);
        BuyingHousesBox.setAlignment(Pos.CENTER);
        BuyingHousesBox.getChildren().add(HousesBox);

        BuyHouse1.setOnAction(actionEvent -> {
            propertiesList.get(position-1).buildHouseOnProperty();
            hv.updateHousesOnMap(propertiesList);
            hh.updateHousesOnMap(propertiesList);
            BuyingHousesBox.setVisible(false);
            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - (propertiesList.get(position-1).getHouseCost()));
            dicing.setVisible(true);
            makeUpdateMapANdTables();
            boughtHouses = 1;
        });

        BuyHouse2.setOnAction(actionEvent -> {
            propertiesList.get(position-1).buildHouseOnProperty();
            propertiesList.get(position-1).buildHouseOnProperty();
            hv.updateHousesOnMap(propertiesList);
            hh.updateHousesOnMap(propertiesList);
            BuyingHousesBox.setVisible(false);
            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - (2*propertiesList.get(position-1).getHouseCost()));
            dicing.setVisible(true);
            makeUpdateMapANdTables();
            boughtHouses = 2;
        });

        BuyHouse3.setOnAction(actionEvent -> {
            propertiesList.get(position-1).buildHouseOnProperty();
            propertiesList.get(position-1).buildHouseOnProperty();
            propertiesList.get(position-1).buildHouseOnProperty();
            hv.updateHousesOnMap(propertiesList);
            hh.updateHousesOnMap(propertiesList);
            BuyingHousesBox.setVisible(false);
            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - (3*propertiesList.get(position-1).getHouseCost()));
            dicing.setVisible(true);
            makeUpdateMapANdTables();
            boughtHouses = 3;
        });

        BuyHouse4.setOnAction(actionEvent -> {
            propertiesList.get(position-1).buildHouseOnProperty();
            propertiesList.get(position-1).buildHouseOnProperty();
            propertiesList.get(position-1).buildHouseOnProperty();
            propertiesList.get(position-1).buildHouseOnProperty();
            hv.updateHousesOnMap(propertiesList);
            hh.updateHousesOnMap(propertiesList);
            BuyingHousesBox.setVisible(false);
            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - (4*propertiesList.get(position-1).getHouseCost()));
            dicing.setVisible(true);
            makeUpdateMapANdTables();
            boughtHouses = 4;
        });

        noBuyHouse.setOnAction(actionEvent ->{
            BuyingHousesBox.setVisible(false);
            dicing.setVisible(true);
        } );

        Image map = new Image("/images/eurobussinesmap.png");

        ImageView mapView = new ImageView(map);
        mapView.setFitHeight(800);
        mapView.setFitWidth(800);
        Pane MapPane = new Pane();

        MapPane.getChildren().add(mapView);
        MapPane.getChildren().add(pawnView1);
        MapPane.getChildren().add(pawnView2);
        MapPane.getChildren().add(pawnView3);
        MapPane.getChildren().add(pawnView4);
        hv = new HousesOnPropertiesVertical(MapPane);
        hv.setNoVisibleHousesVertical();
        hh = new HousesOnPropertiesHorizontal(MapPane,propertiesList);
        hh.setNoVisibleHousesHorizontal();

        pawnView1.setVisible(false);
        pawnView2.setVisible(false);
        pawnView3.setVisible(false);
        pawnView4.setVisible(false);

        panelOponents.setVisible(false);
        panelTourPlayer.setVisible(false);
        panelTourPlayerInJail.setVisible(false);
        PlayerBankroupt.setVisible(false);
        endGameBox.setVisible(false);

        githubLink.setOnAction(actionEvent -> {
            if(Desktop.isDesktopSupported())
            {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/bkprog/MonopolyProjekt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        grp.add(MapPane,0,0);
        grp.add(box,1,0);
        grp.add(panelOponents,1,0);
        grp.add(panelTourPlayer,1,0);
        grp.add(panelTourPlayerInJail,1,0);
        grp.add(PlayerBankroupt,1,0);
        grp.add(endGameBox,1,0);
        grp.add(propertiesMap,2,0);
        propertiesMap.setAlignment(Pos.CENTER);
        propertiesMap.setSpacing(10);
        grp.setAlignment(Pos.CENTER);
        grp.setHgap(50);
        box.setSpacing(10);
        Label label = new Label("Udało się połączyć! Witaj przedsiębiorco " + this.playerNickname);
        label.setTextFill(Color.GREEN);
        ObservableList list = box.getChildren();
        clientConnected.setText(respondServer);
        list.addAll(label,clientConnected,readyCheckInfo,readyButton);
        readyButton.setOnAction(e->{
            readyCheckInfo.setText("Świetnie! Już jesteś gotowy, oczekuj na gotowość innych graczy...");
            playersReady++;
            if(playersReady == NubmerOfPlayersInGame){
                clientConnected.setVisible(false);
                readyCheckInfo.setText("Wszyscy gracze są gotowi! Gra się rozpoczyna...");
                readyCheckInfo.setTextFill(Color.GREEN);
            }

            readyButton.setVisible(false);
            try{
                dOut.writeUTF("ReadyCheck" + playerNickname);
            }
            catch(Exception ex){

            }

        });
        oponetsMoveScene();
        TourPlayerScene();
        TourPlayerInJailScene();
        PlayerBankrouptScene();
        endGame();
        box.setAlignment(Pos.CENTER);
        Scene scena = new Scene(grp,1200,800);
        stage.setScene(scena);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try{
                socket.close();
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        });
    }

    public void PlayerBankrouptScene(){
        PlayerBankroupt.getChildren().add(p1);
        PlayerBankroupt.getChildren().add(p2);
        PlayerBankroupt.getChildren().add(p3);
        PlayerBankroupt.getChildren().add(p4);
        Label bankrouptInfo = new Label("Jesteś bankrutem możesz teraz obserwować gre!");
        PlayerBankroupt.setAlignment(Pos.CENTER);
        bankrouptInfo.setAlignment(Pos.CENTER);

        PlayerBankroupt.getChildren().add(bankrouptInfo);

    }

    public void TourPlayerScene(){
        DiceBox.setAlignment(Pos.CENTER);
        DiceBox.setSpacing(50);
        viewDice1.setFitHeight(50);
        viewDice1.setFitWidth(50);
        viewDice2.setFitHeight(50);
        viewDice2.setFitWidth(50);

        Button BuyPropertyBtn = new Button("Kupuje!");
        Button NoBuyPropertyBtn = new Button("Nie, dziękuję!");
        Label BuyPoropertyInfo = new Label("");

        VBox buyingPopertyBox = new VBox();
        HBox buyingPorpertyButtons = new HBox();

        buyingPopertyBox.setAlignment(Pos.CENTER);
        buyingPopertyBox.setSpacing(10);
        buyingPorpertyButtons.setAlignment(Pos.CENTER);
        buyingPorpertyButtons.setSpacing(50);

        ObservableList listPropertyBuying = buyingPorpertyButtons.getChildren();
        listPropertyBuying.addAll(BuyPropertyBtn,NoBuyPropertyBtn);

        buyingPopertyBox.getChildren().addAll(BuyPoropertyInfo,buyingPorpertyButtons);


        buyingPopertyBox.setVisible(false);

        propertyInfo.setAlignment(Pos.CENTER);
        propertyInfo.setSpacing(20);

        Image blankProperty = new Image("images/Properties/blank.png");
        ImageView propertyView = new ImageView(blankProperty);
        Label propertyInformation = new Label("Niestety nie jesteś na polu, które można kupić.\n Nic nie zyskasz i nic nie tracisz!");
        propertyView.setFitWidth(200);
        propertyView.setFitHeight(300);

        ObservableList propertyBox = propertyInfo.getChildren();
        propertyBox.addAll(propertyView,propertyInformation);

        ObservableList diceImages = DiceBox.getChildren();
        diceImages.addAll(viewDice1,viewDice2);

        ObservableList list = panelTourPlayer.getChildren();
        list.addAll(player1,player2,player3,player4,BuyingHousesBox,info,dicing,diceedInfo,DiceBox,propertyInfo,buyingPopertyBox,readyTour);

        panelTourPlayer.setAlignment(Pos.CENTER);
//        readyTour.setVisible(false);

        panelTourPlayer.setSpacing(10);
        dicing.setOnAction(e->{
            hv.updateHousesOnMap(propertiesList);
            hh.updateHousesOnMap(propertiesList);

            buyingPopertyBox.setVisible(true);
            int randomCard = dice.throwQuestionMarkCard();

            dice = new Dice();
            int dice1 = dice.throwfunction();
            int dice2 = dice.throwfunction();


            position = position + dice1 + dice2;

            if(position > 40){
                position = position - 40;
                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                info.setText("Przeszedłeś przez start, zyskujesz 400$!");
                TourPlayerProfile.setPropertyId(position);
                passedStart = 1;
            }
            else{
                TourPlayerProfile.setPropertyId(position);
            }
            //System.out.println("dice 1: " + position);
            if(TourPlayerProfile.getPlayerNumber() == 1){
                pawnView1.setX(cordsPlayersMap.getCorXProperty(position,1));
                pawnView1.setY(cordsPlayersMap.getCorYProperty(position,1));
            }
            else if(TourPlayerProfile.getPlayerNumber() == 2){
                pawnView2.setX(cordsPlayersMap.getCorXProperty(position,2));
                pawnView2.setY(cordsPlayersMap.getCorYProperty(position,2));
            }
            else if(TourPlayerProfile.getPlayerNumber() == 3){
                pawnView3.setX(cordsPlayersMap.getCorXProperty(position,3));
                pawnView3.setY(cordsPlayersMap.getCorYProperty(position,3));
            }
            else if(TourPlayerProfile.getPlayerNumber() == 4){
                pawnView4.setX(cordsPlayersMap.getCorXProperty(position,4));
                pawnView4.setY(cordsPlayersMap.getCorYProperty(position,4));
            }


            Properties actualProperty = propertiesList.get(position-1);

            if(propertyCardLoadImage(position)){
                propertyInformation.setText("Stoisz na polu, które można zakupić o nazwie " + actualProperty.getNameProperty() + ". Koszt zakupu: " + actualProperty.getBuyCost());
                Image propertyCard = new Image("/images/Properties/property-" + position + ".png");
                propertyView.setImage(propertyCard);

            }
            else{
                if(position == 31){
                    position = 11;
                    isInPrisonMessage = 1;
                    if(TourPlayerProfile.getPlayerNumber() == 1){
                        pawnView1.setX(cordsPlayersMap.getCorXProperty(position,1));
                        pawnView1.setY(cordsPlayersMap.getCorYProperty(position,1));
                    }
                    else if(TourPlayerProfile.getPlayerNumber() == 2){
                        pawnView2.setX(cordsPlayersMap.getCorXProperty(position,2));
                        pawnView2.setY(cordsPlayersMap.getCorYProperty(position,2));
                    }
                    else if(TourPlayerProfile.getPlayerNumber() == 3){
                        pawnView3.setX(cordsPlayersMap.getCorXProperty(position,3));
                        pawnView3.setY(cordsPlayersMap.getCorYProperty(position,3));
                    }
                    else if(TourPlayerProfile.getPlayerNumber() == 4){
                        pawnView4.setX(cordsPlayersMap.getCorXProperty(position,4));
                        pawnView4.setY(cordsPlayersMap.getCorYProperty(position,4));
                    }
                    Image prisonCard = new Image("/images/Properties/property-11j.png");
                    propertyView.setImage(prisonCard);
                    propertyInformation.setText("Idziesz do więzienia!!!");
                    TourPlayerProfile.setInJail(true);
                }
                else{
                    String Cardname = propertiesList.get(position-1).getNameProperty();
                    System.out.println(propertiesList.get(position-1).getNameProperty());
                    if(Cardname.startsWith("Red")){
                        cardNumber = cardId4Server(randomCard);
                        propertyInformation.setText("Red Questionmark Field!");
                        Image RedQuestionMarkCard = new Image("/images/RedBlueCards/RedCards/" + randomCard + ".png");
                        propertyView.setImage(RedQuestionMarkCard);
                        if(randomCard == 1){
                            position = 15;
                        }
                        else if(randomCard == 2){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 100);
                        }
                        else if(randomCard == 3){
                            int destination = 36;
                            if(position >= destination){
                                System.out.println("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                passedStart = 1;
                            }
                            position = destination;
                        }
                        else if(randomCard == 4){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 300);
                        }
                        else if(randomCard == 5){
                            int destination = 24;
                            if(position >= destination){
                                System.out.println("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                passedStart = 1;
                            }
                            position = destination;
                        }
                        else if(randomCard == 6){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 40);
                        }
                        else if(randomCard == 7){
                            int destination = 7;
                            if(position >= destination){
                                System.out.println("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                passedStart = 1;
                            }
                            position = destination;
                        }
                        else if(randomCard == 8){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 300);
                        }
                        else if(randomCard == 9){
                            position = 11;
                            isInPrisonMessage = 1;
                            if(TourPlayerProfile.getPlayerNumber() == 1){
                                pawnView1.setX(cordsPlayersMap.getCorXProperty(position,1));
                                pawnView1.setY(cordsPlayersMap.getCorYProperty(position,1));
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 2){
                                pawnView2.setX(cordsPlayersMap.getCorXProperty(position,2));
                                pawnView2.setY(cordsPlayersMap.getCorYProperty(position,2));
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 3){
                                pawnView3.setX(cordsPlayersMap.getCorXProperty(position,3));
                                pawnView3.setY(cordsPlayersMap.getCorYProperty(position,3));
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 4){
                                pawnView4.setX(cordsPlayersMap.getCorXProperty(position,4));
                                pawnView4.setY(cordsPlayersMap.getCorYProperty(position,4));
                            }
                            propertyInformation.setText("Idziesz do więzienia!!!");
                            TourPlayerProfile.setInJail(true);
                        }
                        else if(randomCard == 10){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 200);
                        }
                        else if(randomCard == 11){
                            position = 1;
                        }
                        else if(randomCard == 12){
                            position = position - 3;
                            if(position == 0)
                                position = 40;
                        }
                        else if(randomCard == 13){
                            position = position + 3;
                            if(position > 40){
                                position = position - 40;
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                info.setText("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setPropertyId(position);
                                passedStart = 1;
                            }
                        }
                        else if(randomCard == 14){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 30);
                        }
                        else if(randomCard == 15){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                        }
                        else if(randomCard == 16){
                            position = 40;
                        }
                    }
                    else if(Cardname.startsWith("Blue")){
                        cardNumber = cardId4Server(randomCard);
                        propertyInformation.setText("Blue Questionmark Field!");
                        Image BlueQuestionMarkCard = new Image("/images/RedBlueCards/BlueCards/" + randomCard + ".png");
                        propertyView.setImage(BlueQuestionMarkCard);
                        if(randomCard == 1){
                            position = 15;
                        }
                        else if(randomCard == 2){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 100);
                        }
                        else if(randomCard == 3){
                            int destination = 36;
                            if(position >= destination){
                                System.out.println("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                passedStart = 1;
                            }
                            position = destination;
                        }
                        else if(randomCard == 4){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 300);
                        }
                        else if(randomCard == 5){
                            int destination = 24;
                            if(position >= destination){
                                System.out.println("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                passedStart = 1;
                            }
                            position = destination;
                        }
                        else if(randomCard == 6){
                            if(TourPlayerProfile.getCash() >= 40)
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 40);
                            else
                                isBankroupt = 1;
                        }
                        else if(randomCard == 7){
                            int destination = 7;
                            if(position >= destination){
                                System.out.println("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                passedStart = 1;
                            }
                            position = destination;
                        }
                        else if(randomCard == 8){
                            if(TourPlayerProfile.getCash() >= 300)
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 300);
                            else
                                isBankroupt = 1;
                        }
                        else if(randomCard == 9){
                            position = 11;
                            isInPrisonMessage = 1;
                            if(TourPlayerProfile.getPlayerNumber() == 1){
                                pawnView1.setX(cordsPlayersMap.getCorXProperty(position,1));
                                pawnView1.setY(cordsPlayersMap.getCorYProperty(position,1));
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 2){
                                pawnView2.setX(cordsPlayersMap.getCorXProperty(position,2));
                                pawnView2.setY(cordsPlayersMap.getCorYProperty(position,2));
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 3){
                                pawnView3.setX(cordsPlayersMap.getCorXProperty(position,3));
                                pawnView3.setY(cordsPlayersMap.getCorYProperty(position,3));
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 4){
                                pawnView4.setX(cordsPlayersMap.getCorXProperty(position,4));
                                pawnView4.setY(cordsPlayersMap.getCorYProperty(position,4));
                            }
                            propertyInformation.setText("Idziesz do więzienia!!!");
                            TourPlayerProfile.setInJail(true);
                        }
                        else if(randomCard == 10){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 200);
                        }
                        else if(randomCard == 11){
                            position = 1;
                        }
                        else if(randomCard == 12){
                            position = position - 3;
                            if(position == 0)
                                position = 40;
                        }
                        else if(randomCard == 13){
                            position = position + 3;
                            if(position > 40){
                                position = position - 40;
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                                info.setText("Przeszedłeś przez start, otrzymujesz 400$!");
                                TourPlayerProfile.setPropertyId(position);
                                passedStart = 1;
                            }
                        }
                        else if(randomCard == 14){
                            if(TourPlayerProfile.getCash() >= 30)
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 30);
                            else
                                isBankroupt = 1;
                        }
                        else if(randomCard == 15){
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() + 400);
                        }
                        else if(randomCard == 16){
                            position = 40;
                        }
                    }
                    else{
                        propertyInformation.setText("Nie jesteś na polu, które możesz kupić. \n Nic nie zyskasz i nic nie tracisz.");
                        propertyView.setImage(blankProperty);
                    }
                }
            }


            Properties actualNEWProperty = propertiesList.get(position-1);
            if(propertyBuyable(propertiesList.get(position-1).getNameProperty())){
                if(propertiesList.get(position-1).getOwnerID() == 0){

                    if(TourPlayerProfile.getCash() >= actualNEWProperty.getBuyCost()){
                        BuyPoropertyInfo.setText("Czy chcesz kupić posiadłość?");
                        BuyPropertyBtn.setVisible(true);
                        NoBuyPropertyBtn.setVisible(true);
                        buyingPopertyBox.setVisible(true);
                        BuyPropertyBtn.setOnAction(event->{

                            buyPropertyMessage = 1;
                            BuyPoropertyInfo.setText("");
                            readyTour.setVisible(true);
                            BuyPropertyBtn.setVisible(false);
                            NoBuyPropertyBtn.setVisible(false);
                            TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualNEWProperty.getBuyCost());
                            actualNEWProperty.setOwnerID(TourPlayerProfile.getPlayerNumber());
                            propertyInformation.setText("Zakupiłeś " + actualNEWProperty.getNameProperty() + " w kraju " + actualNEWProperty.getCountryName() + ", teraz inny gracz będzie ci płacić: " + actualNEWProperty.getPaymentForStay() + "$");

                            if(TourPlayerProfile.getPlayerNumber() == 1){
                                player1.setText("Gracz 1 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + actualNEWProperty.getNameProperty());
                                p1.setText(player1.getText());
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 2){
                                player2.setText("Gracz 2 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + actualNEWProperty.getNameProperty());
                                p2.setText(player2.getText());
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 3){
                                player3.setText("Gracz 3 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + actualNEWProperty.getNameProperty());
                                p3.setText(player3.getText());
                            }
                            else if(TourPlayerProfile.getPlayerNumber() == 4){
                                player4.setText("Gracz 4 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + actualNEWProperty.getNameProperty());
                                p4.setText(player4.getText());
                            }

                            if(playersList.size() >= 2){
                                player1Map.updatePropertiesMap(propertiesList,playersList.get(0));
                                player2Map.updatePropertiesMap(propertiesList,playersList.get(1));
                                if(playersList.size() >= 3){
                                    player3Map.updatePropertiesMap(propertiesList,playersList.get(2));
                                    if(playersList.size() == 4){
                                        player4Map.updatePropertiesMap(propertiesList,playersList.get(3));
                                    }
                                }
                            }
                        });
                        NoBuyPropertyBtn.setOnAction(actionEvent -> {
                            BuyPoropertyInfo.setText("");
                            readyTour.setVisible(true);
                            BuyPropertyBtn.setVisible(false);
                            NoBuyPropertyBtn.setVisible(false);
                        });
                    }
                    else{
                        propertyInformation.setText("Nie stać cię na tą posiadłość!");
                        BuyPropertyBtn.setVisible(false);
                        NoBuyPropertyBtn.setVisible(false);
                        buyingPopertyBox.setVisible(false);
                        readyTour.setVisible(true);
                    }
                }
                else if(propertiesList.get(position-1).getOwnerID() == TourPlayerProfile.getPlayerNumber()){
                    BuyPropertyBtn.setVisible(false);
                    NoBuyPropertyBtn.setVisible(false);
                    buyingPopertyBox.setVisible(false);
                    readyTour.setVisible(true);
                    propertyInformation.setText("Jesteś w domu!");
                }
                else{
                    if(playersList.get(actualNEWProperty.getOwnerID()-1).getIsInJail()){
                        readyTour.setVisible(true);
                        propertyInformation.setText("Właściciel tego pola jest w więzieniu, nie musisz nic płacić! Hurra!");
                    }
                    else{
                        BuyPropertyBtn.setVisible(false);
                        NoBuyPropertyBtn.setVisible(false);
                        buyingPopertyBox.setVisible(false);
                        readyTour.setVisible(true);
                        Player oponent = playersList.get(actualNEWProperty.getOwnerID()-1);
                        Properties actualPropertyPlayer = propertiesList.get(position-1);
                        if(position == 6 || position == 16 || position == 26 || position == 36){
                            int ownedStations = 0;
                            for(Properties prop : propertiesList){
                                if(prop.getIDproperty() == 6 || prop.getIDproperty() == 16 ||prop.getIDproperty() == 26 ||prop.getIDproperty() == 36){
                                    if(prop.getOwnerID() == oponent.getPlayerNumber()){
                                        ownedStations++;
                                    }
                                }
                            }
//                            System.out.println("To pole posiada: " + actualPropertyPlayer.getOwnerID());
//                            System.out.println("This player owned Stations: " + ownedStations);
                            if(ownedStations == 1){
                                if(TourPlayerProfile.getCash() >= actualPropertyPlayer.getPaymentForStay()){
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getPaymentForStay());
                                    oponent.setCash(oponent.getCash() + actualPropertyPlayer.getPaymentForStay());
                                    info.setText("Płacisz " + actualPropertyPlayer.getPaymentForStay() + "$ graczowi: " + oponent.getPlayerName());
                                }
                                else
                                    isBankroupt = 1;
                            }
                            else if(ownedStations == 2){
                                if(TourPlayerProfile.getCash() >= actualPropertyPlayer.getLvl1()){
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getLvl1());
                                    oponent.setCash(oponent.getCash() + actualPropertyPlayer.getLvl1());
                                    info.setText("Płacisz " + actualPropertyPlayer.getLvl1() + "$ graczowi: " + oponent.getPlayerName());
                                }
                                else
                                    isBankroupt = 1;

                            }
                            else if(ownedStations == 3){
                                if(TourPlayerProfile.getCash() >= actualPropertyPlayer.getLvl2()){
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getLvl2());
                                    oponent.setCash(oponent.getCash() + actualPropertyPlayer.getLvl2());
                                    info.setText("Płacisz " + actualPropertyPlayer.getLvl2() + "$ graczowi: " + oponent.getPlayerName());
                                }
                                else
                                    isBankroupt = 1;

                            }
                            else if(ownedStations == 4){
                                if(TourPlayerProfile.getCash() >= actualPropertyPlayer.getLvl3()){
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualPropertyPlayer.getLvl3());
                                    oponent.setCash(oponent.getCash() + actualPropertyPlayer.getLvl3());
                                    info.setText("Płacisz " + actualPropertyPlayer.getLvl3() + "$ graczowi: " + oponent.getPlayerName());
                                }
                                else
                                    isBankroupt = 1;

                            }
                        }
                        else if(position == 13 || position == 29){
                            int sumDices = dice1 + dice2;
                            int ownedPW = 0;
                            for(Properties prop : propertiesList){
                                if(prop.getIDproperty() == 13 || prop.getIDproperty() == 29){
                                    if(prop.getOwnerID() == oponent.getPlayerNumber()){
                                        ownedPW++;
                                    }
                                }
                            }
                            if(ownedPW == 1){
                                int fine = sumDices * 10;
                                if(TourPlayerProfile.getCash() >= fine){
                                    info.setText("Płacisz " + fine + "$ za postój dla gracza " + oponent.getPlayerName());
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - fine);
                                    oponent.setCash(oponent.getCash() + fine);
                                }
                                else
                                    isBankroupt = 1;

                            }
                            else if(ownedPW == 2){
                                int fine = sumDices * 20;
                                if(TourPlayerProfile.getCash() >= fine){
                                    info.setText("Płacisz " + fine + "$ za postój dla gracza " + oponent.getPlayerName());
                                    TourPlayerProfile.setCash(TourPlayerProfile.getCash() - fine);
                                    oponent.setCash(oponent.getCash() + fine);
                                }
                                else
                                    isBankroupt = 1;

                            }
                        }
                        else{
                            if(TourPlayerProfile.getCash() >= actualNEWProperty.getPaymentForStay()){
                                //System.out.println( TourPlayerProfile.getPlayerName() + " płaci graczowi " + playersList.get(actualNEWProperty.getOwnerID()-1).getPlayerName());
                                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - actualNEWProperty.getPaymentForStay());
                                playersList.get(actualNEWProperty.getOwnerID()-1).setCash(playersList.get(actualNEWProperty.getOwnerID()-1).getCash() + actualNEWProperty.getPaymentForStay());
                                propertyInformation.setText("To pole ma właściciela: " + playersList.get(actualNEWProperty.getOwnerID()-1).getPlayerName() + ", musisz mu zapłacić " + actualNEWProperty.getPaymentForStay() + "$");
                            }
                            else
                                isBankroupt = 1;

                        }
                    }
                }

            }
            else{
                if(position == 5){
                    if(TourPlayerProfile.getCash() >= 400){
                        propertyInformation.setText("Stoisz na " + actualProperty.getNameProperty() + ", opłata za postój wynosi: " + actualProperty.getPaymentForStay() + "$");
                        TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 400);
                    }
                    else{
//                        propertyInformation.setText("You are Bankroupt Your account is now 0$");
//                        TourPlayerProfile.setCash(0);
//                        TourPlayerProfile.makeBankropt();
                        isBankroupt = 1;
                    }

                }
                else if(position == 39){
                    if(TourPlayerProfile.getCash() >= 200){
                        propertyInformation.setText("Stoisz na " + actualProperty.getNameProperty() + ", opłata za postój wynosi: " + actualProperty.getPaymentForStay() + "$");
                        TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 200);
                    }
                    else{
//                        propertyInformation.setText("You are Bankroupt Your account is now 0$");
//                        TourPlayerProfile.setCash(0);
//                        TourPlayerProfile.makeBankropt();
                        isBankroupt = 1;
                    }
                }
                BuyPropertyBtn.setVisible(false);
                NoBuyPropertyBtn.setVisible(false);
                readyTour.setVisible(true);
            }

            //System.out.println("dice 2: " + position);
            TourPlayerProfile.setPropertyId(position);
            makeUpdateMapANdTables();

            dicing.setVisible(false);
            String dd = "Wyrzuciłeś: " + dice1 + " i " + dice2 + " suma oczek: " + (dice1+dice2);
            Image imageDice1 = new Image("images/Dice/dice" + dice1 + ".png");
            Image imageDice2 = new Image("images/Dice/dice" + dice2 + ".png");
            viewDice1.setImage(imageDice1);
            viewDice2.setImage(imageDice2);
            diceedInfo.setText(dd);

        });

        readyTour.setOnAction(e->{
            PlayerToursReady++;
            SendPlayerTourReadyCurrentPlayer();
            if(PlayerToursReady == NubmerOfPlayersInGame){
                SendAllPlayersReady();
                PlayerToursReady = 0;
                diceedInfo.setText("");
                DiceBox.setVisible(false);
            }
//            panelTourPlayer.setVisible(false);
            readyTour.setVisible(false);
        });
    }

    public void oponetsMoveScene(){
        Label oponentsMoveInfo = new Label("Kolej innego gracza, czekaj na swoją kolej!");
        panelOponents.setAlignment(Pos.CENTER);

        ObservableList list = panelOponents.getChildren();
        p1.setText(player1.getText());
        p2.setText(player2.getText());
        p3.setText(player3.getText());
        p4.setText(player4.getText());
        list.addAll(p1,p2,p3,p4,oponentsMoveInfo,informationOponentPanel,GetReadyTourButton);

        panelOponents.setSpacing(10);
        GetReadyTourButton.setOnAction(e->{
            informationOponentPanel.setText("Oczekiwanie na resztę graczy...");
//            GetReadyTourButton.setVisible(false);
            PlayerToursReady++;
            SendPlayerTourReady();
            if(PlayerToursReady == NubmerOfPlayersInGame){
                SendAllPlayersReady();
                PlayerToursReady = 0;
                informationOponentPanel.setText("");
            }
//            panelOponents.setVisible(false);
            GetReadyTourButton.setVisible(false);
        });
    }

    public void TourPlayerInJailScene(){
        PrisonReady.setVisible(false);
        panelTourPlayerInJail.setAlignment(Pos.CENTER);
        Label prisonInfo1 = new Label("Jesteś w więzieniu!");
        Label prisonInfo2 = new Label("Masz dwie opcje: rzuć kotkami lub zapłać 400$");
        Label prisonInfo3 = new Label("Rzut kostkami - Jeśli wyrzucisz parę oczek, wychodzisz z więzienia");
        Label prisonInfo4 = new Label("Zapłata - Płacąc 400$ wychodzisz z więzienia");

        prisonInfo5.setTextFill(Color.RED);

        prisonInfo5.setVisible(false);
        prisonInfo6.setVisible(false);
        HBox decisionButtons = new HBox();
        HBox diceImages = new HBox();
        diceImages.getChildren().addAll(dice1ViewPrison,dice2ViewPrison);
        dice1ViewPrison.setFitHeight(50);
        dice1ViewPrison.setFitWidth(50);
        dice2ViewPrison.setFitHeight(50);
        dice2ViewPrison.setFitWidth(50);
        diceImages.setAlignment(Pos.CENTER);
        diceImages.setSpacing(50);
        decisionButtons.setSpacing(70);
        diceImages.setVisible(true);
        decisionButtons.getChildren().addAll(diceButtonPrison,payButton);
        decisionButtons.setVisible(true);
        decisionButtons.setAlignment(Pos.CENTER);
        panelTourPlayerInJail.setSpacing(10);

        ObservableList componentsList = panelTourPlayerInJail.getChildren();
        componentsList.addAll(prisonInfo1,prisonInfo2,prisonInfo3,prisonInfo4,prisonInfo5,decisionButtons,diceImages,prisonInfo6,PrisonReady);

        payButton.setOnAction(actionEvent -> {
            if(TourPlayerProfile.getCash() >= 400){
                //Properties actualProperty = propertiesList.get(TourPlayerProfile.getPropertyId()-1);
                payButton.setVisible(false);
                diceButtonPrison.setVisible(false);
                prisonInfo6.setText("Świetnie! Zapłaciłeś 400$ i jesteś wolny!");
                TourPlayerProfile.setCash(TourPlayerProfile.getCash() - 400);
                TourPlayerProfile.setInJail(false);

                if(TourPlayerProfile.getPlayerNumber() == 1){
                    player1.setText("Gracz 1 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + propertiesList.get(10).getNameProperty());
                    p1.setText(player1.getText());
                }
                else if(TourPlayerProfile.getPlayerNumber() == 2){
                    player2.setText("Gracz 2 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + propertiesList.get(10).getNameProperty());
                    p2.setText(player2.getText());
                }
                else if(TourPlayerProfile.getPlayerNumber() == 3){
                    player3.setText("Gracz 3 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + propertiesList.get(10).getNameProperty());
                    p3.setText(player3.getText());
                }
                else if(TourPlayerProfile.getPlayerNumber() == 4){
                    player4.setText("Gracz 4 : " + TourPlayerProfile.getPlayerName() + " jego pieniądze: " + TourPlayerProfile.getCash() + "$" + " stoi na: " + propertiesList.get(10).getNameProperty());
                    p4.setText(player4.getText());
                }

                PrisonReady.setVisible(true);
                playerPayedFinePrison = 1;
                isInPrisonMessage = 0;
            }
            else{
                prisonInfo5.setText("Nie posiadasz 400$, musisz rzucić kostkami!");
            }
        });

        diceButtonPrison.setOnAction(ev ->{
            dice = new Dice();
            int dice1 = dice.throwfunction();
            int dice2 = dice.throwfunction();
            Image dicePrison1 = new Image("/images/Dice/dice" + dice1 + ".png");
            Image dicePrison2 = new Image("/images/Dice/dice" + dice2 + ".png");

            dice1ViewPrison.setImage(dicePrison1);
            dice2ViewPrison.setImage(dicePrison2);

            if(dice1 == dice2){
                prisonInfo6.setText("Wyrzuciłeś parę oczek, wychodzisz z więzienia");
                TourPlayerProfile.setInJail(false);
                isInPrisonMessage = 0;
            }
            else {
                prisonInfo6.setText("Może następnym razem...!");
                isInPrisonMessage = 1;
            }

            PrisonReady.setVisible(true);
            payButton.setVisible(false);
            diceButtonPrison.setVisible(false);
            playerPayedFinePrison = 0;
        });


        PrisonReady.setOnAction(actionEvent -> {
            PlayerToursReady++;
            SendPlayerTourReadyCurrentPlayer();
            if(PlayerToursReady == NubmerOfPlayersInGame){
                SendAllPlayersReady();
                PlayerToursReady = 0;
                diceedInfo.setText("");
                DiceBox.setVisible(false);
            }
            prisonInfo5.setVisible(false);
            prisonInfo6.setVisible(false);
//            panelTourPlayer.setVisible(false);
            PrisonReady.setVisible(false);
        });


    }

    public void createConnection(){
        try {
            int portServer = Integer.parseInt(portTFServer.getText());
            socket = new Socket(ipTFServer.getText(), portServer);
            dOut = new DataOutputStream(socket.getOutputStream());
            dIn = new DataInputStream(socket.getInputStream());

        }
        catch (IOException exc) {
        }
    }
    public void SendPlayerTourReady(){
        if(sendOnceReadyTour){
            try{
                dOut.writeUTF("PlayerTourReady");
            }
            catch (Exception ex){

            }
            sendOnceReadyTour = false;
        }

    }
    public void SendPlayerTourReadyCurrentPlayer(){
        if(sendOnceReadyTour){
            try{
                dOut.writeUTF("PlayerTourReady "+ recentBankrouptPlayer + " " + getBankuptOtherPlayer + " "+ isBankroupt + " " + boughtHouses + " " + passedStart + " " +  cardNumber + " " + isInPrisonMessage + " " + playerPayedFinePrison + " " + buyPropertyMessage + " " + TourPlayerProfile.getPlayerNumber() + " " + position);
            }
            catch (Exception ex){

            }
            if(isBankroupt == 1){
                UpdatePropetiesWhenBankroupt(propertiesList,TourPlayerProfile.getPlayerNumber());
                hh.updateHousesOnMap(propertiesList);
                hv.updateHousesOnMap(propertiesList);
                TourPlayerProfile.makeBankropt();
                PlayerSesionBankroupt = 1;
                if(TourPlayerProfile.getPlayerNumber() == 1){
                    pawnView1.setVisible(false);
                    player1Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                }
                else if(TourPlayerProfile.getPlayerNumber() == 2){
                    pawnView2.setVisible(false);
                    player2Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                }
                else if(TourPlayerProfile.getPlayerNumber() == 3){
                    pawnView3.setVisible(false);
                    player3Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                }
                else if(TourPlayerProfile.getPlayerNumber() == 4){
                    pawnView4.setVisible(false);
                    player4Map.BankrouptInfoUpdate(TourPlayerProfile.isBankroupt());
                }
                UpdatePropetiesWhenBankroupt(propertiesList,TourPlayerProfile.getPlayerNumber());
            }
            getBankuptOtherPlayer = 0;
            boughtHouses = 0;
            passedStart = 0;
            cardNumber = '0';
            isInPrisonMessage = 0;
            playerPayedFinePrison = 0;
            buyPropertyMessage = 0;
            isBankroupt = 0;
            sendOnceReadyTour = false;
        }

    }
    public void SendAllPlayersReady(){
        if(sendOnceReadyALLTour){
            try{
                dOut.writeUTF("allPlayersTourReady");
            }
            catch (Exception ex){

            }
            sendOnceReadyALLTour = false;
        }
    }

    public boolean propertyCardLoadImage(int propertyNumber){
        if(propertyNumber != 3 &&
                propertyNumber != 8 &&
                propertyNumber != 18 &&
                propertyNumber != 23 &&
                propertyNumber != 31 &&
                propertyNumber != 34 &&
                propertyNumber != 37)
            return true;
        else
            return false;
    }

    public static boolean propertyBuyable(String property){
        if(property.startsWith("Blue")
                || property.startsWith("Red") || property.startsWith("Podatek") ||
                property.startsWith("Parking") || property.startsWith("Wiezienie") ||
                property.startsWith("Go!") || property.startsWith("Idziesz") ||
                property.startsWith("Darmowy"))
            return false;
        else
            return true;
    }

    public void makeUpdateMapANdTables(){
        for(Player p : playersList){
            Properties actualPlayerProperty;
            if(p.getPropertyId() == 0){
                actualPlayerProperty = propertiesList.get(p.getPropertyId());
            }
            else{
                actualPlayerProperty = propertiesList.get(p.getPropertyId()-1);
            }
            if(p.getPlayerNumber() == 1){
                if(p.getIsInJail())
                    player1.setText("Gracz 1 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: Jest w więzieniu!!!");
                else
                    player1.setText("Gracz 1 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: " + actualPlayerProperty.getNameProperty());
                pawnView1.setX(cordsPlayersMap.getCorXProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                pawnView1.setY(cordsPlayersMap.getCorYProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                p1.setText(player1.getText());
            }
            else if(p.getPlayerNumber() == 2){
                if(p.getIsInJail())
                    player2.setText("Gracz 2 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: Jest w więzieniu!!!");
                else
                    player2.setText("Gracz 2 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: " + actualPlayerProperty.getNameProperty());
                pawnView2.setX(cordsPlayersMap.getCorXProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                pawnView2.setY(cordsPlayersMap.getCorYProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                p2.setText(player2.getText());
            }
            else if(p.getPlayerNumber() == 3){
                if(p.getIsInJail())
                    player3.setText("Gracz 3 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: Jest w więzieniu!!!");
                else
                    player3.setText("Gracz 3 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: " + actualPlayerProperty.getNameProperty());
                pawnView3.setX(cordsPlayersMap.getCorXProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                pawnView3.setY(cordsPlayersMap.getCorYProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                p3.setText(player3.getText());
            }
            else if(p.getPlayerNumber() == 4){
                if(p.getIsInJail())
                    player4.setText("Gracz 4 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: Jest w więzieniu!!!");
                else
                    player4.setText("Gracz 4 : " + p.getPlayerName() + " jego pieniądze: " + p.getCash() + "$" + " stoi na: " + actualPlayerProperty.getNameProperty());
                pawnView4.setX(cordsPlayersMap.getCorXProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                pawnView4.setY(cordsPlayersMap.getCorYProperty(actualPlayerProperty.getIDproperty(),p.getPlayerNumber()));
                p4.setText(player4.getText());
            }
        }
    }

    public void ifMorePropertiesThan40DeleteUnsessesery(){
        Iterator itr = propertiesList.iterator();
        int i = 1;
        while(itr.hasNext()){
            Properties x = (Properties)itr.next();
            if(i > 40){
                itr.remove();
            }
            i++;
        }
        //System.out.println("Size of properties list is: " + propertiesList.size());
    }

    public static char cardId4Server(int cardID){
        switch (cardID){
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            case 10: return 'a';
            case 11: return 'b';
            case 12: return 'c';
            case 13: return 'd';
            case 14: return 'e';
            case 15: return 'f';
            case 16: return 'g';
        }
        return '0';
    }

    public static int card4ClientFromServer(char cardID){
        switch (cardID){
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'a': return 10;
            case 'b': return 11;
            case 'c': return 12;
            case 'd': return 13;
            case 'e': return 14;
            case 'f': return 15;
            case 'g': return 16;
        }
        return 0;
    }

    public ArrayList<String> allCountriesPlayers(ArrayList<Properties> propertiesList,Player player) {
        ArrayList<String> countries = new ArrayList<>();
        boolean g = true,i = true,s = true,e = true,b = true,S = true,r = true,a = true;
        int Greece = 0;
        int Italy = 0;
        int Spain = 0;
        int England = 0;
        int Benelux = 0;
        int Sweden = 0;
        int RFN = 0;
        int Austria = 0;
        for (Properties p : propertiesList) {
            if (p.getCountryName().equals("Grecja")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    Greece++;
                if (Greece == 2 && g){
                    countries.add("Grecja");
                    g = false;
                }
            } else if (p.getCountryName().equals("Włochy")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    Italy++;
                if (Italy == 3 && i){
                    countries.add("Włochy");
                    i = false;
                }
            } else if (p.getCountryName().equals("Hiszpania")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    Spain++;
                if (Spain == 3 && s){
                    countries.add("Hiszpania");
                    s = false;
                }
            } else if (p.getCountryName().equals("Anglia")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    England++;
                if (England == 3 && e){
                    countries.add("Anglia");
                    e = false;
                }
            } else if (p.getCountryName().equals("Benelux")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    Benelux++;
                if (Benelux == 3 && b){
                    countries.add("Benelux");
                    b = false;
                }
            } else if (p.getCountryName().equals("Szwecja")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    Sweden++;
                if (Sweden == 3 && S){
                    countries.add("Szwecja");
                    S = false;
                }
            } else if (p.getCountryName().equals("RFN")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    RFN++;
                if (RFN == 3 && r){
                    countries.add("RFN");
                    r = false;
                }
            } else if (p.getCountryName().equals("Austria")) {
                if (player.getPlayerNumber() == p.getOwnerID())
                    Austria++;
                if (Austria == 2 && a){
                    countries.add("Austria");
                    a = false;
                }
            }
        }
        return countries;
    }

    public void UpdatePropetiesWhenBankroupt(ArrayList<Properties> propertiesArrayList, int playerNumber){
        for(Properties property : propertiesArrayList){
            if(property.getOwnerID() == playerNumber){
                property.destroyHouses();
                property.setOwnerID(0);
            }
        }
    }

}
