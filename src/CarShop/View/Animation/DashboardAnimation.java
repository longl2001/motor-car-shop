package CarShop.View.Animation;

import CarShop.Constants.StoreCarCollections;
import CarShop.Model.Model;
import CarShop.Model.RankedCar;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class DashboardAnimation {
    private XYChart.Data<Number,Number> data1;
    public DashboardAnimation(){

    }
    public Line createLine(){
        Line line = new Line(0,0,0,300);
        line.setTranslateY(150);
        line.setStrokeWidth(2);
        line.setStyle("-fx-fill:#adaeb2");

        line.setOpacity(0);
        return line;
    }
    public void setStyle(LineChart lineChart3){
        Region plotArea = (Region) lineChart3.lookup(".chart-plot-background");
        plotArea.setStyle("-fx-background-color: transparent;");
    }
    public XYChart.Series createSeries(){
        XYChart.Series<Number,Number> series = new XYChart.Series();
        for (int i = 0; i < 60; i++) {
            XYChart.Data<Number, Number> data = new XYChart.Data(Integer.toString(i), 0);
            series.getData().add(data);
        }
        return series;
    }
    public void setSeries2(XYChart.Series series2,int x,double y){
        data1 = new XYChart.Data(Integer.toString(x), y);
        data1.setNode(Model.getInstance().getDashboardAnimation().createLine());
        series2.getData().add(data1);

    }

    public void createPieChart(PieChart pieChart){
        ObservableList<PieChart.Data> pieList= FXCollections.observableArrayList(
                new PieChart.Data("Tesla",0),
                new PieChart.Data("Bugatti",0),
                new PieChart.Data("Ford",0),
                new PieChart.Data("Rolls Royce",0));
        pieChart.setData(pieList);

        ObservableList<Integer> list= FXCollections.observableArrayList(123000,100000,90000,200000);

        int i=0;
        double totalVal=0;
        for (PieChart.Data data: pieChart.getData()){
            data.getNode().setScaleY(1.1);
            data.getNode().setScaleX(1.1);
            data.setPieValue(list.get(i));
            totalVal+=data.getPieValue();
            i++;
        }
        animatePieChart(pieChart,totalVal);
    }
    public void animatePieChart(PieChart pieChart,double totalVal){
        for(PieChart.Data data: pieChart.getData()){
            data.getNode().setOnMouseClicked(e->{
                Bounds b1 = data.getNode().getBoundsInLocal();
                double X = (b1.getWidth()) / 2.0 + b1.getMinX();
                double Y = (b1.getHeight()) / 2.0 + b1.getMinY();
                String temp=data.getName();
                String percentage= String.format("%,.0f",data.getPieValue()*100/totalVal);
                data.setName(data.getName()+"\n"+"("+percentage+"%)");
                data.getNode().setTranslateX(0);
                data.getNode().setTranslateY(0);

                moveX(data.getNode(),.8,X,Y,()->{
                    data.setName(temp);
                });
            });
        }
    }
    private void moveX(Node node,double duration, double fromX,double fromY,Runnable onFinished){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(duration), node);
        tt.setByX(fromX);
        tt.setByY(fromY);
        tt.setAutoReverse(true);
        tt.setCycleCount(2);
        tt.play();
        tt.setOnFinished(e->onFinished.run());
    }
    public  RankedCar getCar(ObservableList<RankedCar> rankedCars,Double price){
            for (RankedCar car: rankedCars){
                if(StoreCarCollections.getCar(car.getId()).getPriceVal()==price){
                    return car;
                }
            }
        return null;
    }

    public void vibingLbl(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.1), node);
        translateTransition.setFromX(0);
        translateTransition.setToX(-10);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(4);

        translateTransition.play();
    }
    public void confirmationAppear(HBox container, VBox holder,Label type,Label amount,double val,String type2){
        container.setVisible(true);
        type.setText(type2);
        if(val>0){
            amount.setText("+$"+String.format("%,.2f",val));
            amount.setStyle("-fx-text-fill:#71cfc7;");
        }else{
            amount.setText("-$"+String.format("%,.2f",Math.abs(val)));
            amount.setStyle("-fx-text-fill:#da3531;");
        }
        AnimationType.fade(holder,.6,0,1);
        AnimationType.moveY(holder,.6,16,0);
    }
    public void reverseConfirmation(HBox container,VBox holder){
        AnimationType.moveY(holder,.2,0,-16);
        AnimationType.fade(holder,.2,1,0,()->{
            container.setVisible(false);
            holder.setTranslateY(0);
        });
    }

}
