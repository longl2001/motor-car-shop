package CarShop.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RankedCar {
    private int id;
    private String color;
    private HBox rank;
    private Label name;
    private Label price;
    private Label rankVal;
    public RankedCar(HBox rank, Label name,Label price,Label rankVal,int id){
        this.rank=rank;
        this.name=name;
        this.price=price;
        this.id=id;
        this.rankVal=rankVal;
    }
    public Label getRankVal(){
        return rankVal;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Label getPrice() {
        return price;
    }

    public void setPrice(Label price) {
        this.price = price;
    }

    public HBox getRank() {
        return rank;
    }

    public Label getName() {
        return name;
    }
}
