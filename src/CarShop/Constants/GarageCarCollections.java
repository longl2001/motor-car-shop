package CarShop.Constants;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.EnumSet;

public enum GarageCarCollections {
    TESLA_MODEL_Y("Tesla","Model Y",1 ,FXCollections.observableArrayList(70, 56, 55, 50),92,186474,"#750d00",null,null,null),
    TESLA_MODEL_S("Tesla","Model S",51 ,FXCollections.observableArrayList(82, 72, 78, 40),98,23000,"#750d00",null,null,null),
    TESLA_MODEL_X("Tesla","Model X",101 ,FXCollections.observableArrayList(92, 56, 97, 80),77,23000,"#750d00",null,null,null),

    BUGATTI_VEYRON("Bugatti","Veyron",10, FXCollections.observableArrayList(100,45,55,67),86,232493,"#756500",null,null,null),
    BUGATTI_CHIRON("Bugatti","Chivon",60, FXCollections.observableArrayList(90,55,89,99),60,386474,"#006b72",null,null,null),
    BUGATTI_MISTRAL("Bugatti","Mistral",110, FXCollections.observableArrayList(90,55,89,99),60,386474,"#006b72",null,null,null),

    ROLLS_ROYCE_PHANTOM("Rolls Royce","Phantom",20, FXCollections.observableArrayList(88,56,77,33),76,136274,"#0c246e",null,null,null),
    ROLLS_ROYCE_GHOST("Rolls Royce","Ghost",70, FXCollections.observableArrayList(88,56,77,33),89,136274,"#0c246e",null,null,null),
    ROLLS_ROYCE_CULINAN("Rolls Royce","Culinan",120, FXCollections.observableArrayList(88,56,77,33),90,136274,"#0c246e",null,null,null),


    GENERAL(null,null,0,null,0,0,"",null,null,null,null,null,null);
    private String brand;
    private String model;
    private final String color;

    private ImageView frame;
    private ObservableList<Integer> carInfo;
    private HBox carContainer;
    private ObservableList<HBox> list;
    private ObservableList<Label> list2;
    private ObservableList<Label> titleGeneral;
    private String title;
    private int rating;
    private final int id;
    private ImageView frame2;
    private double price;
    private GarageCarCollections(String brand,String model, int id,ObservableList<Integer> carInfo,int rating,double price,String color,
                                 ObservableList<HBox> list,HBox carContainer,ImageView frame2){
        this.brand=brand;
        this.model=model;
        this.id=id;
        this.carInfo=carInfo;
        this.list=list;
        this.carContainer=carContainer;
        this.rating=rating;
        this.frame2=frame2;
        this.price=price;
        this.color=color;
    }
    private GarageCarCollections(String brand,String model,int id, ObservableList<Integer> carInfo,int rating,double price,String color,ObservableList<HBox> list,
             HBox carContainer,ImageView frame,ObservableList<Label> list2,ObservableList<Label> titleGeneral,ImageView frame2){
        this(brand,model,id,carInfo,rating,price,color,list,carContainer,frame2);
        this.frame=frame;
        this.list2=list2;
        this.titleGeneral=titleGeneral;
    }

    public ObservableList<Label> getTitleGeneral() { return titleGeneral; }
    public void setTitleGeneral(ObservableList<Label> titleGeneral) { this.titleGeneral = titleGeneral; }
    public String getBrand(){return brand;}
    public String getModel(){return model;}
    public String getTitle() {return title; }
    public void setFrame(ImageView frame){
        this.frame=frame;
    }
    public ImageView getFrame(){
        return frame;
    }
    public int getRating(){return rating;}
    public ObservableList<Integer> getCarInfo(){
        return carInfo;
    }
    public ObservableList<HBox> getList(){
        return list;
    }
    public HBox getCarContainer(){
        return carContainer;
    }
    public void setCarContainer(HBox carContainer){
        this.carContainer=carContainer;
    }
    public void setPrgressBars(ObservableList<HBox> list){
        this.list=list;
    }
    public void setLabel(ObservableList<Label> list2){
        this.list2=list2;
    }
    public ObservableList<Label> getLabel(){
        return list2;
    }
    public int getId(){
        return id;
    }
    public void setFrame2(ImageView frame2){this.frame2=frame2;}
    public ImageView getFrame2(){return frame2;}
    public double getPrice(){return price; }
    public void setPrice(double price){this.price=price;}
    public String getColor(){return color;}
    public static EnumSet<GarageCarCollections> getSubset(){
        EnumSet<GarageCarCollections> allCars = EnumSet.allOf(GarageCarCollections.class);
        GarageCarCollections lastCar = allCars.toArray(new GarageCarCollections[0])[allCars.size() - 1];
        return EnumSet.complementOf(EnumSet.of(lastCar));
    }

    public static GarageCarCollections getUpgradedCar(int id){
        for(GarageCarCollections car: GarageCarCollections.values()){
            if(car.getId()==(id+50)){
                return car;
            }
        }
        return null;
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

    public static String getUpgradedNormal(int id){
        for(CarPath car:CarPath.values()){
            if(car.getId()==(id+50)) return car.getNormalImg();
        }
        return null;
    }
    public static String getUpgradedVertical(int id){
        for(CarPath car:CarPath.values()){
            if(car.getId()==(id+50)) return car.getVerticalImg();
        }
        return null;
    }
    public static String getUpgradedHorizontal(int id){
        for(CarPath car:CarPath.values()){
            if(car.getId()==(id+50)) return car.getHorizontalImg();
        }
        return null;
    }
}
