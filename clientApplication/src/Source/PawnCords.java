package Source;

import java.util.ArrayList;

public class PawnCords {
    private int corXp1;
    private int corYp1;

    private int corXp2;
    private int corYp2;

    private int corXp3;
    private int corYp3;

    private int corXp4;
    private int corYp4;

    private int idProperty;
    ArrayList<Cords> cordsArrayList = new ArrayList<>();


    public PawnCords(){
        Cords cord1 = new Cords(730,740,760,740,730,770,760,770);

        Cords cord2 = new Cords(645,740,670,740,645,770,670,770);
        Cords cord3 = new Cords(575,740,605,740,575,770,605,770);
        Cords cord4 = new Cords(510,740,535,740,510,770,535,770);
        Cords cord5 = new Cords(445,740,470,740,445,770,470,770);
        Cords cord6 = new Cords(380,740,400,740,380,770,400,770);
        Cords cord7 = new Cords(310,740,335,740,310,770,335,770);
        Cords cord8 = new Cords(245,740,265,740,245,770,265,770);
        Cords cord9 = new Cords(175,740,200,740,175,770,200,770);
        Cords cord10 = new Cords(110,740,135,740,110,770,135,770);

        Cords cord11 = new Cords(15,740,40,740,15,770,40,770);

        Cords cord12 = new Cords(15,645,40,645,15,670,40,670);
        Cords cord13 = new Cords(15,575,40,575,15,600,40,600);
        Cords cord14 = new Cords(15,510,40,510,15,535,40,535);
        Cords cord15 = new Cords(15,445,40,445,15,470,40,470);
        Cords cord16 = new Cords(15,380,40,380,15,405,40,405);
        Cords cord17 = new Cords(15,310,40,310,15,335,40,335);
        Cords cord18 = new Cords(15,245,40,245,15,270,40,270);
        Cords cord19 = new Cords(15,175,40,175,15,200,40,200);
        Cords cord20 = new Cords(15,110,40,110,15,135,40,135);

        Cords cord21 = new Cords(15,15,40,15,15,40,40,40);

        Cords cord22 = new Cords(110,15,135,15,110,40,135,40);
        Cords cord23 = new Cords(175,15,200,15,175,40,200,40);
        Cords cord24 = new Cords(245,15,265,15,245,40,265,40);
        Cords cord25 = new Cords(310,15,335,15,310,40,335,40);
        Cords cord26 = new Cords(380,15,400,15,380,40,400,40);
        Cords cord27 = new Cords(445,15,470,15,445,40,470,40);
        Cords cord28 = new Cords(510,15,535,15,510,40,535,40);
        Cords cord29 = new Cords(575,15,605,15,575,40,605,40);
        Cords cord30 = new Cords(645,15,670,15,645,40,670,40);

        Cords cord31 = new Cords(730,15,760,15,730,40,760,40);

        Cords cord32 = new Cords(740,135,770,135,740,110,770,110);
        Cords cord33 = new Cords(740,200,770,200,740,175,770,175);
        Cords cord34 = new Cords(740,270,770,270,740,245,770,245);
        Cords cord35 = new Cords(740,335,770,335,740,310,770,310);
        Cords cord36 = new Cords(740,405,770,405,740,380,770,380);
        Cords cord37 = new Cords(740,470,770,470,740,445,770,445);
        Cords cord38 = new Cords(740,535,770,535,740,510,770,510);
        Cords cord39 = new Cords(740,600,770,600,740,575,770,575);
        Cords cord40 = new Cords(740,670,770,670,740,645,770,645);

        cordsArrayList.add(cord1);
        cordsArrayList.add(cord2);
        cordsArrayList.add(cord3);
        cordsArrayList.add(cord4);
        cordsArrayList.add(cord5);
        cordsArrayList.add(cord6);
        cordsArrayList.add(cord7);
        cordsArrayList.add(cord8);
        cordsArrayList.add(cord9);
        cordsArrayList.add(cord10);
        cordsArrayList.add(cord11);
        cordsArrayList.add(cord12);
        cordsArrayList.add(cord13);
        cordsArrayList.add(cord14);
        cordsArrayList.add(cord15);
        cordsArrayList.add(cord16);
        cordsArrayList.add(cord17);
        cordsArrayList.add(cord18);
        cordsArrayList.add(cord19);
        cordsArrayList.add(cord20);
        cordsArrayList.add(cord21);
        cordsArrayList.add(cord22);
        cordsArrayList.add(cord23);
        cordsArrayList.add(cord24);
        cordsArrayList.add(cord25);
        cordsArrayList.add(cord26);
        cordsArrayList.add(cord27);
        cordsArrayList.add(cord28);
        cordsArrayList.add(cord29);
        cordsArrayList.add(cord30);
        cordsArrayList.add(cord31);
        cordsArrayList.add(cord32);
        cordsArrayList.add(cord33);
        cordsArrayList.add(cord34);
        cordsArrayList.add(cord35);
        cordsArrayList.add(cord36);
        cordsArrayList.add(cord37);
        cordsArrayList.add(cord38);
        cordsArrayList.add(cord39);
        cordsArrayList.add(cord40);
    }


    public int getCorXProperty(int propertyId,int playerId){
        Cords playerCords = cordsArrayList.get(propertyId-1);
        if(playerId == 1){
            return playerCords.getCorXp1();
        }
        else if(playerId == 2){
            return playerCords.getCorXp2();
        }
        else if(playerId == 3){
            return playerCords.getCorXp3();
        }
        else if(playerId == 4){
            return playerCords.getCorXp4();
        }
        else return 0;
    }

    public int getCorYProperty(int propertyId,int playerId){
        Cords playerCords = cordsArrayList.get(propertyId-1);
        if(playerId == 1){
            return playerCords.getCorYp1();
        }
        else if(playerId == 2){
            return playerCords.getCorYp2();
        }
        else if(playerId == 3){
            return playerCords.getCorYp3();
        }
        else if(playerId == 4){
            return playerCords.getCorYp4();
        }
        else return 0;
    }
}
