package CarShop.Control;

import CarShop.Constants.MenuOptions;
import CarShop.Model.Model;
import CarShop.Constants.StoreCarCollections;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;
public class storeController implements Initializable {
    @FXML
    private ScrollPane scroll;
    @FXML
    private HBox btnContainer1;
    @FXML
    private BorderPane borderLbl1;
    @FXML
    private VBox carBackground1,carBackground2,carBackground3,carBackground4;
    @FXML
    private VBox carContainer1, carContainer2, carContainer3,carContainer4;
    @FXML
    private ImageView imgView, img1,img2,img3,img4;
    @FXML
    private VBox carImg,blank,rightPane;
    @FXML
    private HBox right;
    @FXML
    private Label num1,num2,num3,num4,num5,num6;
    @FXML
    private Label num11,num21,num31,num41,num51,num61;
    @FXML
    private Label num12,num22,num32,num42,num52,num62;
    @FXML
    private Label num13,num23,num33,num43,num53,num63;
    @FXML
    private Label cnum1,cnum2,cnum3,cnum4,cnum5,cnum6;
    @FXML
    private Button buy1,buy2,buy3,buy4,cart,cancelBtn,confirmBtn;
    @FXML
    private Label brand1,brand2,brand3,brand4,model1,model2,model3,model4;
    @FXML
    private VBox carBack3;
    private StoreCarCollections carBought;
    @FXML
    private Button trendingBtn;
    @FXML
    private HBox growBox;
    @FXML
    private Label orderPrice,totalPrice,speed;
    private ArrayList<Node> generalPrice=new ArrayList<>();
    private ArrayList<Node> price1=new ArrayList<>();
    private ArrayList<Node> price2=new ArrayList<>();
    private ArrayList<Node> price3=new ArrayList<>();
    private ArrayList<Node> price4=new ArrayList<>();
    private ArrayList<Node> buyBtnArr=new ArrayList<>();
    private ObservableList<Label> brandList ;
    private ObservableList<Label> modelList ;
    private ObservableList<StoreCarCollections> carList ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPrice();
        createBuyBtnArr();
        addCars();
        setCarImg();
        addListener();


    }

    private void addCars(){
        StoreCarCollections.createCar(StoreCarCollections.TESLA_MODEL_Y,img1,price1,carBackground1,carContainer1,buy1);
        StoreCarCollections.createCar(StoreCarCollections.BUGATTI_VEYRON,img2,price2,carBackground2,carContainer2,buy2);
        StoreCarCollections.createCar(StoreCarCollections.ROLLS_ROYCE_PHANTOM,img3,price3,carBackground3,carContainer3,buy3);
        StoreCarCollections.createCar(StoreCarCollections.TESLA_MODEL_X,img4,price4,carBackground4,carContainer4,buy4);
    }
    private void addListener(){
        Model.getInstance().getStoreAnimation().setListener();
        Model.getInstance().getView().getSelection().addListener((observableVal,oldVal,newVal)->{
            if(newVal== MenuOptions.STORE){
                startAction();
            }
        });
    }
    private void setCarImg(){
        int i=0;
        brandList =FXCollections.observableArrayList(brand1,brand2,brand3,brand4);
        modelList =FXCollections.observableArrayList(model1,model2,model3,model4);
        for(StoreCarCollections car: StoreCarCollections.getCarList()){
                car.getImg().setImage(new Image(getClass().getResource(StoreCarCollections.getNormalImg(car.getId())).toString()));
                brandList.get(i).setText(car.getBrand().toUpperCase());
                modelList.get(i).setText(car.getModel());
                i++;
        }
    }

    /**
     * Start the animation and action events
     */
    private void startAction(){
        for(StoreCarCollections car: StoreCarCollections.values()){
            if(car.getCarContainer()!=null) {
                if (car.getCarContainer() == carContainer1) {
                    carClicked(car);
                }
                car.getCarContainer().setOnMouseClicked(e -> carClicked(car));
                car.getBuyBtn().setOnAction(e -> {
                    System.out.println("click");
                    Model.getInstance().getStoreAnimation().setVisible(right, rightPane);
                    Model.getInstance().getStoreAnimation().setPriceLabel(orderPrice, car.getPrice(), totalPrice);
                    Model.getInstance().getView().getStoreSelection().set(car);
                });
            }

            confirmBtn.setOnAction(e->{
                if(Model.getInstance().getAccount().getBalance() >= Model.getInstance().getView().getStoreSelection().get().getPriceVal()) {
                    StoreCarCollections obj= Model.getInstance().getView().getStoreSelection().get();
                    Model.getInstance().getCarPrice().addCar(obj);
                    Model.getInstance().getGarageList().addCar(obj.getId());
                    Model.getInstance().getAccount().subtractBalance(Model.getInstance().getCarPrice().getEachTaxPrice());
                    Model.getInstance().getAccount().addPortfolioBal(obj.getPriceVal());


                }
            });
            cancelBtn.setOnMouseClicked(e->Model.getInstance().getStoreAnimation().setInVisible(right,rightPane));
        }
    }

    private void carClicked(StoreCarCollections carName){
            Model.getInstance().getView().getStoreSelection().set(carName);
            Model.getInstance().getStoreAnimation().animateVCar(carName,imgView);

            Model.getInstance().getPriceAnimation().animatePrice(carName, generalPrice);
    }
    private void createBuyBtnArr(){
        buyBtnArr=Model.getInstance().getStoreAnimation().createArr(buy1,buy2,buy3,buy4);
    }
    private void createPrice(){
        price1= Model.getInstance().getStoreAnimation().createArr(num1,num2,num3,num4,num5,num6);
        price2= Model.getInstance().getStoreAnimation().createArr(num11,num21,num31,num41,num51,num61);
        price3= Model.getInstance().getStoreAnimation().createArr(num12,num22,num32,num42,num52,num62);
        price4= Model.getInstance().getStoreAnimation().createArr(num13,num23,num33,num43,num53,num63);
        generalPrice= Model.getInstance().getStoreAnimation().createArr(cnum1,cnum2,cnum3,cnum4,cnum5,cnum6);
    }
    private void act(){
        int startValue = 184523;
        int endValue = 184710;
        int durationInMilis = 2;
        int frameCount = endValue - startValue + 1;
        int frameDuration = 2000 / frameCount;

        Timeline timeline = new Timeline();
        for (int j = startValue; j <= endValue; j++) {
            int frameIndex = j - startValue;
            String frameText = String.format("%06d", j);
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(frameIndex * frameDuration),
                    e -> speed.setText(frameText)
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
    }
}
