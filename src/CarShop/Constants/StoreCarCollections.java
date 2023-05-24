package CarShop.Constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public enum StoreCarCollections {
    TESLA_MODEL_Y(1,"tesla","Model Y","#750d00",233333,null,null,null,null,null),
    TESLA_MODEL_S(51,"tesla","Model S","#750d00",186474,null,null,null,null,null),
    TESLA_MODEL_X(101,"tesla","Model X","#750d00",155555,null,null,null,null,null),

    BUGATTI_VEYRON(10,"bugatti","Veyron","#750d00",132560,null,null,null,null,null),
    BUGATTI_CHIRON(60,"bugatti","Chiron","#750d00",233633,null,null,null,null,null),
    BUGATTI_MISTRAL(110,"bugatti","Mistral","#750d00",200000,null,null,null,null,null),

    ROLLS_ROYCE_PHANTOM(20,"rolls royce","Phantom","#756500",232493,null,null,null,null,null),
    ROLLS_ROYCE_GHOST(70,"rolls royce","Ghost","#006b72",386474,null,null,null,null,null),
    ROLLS_ROYCE_CULINAN(120,"rolls royce","Culinan","#0c246e",136274,null,null,null,null,null);

    private  ImageView img;
    private  VBox carBackground;
    private VBox carContainer;
    private Button buyBtn;
    private final int id;
    private ArrayList<Node> price;
    private String brand;
    private String model;
    private double priceVal;
    private String color;
    private static ObservableList<StoreCarCollections> carList= FXCollections.observableArrayList();
    private StoreCarCollections(int id,String brand,String model,String color,double priceVal,ImageView img, ArrayList<Node> price, VBox carBackground, VBox carContainer, Button buyBtn){
        this.img=img;
        this.id=id;
        this.carBackground=carBackground;
        this.carContainer=carContainer;
        this.buyBtn=buyBtn;
        this.price=price;
        this.priceVal=priceVal;
        this.color=color;
        this.brand=brand;
        this.model=model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getId(){
        return id;
    }
    public String getColor(){return color;}
    public ImageView getImg(){
        return img;
    }
    public VBox getCarBackground(){
        return carBackground;
    }
    public VBox getCarContainer(){return carContainer;}
    public void setImg(ImageView img){
        this.img=img;
    }
    public Button getBuyBtn() {
        return buyBtn;
    }
    public void setBuyBtn(Button buyBtn) {
        this.buyBtn = buyBtn;
    }
    public void setCarBackground(VBox carBackground){
        this.carBackground=carBackground;
    }
    public void setCarContainer(VBox carContainer){
        this.carContainer=carContainer;
    }
    public ArrayList<Node> getPrice() {return price; }
    public double getPriceVal(){
        return priceVal;
    }
    public void setPriceVal(double priceVal){this.priceVal=priceVal;}
    public void setPrice(ArrayList<Node> price) { this.price = price; }

    public static void createCar(StoreCarCollections car, ImageView img, ArrayList<Node> price, VBox carBackground, VBox carContainer, Button buyBtn){
        car.setImg(img);
        car.setCarBackground(carBackground);
        car.setCarContainer(carContainer);
        car.setBuyBtn(buyBtn);
        car.setPrice(price);
        carList.add(car);
    }
    public static ObservableList<StoreCarCollections> getCarList(){
        return carList;
    }
    public static String getNormalImg(int id){
        for(CarPath car:CarPath.values()){
            if(car.getId()==id) return car.getNormalImg();
        }
        return null;
    }
    public static String getVerticalImg(int id){
        for(CarPath car:CarPath.values()){
            if(car.getId()==id) return car.getVerticalImg();
        }
        return null;
    }
    public static String getHorizontalImg(int id){
        for(CarPath car:CarPath.values()){
            if(car.getId()==id) return car.getHorizontalImg();
        }
        return null;
    }
    public static StoreCarCollections getCar(int id){
        for (StoreCarCollections car:StoreCarCollections.values()){
            if(car.getId()==id){
                return car;
            }
        }
        return null;
    }


}
