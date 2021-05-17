package Source;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HousesOnPropertiesHorizontal {
    private ArrayList<HousesOnPropertyHorizontal> propetyHousesList = new ArrayList<>();
    private HousesCordsHorizontal housesCordsHorizontal = new HousesCordsHorizontal();
    private ArrayList<Properties> propertiesArrayList;

    public HousesOnPropertiesHorizontal(Pane mapPanel, ArrayList<Properties> propertiesList){
        this.propertiesArrayList = propertiesList;
        for(int i = 0; i< housesCordsHorizontal.getCordsList().size(); i++){
            HousesOnPropertyHorizontal propertyHorizontal = new HousesOnPropertyHorizontal(mapPanel, housesCordsHorizontal.getHousesCord(i).getX(), housesCordsHorizontal.getHousesCord(i).getY());
            propetyHousesList.add(propertyHorizontal);
        }
    }

    public void setNoVisibleHousesHorizontal(){
        for(int i = 0; i< propetyHousesList.size(); i++){
            propetyHousesList.get(i).getH1().setVisible(false);
            propetyHousesList.get(i).getH2().setVisible(false);
            propetyHousesList.get(i).getH3().setVisible(false);
            propetyHousesList.get(i).getH4().setVisible(false);
        }
    }

    public void updateHousesOnMap(ArrayList<Properties> propertiesArrayList) {
        int i = 0;
        for (Properties property : propertiesArrayList) {
            if (property.getCountryName().equals("Grecja") || property.getCountryName().equals("Włochy")
                    || property.getCountryName().equals("Benelux") || property.getCountryName().equals("Szwecja")) {
                System.out.println(property.getPropertyLVL());
                switch (property.getActualLvlProperty()) {
                    case 0: {
                        propetyHousesList.get(i).getH1().setVisible(false);
                        propetyHousesList.get(i).getH2().setVisible(false);
                        propetyHousesList.get(i).getH3().setVisible(false);
                        propetyHousesList.get(i).getH4().setVisible(false);
                        i++;
                        break;
                    }
                    case 1: {
                        propetyHousesList.get(i).getH1().setVisible(true);
                        propetyHousesList.get(i).getH2().setVisible(false);
                        propetyHousesList.get(i).getH3().setVisible(false);
                        propetyHousesList.get(i).getH4().setVisible(false);
                        i++;
                        break;
                    }
                    case 2: {
                        propetyHousesList.get(i).getH1().setVisible(true);
                        propetyHousesList.get(i).getH2().setVisible(true);
                        propetyHousesList.get(i).getH3().setVisible(false);
                        propetyHousesList.get(i).getH4().setVisible(false);
                        i++;
                        break;
                    }
                    case 3: {
                        propetyHousesList.get(i).getH1().setVisible(true);
                        propetyHousesList.get(i).getH2().setVisible(true);
                        propetyHousesList.get(i).getH3().setVisible(true);
                        propetyHousesList.get(i).getH4().setVisible(false);
                        i++;
                        break;
                    }
                    case 4: {
                        propetyHousesList.get(i).getH1().setVisible(true);
                        propetyHousesList.get(i).getH2().setVisible(true);
                        propetyHousesList.get(i).getH3().setVisible(true);
                        propetyHousesList.get(i).getH4().setVisible(true);
                        i++;
                        break;
                    }
                }
            }
        }
    }

}
