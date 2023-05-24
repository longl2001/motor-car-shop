package CarShop.View.Animation;

import CarShop.Constants.CarPath;
import CarShop.Constants.GarageCarCollections;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Collections;

public class GarageAnimation {
    private Image img;
    private Timeline timeline;
    private double newWidth;

    public GarageAnimation(){
    }

    /**
     * Set the general image frame and animate the this image
     * @param car
     */
    public void moveCar(GarageCarCollections car){
        ImageView imgView=GarageCarCollections.GENERAL.getFrame();
        imgView.setImage(new Image(getClass().getResource(GarageCarCollections.getNormalImg(car.getId())).toString()));
        AnimationType.moveX(imgView,.8,160,0);
        AnimationType.scale(imgView,.8,.8,1);
        AnimationType.fade(imgView,.8,0,1);
    }

    /**
     *Set the horizontal image frame and animate this image
     * @param car
     */
    public void setHCar(GarageCarCollections car){
        ImageView img= car.getFrame2();
        img.setImage(new Image(getClass().getResource(GarageCarCollections.getHorizontalImg(car.getId())).toString()));
        //AnimationType.scale(img,.8,.5,1);
        AnimationType.moveY(img,.8,10,0);
        AnimationType.fade(img,.8,0.5,1);
    }
    public void movePane(VBox left, VBox right){
        left.setVisible(false);
        AnimationType.moveX(right,.5,0,-552);
    }

    /**
     *Animate car attributes
     * @param car
     * @param onFinished
     */
    public void animateProgress(GarageCarCollections car,Runnable onFinished){
        double max= Collections.max(car.getCarInfo())*2;
        timeline = new Timeline(new KeyFrame(Duration.seconds(.008), event -> {
            for (int i = 0; i < car.getList().size(); i++) {
                 newWidth = car.getList().get(i).getPrefWidth() +2;
                if (newWidth > car.getCarInfo().get(i)*2) {
                    newWidth = car.getCarInfo().get(i)*2;
                }
                car.getList().get(i).setPrefWidth(newWidth);
            }
        }));
        timeline.setCycleCount((int)max/2);
        timeline.setOnFinished(e->onFinished.run());
        timeline.play();

    }
    public void setText(GarageCarCollections car){
        for (int i = 0; i < car.getList().size(); i++) {
            int text=car.getCarInfo().get(i);
            GarageCarCollections.GENERAL.getLabel().get(i).setText(String.valueOf(text));
        }
    }
    public void resetBars(GarageCarCollections car){
        for (int i = 0; i < car.getList().size(); i++) {
            car.getList().get(i).setPrefWidth(0);
        }
    }
    public void setBarsColor(GarageCarCollections car){
        for (HBox bar: car.getList()){
            bar.setStyle("-fx-background-color:"+car.getColor()+";");
        }
    }
    public void setUpgradeColor(Button upgradeBtn, GarageCarCollections car){
        String bgStyle="-fx-background-color: #001c6c;";
        String radStyle= "-fx-background-radius:10 0 0 10;";
        upgradeBtn.setStyle(bgStyle+"\n"+radStyle+"\n");
    }

    /**
     * Set the brand and model titles
     * @param car
     */
    public void setTitle(GarageCarCollections car){
        String brand = car.getBrand().toUpperCase();
        String model = car.getModel().toUpperCase();
        GarageCarCollections.GENERAL.getTitleGeneral().get(0).setText(brand);
        GarageCarCollections.GENERAL.getTitleGeneral().get(1).setText(model);
    }

