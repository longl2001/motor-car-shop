package CarShop.View.Animation;

import CarShop.Constants.CarPath;
import CarShop.Constants.GarageCarCollections;
import CarShop.Model.Model;
import CarShop.Constants.StoreCarCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;

public class StoreAnimation {
    public StoreAnimation(){
    }

    /**
     * car holder animation
     */
    public void setListener() {
        Model.getInstance().getView().getStoreSelection().addListener((observableValue, oldVal, newVal) -> {
            if (oldVal != null) {
                reverseBackground(oldVal.getCarBackground());
                AnimationType.rescale(oldVal.getImg());
            }
            setBackground(newVal.getCarBackground());
            Model.getInstance().getCarsAnimation().animateStoreHCar(newVal.getImg());
        });
        Model.getInstance().getView().getStoreSelection().set(StoreCarCollections.TESLA_MODEL_Y);
    }

    /**
     * animate the vertical car
     */
    public void animateVCar(StoreCarCollections carName, ImageView img){

        img.setImage(new Image(getClass().getResource(GarageCarCollections.getVerticalImg(carName.getId())).toString()));
        AnimationType.rescale(img);

        Model.getInstance().getTransition().moveStoreCar(img);
    }

    /**
     * set and reverse background color
     */
    private void setBackground(VBox box){
            box.setStyle("-fx-background-color:transparent;");
    }
    private void reverseBackground(VBox box){
            box.setStyle("-fx-background-color:rgba(0,0,0,.05);");
    }

    /**
     * set text appeared and disappeared animation
     */
    public void setVisible(HBox right, VBox rightPane){
        right.setVisible(true);
        right.setStyle("-fx-background-color:rgba(0,0,0,0.3);");
        AnimationType.moveX(rightPane,.3,300,0);
    }
    public void setInVisible(HBox right, VBox rightPane){
        AnimationType.moveX(rightPane,.3,0,446);
        right.setVisible(false);
    }
    public ArrayList<Node> createArr(Node... params){
        ArrayList<Node> list  = new ArrayList<>();
        list.addAll(Arrays.asList(params));
        return list;
    }
    public void setPriceLabel(Label originalPrice, ArrayList<Node> arr,Label totalPrice){
         String price= "";
        for (int i = arr.size()-1; i >=0; i--) {
            Label lbl = (Label)arr.get(i);
            price+=lbl.getText();
        }
        double totalFeePrice = Double.parseDouble(price)*1.013 ;
        totalPrice.setText("$"+ String.format("%,.2f",totalFeePrice));
        originalPrice.setText("$"+String.format("%,.2f",Double.parseDouble(price)));
    }

}
