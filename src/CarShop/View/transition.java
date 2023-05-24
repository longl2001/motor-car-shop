package CarShop.View;

import CarShop.View.Animation.CarsAnimation;
import CarShop.View.Animation.LoginAnimation;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class transition {
    public static FadeTransition fadeTransition;
    public static ScaleTransition scaleTransition;
    public static TranslateTransition translateTransition;

    public transition() {
    }

    public void moveStoreCar(ImageView img){
        CarsAnimation.moveCarY(img);
    }
    public void moveGarageCar(ImageView img){
        CarsAnimation.moveCar(img);
    }

    public static TranslateTransition moveBtnToRight(Button btn) {
        translateTransition = new TranslateTransition(Duration.seconds(.8), btn);
        translateTransition.setFromX(0);
        translateTransition.setToX(200);
        translateTransition.setOnFinished(e -> {
            btn.setTranslateX(0);
        });
        translateTransition.play();

        return translateTransition;
    }
    /**
     * move label
     *
     * @param lbl
     * @return
     */
    public static TranslateTransition moveLblY(Label lbl) {
        translateTransition = new TranslateTransition(Duration.seconds(.1), lbl);
        translateTransition.setToY(-20);
        translateTransition.play();
        return translateTransition;
    }

    public static ScaleTransition scaleLbl(Label lbl) {
        scaleTransition = new ScaleTransition(Duration.seconds(.1), lbl);
        scaleTransition.setToX(.8);
        scaleTransition.setToY(.8);
        scaleTransition.play();
        return scaleTransition;
    }

    public static void moveLblUp(Label lbl) {
        moveLblY(lbl);
        scaleLbl(lbl);
    }

    public TranslateTransition negMoveLblY(Label lbl) {
        translateTransition = new TranslateTransition(Duration.seconds(.1), lbl);
        translateTransition.setToY(0);
        translateTransition.play();
        return translateTransition;
    }

    public ScaleTransition negScaleLbl(Label lbl) {
        scaleTransition = new ScaleTransition(Duration.seconds(.1), lbl);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        return scaleTransition;
    }

    public void moveLblDown(Label lbl) {
        negMoveLblY(lbl);
        negScaleLbl(lbl);
    }

    /**
     * animation after successful login
     */
    public TranslateTransition loginSuccessful(HBox pane) {
        pane.setVisible(true);
        translateTransition = new TranslateTransition(Duration.seconds(.5), pane);
        translateTransition.setFromY(584);
        translateTransition.setToY(0);

        fadeTransition = new FadeTransition(Duration.seconds(.5), pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        translateTransition.play();
        fadeTransition.play();

        return translateTransition;
    }

}