    public void animateTitle(){
        for (int i = 0; i < 2; i++) {
            AnimationType.moveX(GarageCarCollections.GENERAL.getTitleGeneral().get(i),.8,24,0);
            AnimationType.fade(GarageCarCollections.GENERAL.getTitleGeneral().get(i),.8,0,1);
        }

    }
    public void animateAttributes(GarageCarCollections car){
        for (int i = 0; i < 4; i++) {
        int index=i;
        int startValue = 0;
        int endValue = car.getCarInfo().get(i);
        int durationInMilis = 800;
        int frameCount = endValue - startValue + 1;
        int frameDuration = durationInMilis / frameCount;

        Timeline timeline = new Timeline();
        for (int j = startValue; j <= endValue; j++) {
            int frameIndex = j - startValue;
            String frameText = String.format("%02d", j);
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(frameIndex * frameDuration),
                    e -> GarageCarCollections.GENERAL.getLabel().get(index).setText(frameText)
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
        }
    }
    public void upgrade(VBox leftPane, VBox rightPane, Button backBtn,Runnable onFinished){
        AnimationType.moveX(leftPane,.8,0,-552);
        AnimationType.fade(leftPane,.8,1,.3);
        AnimationType.moveX(rightPane,.6,0,-552,()->{
            backBtn.setVisible(true);
            onFinished.run();
        });

    }
    public void back(VBox leftPane,VBox rightPane,Button backBtn){
        AnimationType.moveX(leftPane,.7,-552,0);
        AnimationType.fade(leftPane,.7,0.3,1);
        AnimationType.moveX(rightPane,.7,-552,0,()->{
            backBtn.setVisible(false);
        });
    }

    public void upgradeAnimation(int id,VBox linesContainer,ImageView imgHolder,HBox pinkCircle,HBox blackCircle,VBox boxContainer,
                                 HBox upperBox,HBox lowerBox,HBox layer,ImageView tatImg,Runnable onFinished){
        linesContainer.setVisible(true);
        AnimationType.moveX(linesContainer,.5,-400,400);
        AnimationType.scale(imgHolder,.5,1,.1);
        AnimationType.rotate(imgHolder,.5,0,-270,()->{
            layer.setVisible(true);
            AnimationType.moveY(imgHolder,.2,0,40,()->{
                imgHolder.setRotate(0);
                imgHolder.setVisible(false);
                AnimationType.moveY(imgHolder,.01,40,0);
                pinkCircle.setVisible(true);
                AnimationType.fade(pinkCircle,.8,0,1);
                AnimationType.scale(pinkCircle,.8,1,1.25,()->{
                    blackCircle.setVisible(true);
                    AnimationType.fade(blackCircle,0.02,0,1,()->{
                        blackCircle.setVisible(false);
                        boxContainer.setVisible(true);
                        AnimationType.fade(boxContainer,.8,0,1);
                        AnimationType.fade(upperBox,.8,0,1);
                        AnimationType.moveY(upperBox,.8,-40,0);
                        AnimationType.fade(lowerBox,.8,0,1);
                        AnimationType.scale(pinkCircle,.8,1.25,1);
                        AnimationType.moveY(lowerBox,.8,60,0,()->{
                            AnimationType.fade(pinkCircle,.8,1,0);
                            AnimationType.scale(pinkCircle,.8,1,0);
                            AnimationType.rotate(boxContainer,.8,0,180,()->{
                                pinkCircle.setOpacity(1);
                                AnimationType.scale(layer,1.2,0,1.5);
                                AnimationType.fade(pinkCircle,1.2,1,0);
                                AnimationType.fade(upperBox,.7,1,0);
                                AnimationType.moveY(upperBox,.7,0,-40);
                                AnimationType.fade(lowerBox,.7,1,0);
                                AnimationType.moveY(lowerBox,.7,0,60);
                                imgHolder.setVisible(true);
                                setImg(imgHolder, GarageCarCollections.getUpgradedNormal(id));
                                AnimationType.scale(imgHolder,.8,.1,1);
                                AnimationType.scale(pinkCircle,1.2,0,1.75,()->{
                                    AnimationType.rescale(layer);
                                    AnimationType.rescale(pinkCircle);
                                    boxContainer.setRotate(0);
                                    pinkCircle.setVisible(false);
                                    layer.setVisible(false);
                                    linesContainer.setVisible(false);
                                    boxContainer.setOpacity(0);
                                    tatImg.setRotate(0);
                                    onFinished.run();
                                });
                            });

                        });
                    });

                });

            });
        });

    }
    public void setImg(ImageView img,String path){
        img.setImage(new Image(getClass().getResource(path).toString()));
    }
}
