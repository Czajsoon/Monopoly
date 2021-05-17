package Source;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PropertyMapImages {
    private Label nickname = new Label();
    private ArrayList<PropertyMapImage> imagesList = new ArrayList<>();
    private PropertyMapImage imageProperty;
    private HBox box1 = new HBox();
    private HBox box2 = new HBox();
    private HBox box3 = new HBox();
    private HBox box4 = new HBox();
    private HBox box5 = new HBox();
    private VBox mainBox = new VBox();

    public PropertyMapImages(String nickname){
        this.nickname.setText("Własności gracza o nazwie: " + nickname);
        initializeImagesMap();
    }

    private void initializeImagesMap(){
        for(int i =0;i<28;i++){
            imageProperty = new PropertyMapImage();
            imagesList.add(imageProperty);
        }
        for(int i=0;i<6;i++){
            box1.getChildren().add(imagesList.get(i).getPropertyView());
        }
        for(int i=6;i<12;i++){
            box2.getChildren().add(imagesList.get(i).getPropertyView());
        }
        for(int i=12;i<18;i++){
            box3.getChildren().add(imagesList.get(i).getPropertyView());
        }
        for(int i=18;i<24;i++){
            box4.getChildren().add(imagesList.get(i).getPropertyView());
        }
        for(int i=24;i<28;i++){
            box5.getChildren().add(imagesList.get(i).getPropertyView());
        }
        mainBox.getChildren().add(nickname);
        mainBox.getChildren().add(box1);
        mainBox.getChildren().add(box2);
        mainBox.getChildren().add(box3);
        mainBox.getChildren().add(box4);
        mainBox.getChildren().add(box5);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(2);
        box1.setAlignment(Pos.CENTER);
        box1.setSpacing(2);
        box2.setAlignment(Pos.CENTER);
        box2.setSpacing(2);
        box3.setAlignment(Pos.CENTER);
        box3.setSpacing(2);
        box4.setAlignment(Pos.CENTER);
        box4.setSpacing(2);
        box5.setAlignment(Pos.CENTER);
        box5.setSpacing(2);
    }

    public VBox getMainBox() {
        return mainBox;
    }

    public void updatePropertiesMap(ArrayList<Properties> propertiesList, Player player){
        int i = 0;
        String pathImage = "images/GridOwnersMap/property-";
        for(Properties property : propertiesList){
            switch(property.getIDproperty()){
                case 2:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
                case 4:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
                case 6:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
                case 7:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
                case 9:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
                case 10:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
                case 12:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
                case 13:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 14:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 15:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 16:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 17:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 19:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 20:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 22:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 24:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 25:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 26:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 27:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 28:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 29:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 30:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 32:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 33:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 35:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 36:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 38:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }case 40:{
                    if(property.getOwnerID() == player.getPlayerNumber()){
                        makeChange(i,pathImage,property);
                    }
                    i++;
                    break;
                }
            }
        }

    }

    private void makeChange(int i,String pathImage,Properties property){
        Image propertyImage = new Image(pathImage + property.getIDproperty() + ".png");
        imagesList.get(i).setPropertyView(propertyImage);
    }

    public void BankrouptInfoUpdate(boolean isBanktoupt){
        if(isBanktoupt){
            mainBox.setVisible(false);
        }
    }
}
