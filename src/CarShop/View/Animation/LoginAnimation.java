package CarShop.View.Animation;

import CarShop.Constants.Hawks;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class LoginAnimation {
    static TranslateTransition translateTransition;
    static ScaleTransition scaleTransition;
    private static Timeline timeline;
    public LoginAnimation(){

    }
    /**
     * animations for username and password when the input field is clicked
     *
     * @param lbl
     * @return
     */

    public static void moveUpLbl(Label lbl) {
        moveLblY(lbl);
        scaleLbl(lbl);
    }
    public static void textUnFocused(HBox box,Label username, Label password){
        box.requestFocus();
        moveDownLbl(username);
        moveDownLbl(password);
    }
    public static void moveDownLbl(Label lbl){
        negMoveLblY(lbl);
        negScaleLbl(lbl);
    }
    private static TranslateTransition moveLblY(Label lbl) {
        translateTransition = new TranslateTransition(Duration.seconds(.1), lbl);
        translateTransition.setToY(-20);
        translateTransition.play();
        return translateTransition;
    }

    private static ScaleTransition scaleLbl(Label lbl) {
        scaleTransition = new ScaleTransition(Duration.seconds(.1), lbl);
        scaleTransition.setToX(.8);
        scaleTransition.setToY(.8);
        scaleTransition.play();
        return scaleTransition;
    }
    private static TranslateTransition negMoveLblY(Label lbl) {
        translateTransition = new TranslateTransition(Duration.seconds(.1), lbl);
        translateTransition.setToY(0);
        translateTransition.play();
        return translateTransition;
    }
    private static ScaleTransition negScaleLbl(Label lbl) {
        scaleTransition = new ScaleTransition(Duration.seconds(.1), lbl);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        return scaleTransition;
    }


    /**
     * HawksAnimations
     * @param img
     * @param duration
     * @param yValue
     */

    private static void moveY(HBox img,double duration, double yValue){

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), img);
        translateTransition.setToY(yValue);
        translateTransition.play();
    }
    private static void scale(HBox img,double duration,double xValue,double yValue){
        ScaleTransition scaleTransition=new ScaleTransition(Duration.seconds(duration),img);
        scaleTransition.setToX(xValue);
        scaleTransition.setToY(yValue);
        scaleTransition.play();
    }
    private static void fade(HBox img,double duration,double toValue){
        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(duration),img);
        fadeTransition.setToValue(toValue);
        fadeTransition.play();
    }
    public  void animateHawk() {


        //change img2 to gray
        ColorAdjust grayEffect = new ColorAdjust();
        grayEffect.setSaturation(-1);
        Hawks.HAWK.img2.setEffect(grayEffect);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(2);

        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    fade(Hawks.HAWK.img2,1,1);
                    scale(Hawks.HAWK.img2,.8,1,1);
                    fadeAndMove(Hawks.HAWK.lbl1,1);
                }),
                new KeyFrame(Duration.seconds(1.5), e -> {
                    fade(Hawks.HAWK.img1,.7,1);
                    fadeAndMove(Hawks.HAWK.lbl2,1);
                    fadeAndMove(Hawks.HAWK.lbl3,1);
                }),
                new KeyFrame(Duration.seconds(2.2), e -> {
                    fade(Hawks.HAWK.img1,.7,0);
                }),
                new KeyFrame(Duration.seconds(2.4), e -> {
                    fade(Hawks.HAWK.img3,1,.4);
                }),
                new KeyFrame(Duration.seconds(3.4), e -> {
                    fade(Hawks.HAWK.img4,.5,1);
                    moveY(Hawks.HAWK.img3,.5,-20);
                }),
                new KeyFrame(Duration.seconds(3.8), e -> {
                    fade(Hawks.HAWK.img3,.2,0);
                    Hawks.HAWK.img2.setEffect(colorAdjust);
                }),
                new KeyFrame(Duration.seconds(4.3), e -> {
                    scale(Hawks.HAWK.img2,.4,1.3,1.3);
                }),
                new KeyFrame(Duration.seconds(4.7), e -> {
                    scale(Hawks.HAWK.img2,.2,1,1);
                }),
                new KeyFrame(Duration.seconds(5), e -> {
                    Hawks.HAWK.img5.setVisible(true);
                }),
                new KeyFrame(Duration.seconds(5.05), e -> {
                    Hawks.HAWK.img5.setVisible(false);
                }),
                new KeyFrame(Duration.seconds(5.07), e -> {
                    fade(Hawks.HAWK.img4,.4,0);
                }),
                new KeyFrame(Duration.seconds(5.3), e -> {
                    fade(Hawks.HAWK.img6,.5,1);
                    scale(Hawks.HAWK.img6,.15,1.25,1.25);
                    scaleHeading(Hawks.HAWK.lbl1,.3);
                })
        );
        timeline.play();
    }

    /**
     * fade and move the loading title
     * @param lbl
     * @param time
     */
    private static void fadeAndMove(Label lbl,double time){
        //move lbl1
        TranslateTransition moveLbl1Y = new TranslateTransition(Duration.seconds(time),lbl);
        moveLbl1Y.setFromY(100);
        moveLbl1Y.setToY(0);
        //fade lbl1
        FadeTransition fadeLbl1 = new FadeTransition(Duration.seconds(time),lbl);
        fadeLbl1.setToValue(1);

        moveLbl1Y.play();
        fadeLbl1.play();
    }
    private static void scaleHeading(Label lbl,double duration){
        ScaleTransition scaleLbl = new ScaleTransition(Duration.seconds(duration),lbl);
        scaleLbl.setToX(1.1);
        scaleLbl.setToY(1.1);
        scaleLbl.play();
    }
    public void vibingImg(Node node){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.1), node);
        translateTransition.setFromX(0);
        translateTransition.setToX(-10);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(4);
        translateTransition.play();
    }
}
