package Source;

import java.util.ArrayList;

public class HousesCordsHorizontal {
    private ArrayList<HousesCord> cordsList = new ArrayList<>();

    public HousesCordsHorizontal(){
        HousesCord Grece1 = new HousesCord(640,708);
        HousesCord Grece2 = new HousesCord(506,708);

        HousesCord Italy1 = new HousesCord(305,708);
        HousesCord Italy2 = new HousesCord(171,708);
        HousesCord Italy3 = new HousesCord(106,708);

        HousesCord Benelux1 = new HousesCord(106,83);
        HousesCord Benelux2 = new HousesCord(238,83);
        HousesCord Benelux3 = new HousesCord(305,83);

        HousesCord Sweden1 = new HousesCord(439,83);
        HousesCord Sweden2 = new HousesCord(506,83);
        HousesCord Sweden3 = new HousesCord(640,83);

        cordsList.add(Grece1);
        cordsList.add(Grece2);

        cordsList.add(Italy1);
        cordsList.add(Italy2);
        cordsList.add(Italy3);

        cordsList.add(Benelux1);
        cordsList.add(Benelux2);
        cordsList.add(Benelux3);

        cordsList.add(Sweden1);
        cordsList.add(Sweden2);
        cordsList.add(Sweden3);
    }

    public HousesCord getHousesCord(int index){
        return cordsList.get(index);
    }

    public ArrayList<HousesCord> getCordsList() {
        return cordsList;
    }
}
