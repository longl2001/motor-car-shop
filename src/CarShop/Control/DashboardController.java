package CarShop.Control;

import CarShop.Constants.GarageCarCollections;
import CarShop.Constants.StoreCarCollections;
import CarShop.DBConnection.DBHandler;
import CarShop.Model.Model;
import CarShop.Model.RankedCar;
import CarShop.View.Animation.AnimationType;
import CarShop.View.Data.CarPrice;
import CarShop.Constants.MenuOptions;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    private XYChart.Series<Number, Number> series1,series2;
    @FXML
    private LineChart<Number,Number> lineChart3;
    @FXML
    private Label portfolioLbl;
    @FXML
    private Label priceChangeLbl;
    @FXML
    private Label balanceLbl;
    @FXML
    private PieChart pieChart;
    @FXML
    private TextField depositField;
    @FXML
    private StackPane pieChartContainer;
    private int k=0;
    private double newVal=0;
    private double originalVal;
    private double priceChange;
    private String priceFormat;
    private double temp2;
    private double percentChange=0;
    private String percentFormat;
    private boolean isEntered=false;
    private String concat;
    private String value;
    private CarPrice carPrice;
    private double remaining;
    //Balance section
    @FXML
    private Label balPercentage;
    //transaction section
    @FXML
    private Button submitBtn;
    @FXML
    private HBox depositLayer;
    @FXML
    private Button withdrawBtn;
    @FXML
    private Button depositBtn;
    @FXML
    private Label warningLbl;
    @FXML
    private VBox BOA;
    @FXML
    private VBox wellsFargo;
    @FXML
    private Label exchangeLbl;
    private boolean isDeposit=true;

    //Confirm section
    @FXML
    private HBox confirmContainer;
    @FXML
    private VBox confirmHolder;
    @FXML
    private Label transactionType,amountLbl;
    @FXML
    private Button okBtn;
    //Rank section
    @FXML
    private VBox rankContainer;
    @FXML
    private HBox rank1,rank2,rank3,rank4,rank5,rank6;
    @FXML
    private Label name1,name2,name3,name4,name5,name6;
    @FXML
    private Label price1,price2,price3,price4,price5,price6;
    @FXML
    private Label rankVal1,rankVal2,rankVal3,rankVal4,rankVal5,rankVal6;
    //Database Connection
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement st;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handler = new DBHandler();
        setPricing();
    }
    private void setPricing(){
        Model.getInstance().getAccount().setBalance(getFormatBal());
        Model.getInstance().getView().getSelection().addListener((observableVal,oldVal,newVal)->{
            if(newVal== MenuOptions.DASHBOARD){
                setPieChart();
                setBalance();
            }
        });
        setTimeline();
    }
    private void setBalance(){
        portfolioLbl.setText(String.format("%,.2f",getPortfolioVal()));
        balPercentage.setText(String.format("%,.2f",0.0)+"%");
        if(Model.getInstance().getCarPrice().getList().size()!=0){
            balanceLbl.setText(String.format("%,.2f",Model.getInstance().getAccount().getBalance()));
        }
    }
    private void setTimeline(){
        porfolioSection();
        inventorySection();
        transactionSection();
        rankSection();
    }
    private void rankSection(){
        ObservableList<RankedCar> rankedCars=createRankedCars();
        ObservableList<Double> priceList=FXCollections.observableArrayList();
        for (RankedCar car:rankedCars){
            priceList.add(StoreCarCollections.getCar(car.getId()).getPriceVal());
        }
        FXCollections.sort(priceList);
        for (int i = priceList.size()-1; i >=0 ; i--) {
            RankedCar temp = Model.getInstance().getDashboardAnimation().getCar(rankedCars,priceList.get(i));
            temp.getPrice().setText(""+priceList.get(i));
                temp.getRankVal().setText("" + (priceList.size() - i));

            String brand=StoreCarCollections.getCar(temp.getId()).getBrand().toUpperCase();
            temp.getName().setText(""+brand+" "+
                    StoreCarCollections.getCar(temp.getId()).getModel());
            rankContainer.getChildren().add(temp.getRank());
        }
    }
    private ObservableList<RankedCar> createRankedCars(){
        ObservableList<StoreCarCollections> carList = FXCollections.observableArrayList();
        for(StoreCarCollections car:StoreCarCollections.values()){
            carList.add(car);
        }
        rankContainer.getChildren().removeAll(rank1,rank2,rank3,rank4,rank5,rank6);
        RankedCar car1=new RankedCar(rank1,name1,price1,rankVal1,carList.get(0).getId());
        RankedCar car2=new RankedCar(rank2,name2,price2,rankVal2,carList.get(1).getId());
        RankedCar car3=new RankedCar(rank3,name3,price3,rankVal3,carList.get(2).getId());
        RankedCar car4=new RankedCar(rank4,name4,price4,rankVal4,carList.get(3).getId());
        RankedCar car5=new RankedCar(rank5,name5,price5,rankVal5,carList.get(4).getId());
        RankedCar car6=new RankedCar(rank6,name6,price6,rankVal6,carList.get(5).getId());

        ObservableList<RankedCar> rankedCars = FXCollections.observableArrayList(car1,car2,car3,car4,car5,car6);
        return rankedCars;
    }
    private boolean isValidNumber(String input) {
        try {
            double number = Double.parseDouble(input);
            return number > 50 && number <=10000000;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void allowTransaction(){
        warningLbl.setVisible(false);
        double val= Double.parseDouble(depositField.getText());
        double newVal;
        double newPercent;
        if(isDeposit){
            newVal=Model.getInstance().getAccount().getBalance()+val;
            newPercent=val*100/Model.getInstance().getAccount().getBalance();
        }else{
            newVal=Model.getInstance().getAccount().getBalance()-val;
            newPercent=-val*100/Model.getInstance().getAccount().getBalance();
        }
        Model.getInstance().getAccount().setBalance(newVal);
        Model.getInstance().getAccount().setBalPercentage(newPercent);

    }

    private void preventTransaction(){
        warningLbl.setVisible(true);
        Model.getInstance().getDashboardAnimation().vibingLbl(submitBtn);
    }
    private boolean isEnoughBalance(){
        double val= Double.parseDouble(depositField.getText());
        if(!isDeposit && val>Model.getInstance().getAccount().getBalance()){
            return false;
        }
        return true;
    }
    private void transactionSection(){
        submitBtn.setOnAction(e->{
            if(isValidNumber(depositField.getText())&& isEnoughBalance()){
                allowTransaction();
                if(isDeposit){
                    Model.getInstance().getDashboardAnimation().confirmationAppear(confirmContainer,confirmHolder,
                            transactionType,amountLbl,Double.parseDouble(depositField.getText()),"Deposit:");
                } else{
                    Model.getInstance().getDashboardAnimation().confirmationAppear(confirmContainer,confirmHolder,
                            transactionType,amountLbl,-Double.parseDouble(depositField.getText()),"Withdraw:");
                }
                depositField.clear();
            }else{
                preventTransaction();
            }
        });
        String black="-fx-text-fill:black;";
        String white="-fx-text-fill:white;";
        String bg="-fx-background-color:transparent;";
        String rad="-fx-background-radius:10;";
        okBtn.setOnAction(e->Model.getInstance().getDashboardAnimation().reverseConfirmation(confirmContainer,confirmHolder));
        withdrawBtn.setOnAction(e->{
            isDeposit=false;
            if(depositLayer.getTranslateX()!=146){
                depositBtn.setStyle(black+bg+rad);
                withdrawBtn.setStyle(white+bg+rad);
                AnimationType.moveX(depositLayer,.2,0,146);
            }

        });
        depositBtn.setOnAction(e->{
            isDeposit=true;
            if(depositLayer.getTranslateX()!=0){
                depositBtn.setStyle(white+bg+rad);
                withdrawBtn.setStyle(black+bg+rad);
                AnimationType.moveX(depositLayer,.2,146,0);
            }
        });
    }
    private void inventorySection(){
        setPieChart();
    }
    private void porfolioSection(){
        setSeries1();
        startTimeLine();
        setStyle();
    }
    private void setStyle(){
        Model.getInstance().getDashboardAnimation().setStyle(lineChart3);
    }
    private void setSeries1() {
        series1=Model.getInstance().getDashboardAnimation().createSeries();
        series2=new XYChart.Series<>();
    }
    private void setPieChart() {
        Model.getInstance().getDashboardAnimation().createPieChart(pieChart);
    }
    private void addListener2(){

        if(Model.getInstance().getAccount().getAccountID()!=0){
            updateAccountDB();
        }
    }
    private void startTimeLine() {

        originalVal=Model.getInstance().getAccount().getPortfolioVal();
        lineChart3.getData().addAll(series1,series2);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            for(GarageCarCollections car:GarageCarCollections.getSubset()){
                double newPrice=car.getPrice()+car.getPrice()*getRandomPercentage();
                car.setPrice(newPrice);
            }
            Model.getInstance().getAccount().updatePortfolio();
            addListener2();
            moveChart();
            balanceLbl.setText(String.format("%,.2f",Model.getInstance().getAccount().getBalance()));
            balPercentage.setText(String.format("%,.2f",Model.getInstance().getAccount().getBalPercentage())+"%");
            newVal=Model.getInstance().getAccount().getPortfolioVal();
            priceChange=newVal-originalVal;
            if(originalVal==0 && newVal!=0) {
                percentChange=100;
            }else if(newVal==0){
                percentChange=0;
            }
            else {
                percentChange = (priceChange / originalVal) * 100;
            }
            value = String.format("%,.2f",newVal);
            priceFormat=String.format("%,.2f",priceChange);
            percentFormat=String.format("%,.2f",percentChange);
            concat=priceFormat+" ("+percentFormat+"%)";

            if(isEntered==false){
                portfolioLbl.setText(value);
                priceChangeLbl.setText(concat);
            }
            Model.getInstance().getDashboardAnimation().setSeries2(series2,k,newVal);
            k+=1;
            if(k>=60){
                k=0;
                setStyle();
                series2.getData().clear();
                originalVal=Model.getInstance().getAccount().getPortfolioVal();
            }
            for (XYChart.Data<Number, Number> data : series2.getData()) {
                Node node = data.getNode();
                setHover(node,data);
                setExit(node);
            }


        }));
        timeline.setCycleCount(timeline.INDEFINITE);
        timeline.play();
    }
    private double getRandomPercentage() {
        Random random = new Random();
        double randomValue = (random.nextDouble() * 0.1) - 0.05;
        return randomValue;
    }
    private void updateAccountDB(){
        connection = handler.getConnection();
            try  {
                String update = "UPDATE Account SET balance = ?, portfolio_val = ? WHERE user_id = ?";
                st = connection.prepareStatement(update);
                st.setDouble(1, Model.getInstance().getAccount().getBalance());
                st.setDouble(2, Model.getInstance().getAccount().getPortfolioVal());
                st.setInt(3, Model.getInstance().getAccount().getAccountID());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    private void moveChart(){
        if (Model.getInstance().getGarageList().getList().size()==0){
            lineChart3.setTranslateY(-64);
        }else{
            lineChart3.setTranslateY(0);
        }
    }
    private double getPortfolioVal(){
        double total=0;
        for(GarageCarCollections car: Model.getInstance().getGarageList().getList()){
            total+=car.getPrice();
        }
        Model.getInstance().getAccount().setPortfolioVal(total);
        return total;
    }
    private double getFormatBal(){
        String temp=balanceLbl.getText().replaceAll(",","");
        return Double.parseDouble(temp);
    }

    private void setHover(Node node,XYChart.Data<Number,Number> data){
        node.setOnMouseEntered(e -> {
            node.setOpacity(.2);
            isEntered = true;
            String value1 = String.format("%,.2f",data.getYValue());
            portfolioLbl.setText(value1);

            temp2=(double)data.getYValue()-originalVal;
            String value2 = String.format("%,.2f",temp2);
            if(originalVal==0 && temp2!=0) {
                percentChange=100;
            }else if(temp2==0){
                percentChange=0;
            }
            else {
                percentChange = (temp2 / originalVal) * 100;
            }
            percentFormat=String.format("%,.2f",percentChange);
            concat=value2+" ("+percentFormat+"%)";
            priceChangeLbl.setText(concat);
        });
    }
    private void setExit(Node node){
        node.setOnMouseExited(e -> {
            node.setOpacity(0);
            portfolioLbl.setText(value);
            isEntered = false;
        });
    }
}

