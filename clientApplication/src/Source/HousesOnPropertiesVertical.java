package Source;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HousesOnPropertiesVertical {
    private ArrayList<HousesOnPropertyVertical> propetyHousesList = new ArrayList<>();
    private HousesCordsVertical housesCordsVertical = new HousesCordsVertical();

    public HousesOnPropertiesVertical(Pane mapPanel){
        for(int i = 0; i< housesCordsVertical.getCordsList().size(); i++){
            HousesOnPropertyVertical propertyVertical = new HousesOnPropertyVertical(mapPanel, housesCordsVertical.getHousesCord(i).getX(), housesCordsVertical.getHousesCord(i).getY());
            propetyHousesList.add(propertyVertical);
        }
    }

    public void setNoVisibleHousesVertical(){
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
             if (property.getCountryName().equals("Hiszpania") || property.getCountryName().equals("Anglia")
              || property.getCountryName().equals("RFN") || property.getCountryName().equals("Austria")) {
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
