package Source;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HousesOnPropertyVertical {
    private Image house = new Image("images/house.png");
    private ImageView h1 = new ImageView(house);
    private ImageView h2 = new ImageView(house);
    private ImageView h3 = new ImageView(house);
    private ImageView h4 = new ImageView(house);

    public HousesOnPropertyVertical(Pane mapPanel, int x, int y){
        VBox housesBox = new VBox();
        housesBox.setSpacing(5);
        housesBox.setLayoutX(x);
        housesBox.setLayoutY(y);
        housesBox.getChildren().add(h1);
        h1.setFitWidth(10);
        h1.setFitHeight(10);
        housesBox.getChildren().add(h2);
        h2.setFitWidth(10);
        h2.setFitHeight(10);
        housesBox.getChildren().add(h3);
        h3.setFitWidth(10);
        h3.setFitHeight(10);
        housesBox.getChildren().add(h4);
        h4.setFitWidth(10);
        h4.setFitHeight(10);
        mapPanel.getChildren().add(housesBox);
    }

    public ImageView getH1() {
        return h1;
    }

    public ImageView getH2() {
        return h2;
    }

    public ImageView getH3() {
        return h3;
    }

    public ImageView getH4() {
        return h4;
    }
}
