package Server;

import Server.Session;
import Source.DataReaderProperties;
import Source.Properties;
import Source.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class Server {

    private static ArrayList<Socket> socketList = new ArrayList<Socket>();

    private static ArrayList<Player> playersList = new ArrayList<>();
    private static ArrayList<Properties> propertiesList = new ArrayList<>();
    private static DataReaderProperties readerProperties = new DataReaderProperties();

    public static void main(String[] args) throws IOException, FileNotFoundException {

        while(true){
            Scanner scanner = new Scanner(System.in);
            String input;
            String portServer;
            System.out.println("IP servera to: " + InetAddress.getLocalHost());

            System.out.println("Podaj port servera:");
            portServer = scanner.nextLine();
            do{

                System.out.println("Enter number of players: ");
                input = scanner.nextLine();

            }while(Integer.parseInt(input)<2 || Integer.parseInt(input)>4);

            System.out.println("Number of slots in game: " + input);

            try(ServerSocket serverSocket = new ServerSocket(Integer.parseInt(portServer))){
                int numberOfPlayers = Integer.parseInt(input);
                for(int i = 0; i<numberOfPlayers; i++){
                    if(i==0){
                        System.out.println("Server is waiting for clients connection :)");
                    }

                    Socket client = serverSocket.accept();
                    socketList.add(client);
                    System.out.println( i+1 + " Client connected!");
                    Session ss = new Session(client,socketList,i+1,numberOfPlayers,playersList,readerProperties.getPropertieslist());
                    ss.start();
                }

            }
            catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
            break;
        }
    }
}
/** TEST CZY DZIALA **/