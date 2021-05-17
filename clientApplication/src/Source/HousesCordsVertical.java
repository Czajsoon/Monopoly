package Source;

import java.util.ArrayList;

public class HousesCordsVertical {
    private ArrayList<HousesCord> cordsList = new ArrayList<>();

    public HousesCordsVertical(){
        HousesCord Spain1 = new HousesCord(83,638);
        HousesCord Spain2 = new HousesCord(83,505);
        HousesCord Spain3 = new HousesCord(83,438);

        HousesCord England1 = new HousesCord(83,305);
        HousesCord England2 = new HousesCord(83,172);
        HousesCord England3 = new HousesCord(83,106);

        HousesCord RFN1 = new HousesCord(707,305);
        HousesCord RFN2 = new HousesCord(707,172);
        HousesCord RFN3 = new HousesCord(707,106);

        HousesCord Austria1 = new HousesCord(707,505);
        HousesCord Austria2 = new HousesCord(707,638);

        cordsList.add(Spain1);
        cordsList.add(Spain2);
        cordsList.add(Spain3);
        cordsList.add(England1);
        cordsList.add(England2);
        cordsList.add(England3);
        cordsList.add(RFN1);
        cordsList.add(RFN2);
        cordsList.add(RFN3);
        cordsList.add(Austria1);
        cordsList.add(Austria2);
    }

    public HousesCord getHousesCord(int index){
        return cordsList.get(index);
    }

    public ArrayList<HousesCord> getCordsList() {
        return cordsList;
    }
}
