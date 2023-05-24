package CarShop.Control;

import CarShop.Constants.MenuOptions;
import CarShop.View.Animation.AnimationType;
import CarShop.View.Circle_progress.ProgressThread;
import CarShop.View.Circle_progress.RingProgressIndicator;
import CarShop.Model.Model;
import CarShop.Constants.CarPath;
import CarShop.Constants.GarageCarCollections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


import java.net.URL;
import java.util.ResourceBundle;

public class GarageController implements Initializable {
    @FXML
    private ImageView img1,img2,img3,img4;
    @FXML
    private HBox rank;
    @FXML
    private StackPane stage2,stage3;
    @FXML
    private ImageView arrowImg,upgradedCar;
    @FXML
    private HBox holder;
    @FXML
    private StackPane pane;
    @FXML
    private Label speedLbl,fuelLbl,powerLbl,tempLbl,brandTitle,modelTitle;
    @FXML
    private ImageView car;
    @FXML
    private HBox car1Hbox, car2Hbox,car3Hbox,car4Hbox;
    @FXML
    private HBox bar1,bar2,bar3,bar4;
    @FXML
    private Button upgradeBtn,backBtn;
    @FXML
    private VBox leftPane, rightPane;
    private ProgressThread pt;
    //stage1
    @FXML
    private HBox emptyLbl;
    @FXML
    private Button upBtn,downBtn;
    @FXML
    private VBox carListHolder,listClipper;
    //stage 2
    @FXML
    private Button upgradeBtn2,cancelBtn2;
    @FXML
    private Label priceLbl,rankFirst,rankSecond;
    @FXML
    private VBox infoLeft,infoRight;
    //stage 3
    @FXML
    private ImageView imgHolder;
    @FXML
    private VBox linesContainer,linesClipper, boxContainer;
    @FXML
    private Button activateBtn,finishBtn;
    @FXML
    private HBox upperBox,lowerBox,blackCircle,pinkCircle,layer;
    @FXML
    private ImageView tatImg;
    private ObservableList<HBox> containerList;
    private ObservableList<ImageView> imgList;
    private String currentColor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        addListener();
    }
    /**
     * Create elements from fxml nodes
     */
    private void setData(){
        holder.setVisible(false);
        emptyLbl.setVisible(true);
        clipContainer();
        setImgFrame();
        setLbl();
        setBar();
        setGeneral();
        setContainer();
    }

    /**
     * if garage menu is chosen, execute the three stages in this place
     */
    private void addListener(){
        imgList = FXCollections.observableArrayList();
        imgList.addAll(img1,img2,img3,img4);
        Model.getInstance().getView().getSelection().addListener((observable,oldVal,newVal)->{
            if(newVal==MenuOptions.GARAGE && Model.getInstance().getGarageList().getList().size()>=1){
                    beginThreeStages();
                }
        });
    }
    private void beginThreeStages(){
        startStage1();
    }
    private void startStage1(){
        setElements();
        for (GarageCarCollections obj: GarageCarCollections.getSubset()){
            if(obj.getCarContainer()!=null) {
                startStage2(obj);
                containerClicked(obj);
                animateCircular(obj);
                upgradeClicked(obj);
                obj.getCarContainer().setOnMouseClicked(e -> {
                    Model.getInstance().getGarageAnimation().setBarsColor(obj);
                    containerClicked(obj);
                    animateCircular(obj);
                    upgradeClicked(obj);
                    startStage2(obj);
                });
            }
        }
        directionIconClicked();
        cancelClicked();
        backBtnClicked();
        upgrade2Clicked();
    }

    /**
     * Set the container and images of the bought car into the garage car enum
     */
    private void setElements(){
        emptyLbl.setVisible(false);
        holder.setVisible(true);
        for (int k = 0; k < Model.getInstance().getGarageList().getList().size(); k++) {
            if(k>=containerList.size()){
                break;
            }
            int id= Model.getInstance().getGarageList().getList().get(k).getId();
            for (GarageCarCollections car:GarageCarCollections.getSubset()){
                if(car.getId()==id){
                    Model.getInstance().getGarageAnimation().setUpgradeColor(upgradeBtn,car);
                    Model.getInstance().getGarageAnimation().setBarsColor(car);
                    containerList.get(k).setVisible(true);
                    car.setCarContainer(containerList.get(k));
                    car.setFrame2(imgList.get(k));
                    break;
                }
            }
        }
    }
    /**
     * If the car's container is clicked, activate the animation and move to the next stage
     * @param car
     */
    private void containerClicked(GarageCarCollections car){
        car.getCarContainer().setDisable(true);
        Model.getInstance().getGarageAnimation().setHCar(car);
        Model.getInstance().getGarageAnimation().moveCar(car);
        Model.getInstance().getGarageAnimation().setText(car);
        Model.getInstance().getGarageAnimation().resetBars(car);
        Model.getInstance().getGarageAnimation().setTitle(car);
        Model.getInstance().getGarageAnimation().animateTitle();
        Model.getInstance().getGarageAnimation().animateAttributes(car);
        Model.getInstance().getGarageAnimation().animateProgress(car, () -> {
            car.getCarContainer().setDisable(false);
        });
    }

    private void setContainer(){
        containerList = FXCollections.observableArrayList();
        containerList.addAll(car1Hbox,car2Hbox,car3Hbox,car4Hbox);
        for (HBox container: containerList){
            container.setVisible(false);
        }
    }
    private void directionIconClicked(){
        upBtn.setOnAction(e->{
            double y=carListHolder.getTranslateY();
            y+=24;
            if(y>0) y=0;
            carListHolder.setTranslateY(y);
        });
        downBtn.setOnAction(e->{
            double y=carListHolder.getTranslateY();
            y-=24;

            carListHolder.setTranslateY(y);
        });
    }

    private void startstage3(GarageCarCollections obj){
        finishBtn.setOnAction(e->{
            int index= Model.getInstance().getGarageList().getList().indexOf(obj);
            Model.getInstance().getGarageList().getList().set(index,GarageCarCollections.getUpgradedCar(obj.getId()));
            beginThreeStages();
            AnimationType.setVisible(false,finishBtn,stage3,stage2);
            AnimationType.setVisible(true,car,upgradeBtn2);
            car.setOpacity(1);
            car.setScaleX(1);
            AnimationType.setOpacity(0,arrowImg,upgradedCar,infoRight,infoLeft,rankFirst,rankSecond,upgradeBtn2);
            AnimationType.moveX(car,.8,276,0);
            Model.getInstance().getGarageAnimation().back(leftPane,rightPane,backBtn);
        });
    }
    private void startStage2(GarageCarCollections obj){
        int id=obj.getId();
        startstage3(obj);
        activateBtn.setOnAction(e->{
            stage3.setVisible(true);
            car.setVisible(false);
            Model.getInstance().getGarageAnimation().setImg(imgHolder,GarageCarCollections.getNormalImg(obj.getId()));
            Model.getInstance().getGarageAnimation().setImg(car, GarageCarCollections.getUpgradedNormal(obj.getId()));
            activateBtn.setVisible(false);
            AnimationType.fade(activateBtn,.5,1,0);
            AnimationType.fade(cancelBtn2,.5,1,0);
            AnimationType.moveY(priceLbl,.8,0,-48);
            AnimationType.flip(priceLbl,.8,0,180,()->{
                AnimationType.flip(priceLbl,.3,180,0);
                priceLbl.setScaleX(1);
                Model.getInstance().getGarageAnimation().upgradeAnimation(id,linesContainer,imgHolder,pinkCircle,blackCircle,boxContainer,
                        upperBox,lowerBox,layer,tatImg,()->{
                            finishBtn.setVisible(true);
                        });
                priceLbl.setVisible(false);
            });
        });
    }

    private void animateCircular(GarageCarCollections car){
        rank.setVisible(true);
        RingProgressIndicator ringProgressIndicator = new RingProgressIndicator();
        ringProgressIndicator.setRingWidth(55);
        ringProgressIndicator.makeIndeterminate();
        pane.getChildren().add(ringProgressIndicator);
        pt = new ProgressThread(ringProgressIndicator,car);
        pt.start();
    }

    private void clipContainer(){
        Rectangle clipRect = new Rectangle(0, 0, holder.getPrefWidth(), holder.getPrefHeight());
        holder.setClip(clipRect);
        Rectangle clipRect2 = new Rectangle(0,0,linesClipper.getPrefWidth(),linesClipper.getPrefHeight());
        linesClipper.setClip(clipRect2);
        Rectangle clipRect3 = new Rectangle(0,0,306,704);
        listClipper.setClip(clipRect3);

    }
    private void setGeneral(){
        GarageCarCollections.GENERAL.setFrame(car);
        ObservableList<Label> titleList = FXCollections.observableArrayList();
        titleList.addAll(brandTitle,modelTitle);
        GarageCarCollections.GENERAL.setTitleGeneral(titleList);
    }
    private void setBar(){
        ObservableList<HBox> listBar = FXCollections.observableArrayList();
        listBar.addAll(bar1,bar2,bar3,bar4);
        for (GarageCarCollections car:GarageCarCollections.getSubset()){
            car.setPrgressBars(listBar);
        }
    }
    private void setLbl(){
        ObservableList<Label> listLbl = FXCollections.observableArrayList();
        listLbl.addAll(speedLbl,fuelLbl,powerLbl,tempLbl);
        GarageCarCollections.GENERAL.setLabel(listLbl);
    }
    private void setImgFrame(){
        CarPath.FRAME.setFrame(car,imgHolder);
    }
    private void cancelClicked(){
        cancelBtn2.setOnAction(e->{
            upgradeBtn2.setDisable(false);
            upgradeBtn2.setVisible(true);
            cancelBtn2.setVisible(false);
            activateBtn.setVisible(false);
            backBtn.setVisible(true);
            AnimationType.fade(priceLbl,.8,1,0);
            AnimationType.moveY(priceLbl,.8,0,24);
            AnimationType.fade(arrowImg,.8,0,1);
            AnimationType.fade(infoRight,.5,0,1);
            AnimationType.fade(infoLeft,.5,0,1);
            AnimationType.moveX(car,.8,276,0);
            AnimationType.moveX(upgradedCar,.8,-276,0);
            AnimationType.fade(upgradedCar,.4,0,1);
            AnimationType.fade(rankFirst,.5,0,1);
            AnimationType.fade(rankSecond,.5,0,1);
            AnimationType.moveY(rankFirst,.5,-16,0);
            AnimationType.moveY(rankSecond,.5,-16,0);
            AnimationType.moveY(infoLeft,.5,-16,0);
            AnimationType.moveY(infoRight,.5,-16,0);
        });
    }
    private void upgrade2Clicked(){
        upgradeBtn2.setOnAction(e->{
            upgradeBtn2.setDisable(true);
            AnimationType.setVisible(true,activateBtn,cancelBtn2,priceLbl);
            AnimationType.setVisible(false,backBtn,upgradeBtn2);
            AnimationType.fade(priceLbl,.8,0,1);
            AnimationType.moveY(priceLbl,.8,24,0);
            AnimationType.fade(arrowImg,.8,1,0);
            AnimationType.fade(infoRight,.5,1,0);
            AnimationType.fade(infoLeft,.5,1,0);
            AnimationType.moveX(car,.8,0,276);
            AnimationType.moveX(upgradedCar,.8,0,-276);
            AnimationType.fade(upgradedCar,.4,1,0);
            AnimationType.fade(rankFirst,.5,1,0);
            AnimationType.fade(rankSecond,.5,1,0);
            AnimationType.moveY(rankFirst,.5,0,-16);
            AnimationType.moveY(rankSecond,.5,0,-16);
            AnimationType.moveY(infoLeft,.5,0,-16);
            AnimationType.moveY(infoRight,.5,0,-16,()->cancelBtn2.setVisible(true));

        });
    }
    private void upgradeClicked(GarageCarCollections obj){
        double duration=.8;
        upgradeBtn.setOnAction(e->{
            car.setScaleX(-1);
            Model.getInstance().getGarageAnimation().upgrade(leftPane,rightPane,backBtn,()->{
                stage2.setVisible(true);
                upgradeBtn2.setVisible(true);
                activateBtn.setOpacity(1);
                AnimationType.fade(infoLeft,duration,0,1);
                AnimationType.moveX(arrowImg,duration,-32,0);
                AnimationType.fade(arrowImg,duration,0,1,()->{
                    AnimationType.fade(infoRight,duration,0,1);
                    AnimationType.moveX(infoRight,duration,-32,0);
                    upgradedCar.setImage(new Image(getClass().getResource(GarageCarCollections.getUpgradedNormal(obj.getId())).toString()));
                    AnimationType.moveX(upgradedCar,duration,-120,0);
                    AnimationType.fade(upgradedCar,duration,0,1,()->{
                        AnimationType.fade(rankFirst,.5,0,1);
                        AnimationType.fade(rankSecond,.5,0,1);
                        AnimationType.moveX(rankFirst,.5,-32,0);
                        AnimationType.moveX(rankSecond,.5,-32,0,()->{
                            AnimationType.moveY(upgradeBtn2,.8,32,0);
                            AnimationType.fade(upgradeBtn2,.8,0,1,()->upgradeBtn2.setDisable(false));
                        });
                    });
                });
            });});
    }
    private void backBtnClicked(){
        backBtn.setOnAction(e->{
            car.setScaleX(1);
            stage2.setVisible(false);
            AnimationType.setOpacity(0,arrowImg,upgradedCar,infoRight,infoLeft,rankFirst,rankSecond,upgradeBtn2);
            upgradeBtn2.setDisable(true);
            Model.getInstance().getGarageAnimation().back(leftPane,rightPane,backBtn);
        });
    }

}
