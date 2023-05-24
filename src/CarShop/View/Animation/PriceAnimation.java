package CarShop.View.Animation;
import CarShop.Constants.StoreCarCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.ArrayList;
public class PriceAnimation {
    private StoreCarCollections car;
    private ArrayList<Node> generalPrice;
    public PriceAnimation() {
    }

    public void animatePrice(StoreCarCollections car, ArrayList<Node> generalPrice) {
        this.car=car;
        this.generalPrice=generalPrice;
        start();
    }
    private void start(){
        for (int i = 0; i < generalPrice.size(); i++) {
            Label lbl1=(Label)generalPrice.get(i);
            Label lbl2=(Label)car.getPrice().get(i);
            lbl1.setText(lbl2.getText());
        }
    }

}
