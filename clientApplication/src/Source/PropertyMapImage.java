package Source;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PropertyMapImage {
    private Image property = new Image("images/GridOwnersMap/property-blank.png");
    private ImageView propertyView = new ImageView(property);

    public PropertyMapImage(){
        propertyView.setFitHeight(30);
        propertyView.setFitWidth(60);
    }

    public ImageView getPropertyView() {
        return propertyView;
    }

    public void setPropertyView(Image image) {
        this.propertyView.setImage(image);
    }
}
