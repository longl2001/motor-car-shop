package CarShop.View.Animation;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
public class AnimationType {
    static TranslateTransition translateTransition;
    static FadeTransition fadeTransition;
    static ScaleTransition scaleTransition;
    static RotateTransition rotateTransition;
    public AnimationType(){
    }
    /**
     * motion
     */
    public static void moveX2(double duration, double fromX, double toX,Node... params) {
        moveX2(duration, fromX, toX, () -> {},params);
    }
    public static void moveX2( double duration, double fromX, double toX,Runnable onFinished,Node... params){
        translateTransition=new TranslateTransition(Duration.seconds(duration),params[0]);
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.play();
        translateTransition.setOnFinished(e->onFinished.run());
    }

    /**
     * Animate in X direction
     *
     */
    public static void moveX(Node node , double duration, double fromX, double toX) {
        moveX(node, duration, fromX, toX, () -> {});
    }
    public static void moveX(Node node, double duration, double fromX, double toX,Runnable onFinished){
        translateTransition=new TranslateTransition(Duration.seconds(duration),node);
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.play();
        translateTransition.setOnFinished(e->onFinished.run());
    }

    /**
     * Animate in Y direction
     *
     */
    public static void moveY(Node node, double duration,double fromY,double toY){
        moveY(node,duration,fromY,toY,()->{});
    }
    public static void moveY(Node node,double duration,double fromY,double toY,Runnable onFinished){
        translateTransition=new TranslateTransition(Duration.seconds(duration),node);
        translateTransition.setFromY(fromY);
        translateTransition.setToY(toY);
        translateTransition.play();
        translateTransition.setOnFinished(e->onFinished.run());
    }

    /**
     * Animate the opacity
     *
     */
    public static void fade(Node node, double duration,double fromVal,double toVal){
        fade(node,duration,fromVal,toVal,()->{});
    }
    public static void fade(Node node,double duration,double fromVal,double toVal,Runnable onFinished){
        fadeTransition = new FadeTransition(Duration.seconds(duration),node);
        fadeTransition.setFromValue(fromVal);
        fadeTransition.setToValue(toVal);
        fadeTransition.play();
        fadeTransition.setOnFinished(e->onFinished.run());
    }

    /**
     * Animate the size or scaling
     *
     */
    public static void scale(Node node, double duration,double fromVal,double toVal){
        scale(node,duration,fromVal,toVal,()->{});
    }
    public static void scale(Node node,double duration,double fromVal, double toVal,Runnable onFinished){
        scaleTransition = new ScaleTransition(Duration.seconds(duration),node);
        scaleTransition.setFromX(fromVal);
        scaleTransition.setFromY(fromVal);
        scaleTransition.setToX(toVal);
        scaleTransition.setToY(toVal);
        scaleTransition.play();
        scaleTransition.setOnFinished(e->onFinished.run());
    }

    /**
     * Animate the rotation
     *
     */
    public static void rotate(Node node,double duration, double byAngle,double toAngle){
        rotate(node,duration,byAngle,toAngle,()->{});
    }
    public static void rotate(Node node, double duration, double byAngle, double toAngle,Runnable onFinished){
        rotateTransition = new RotateTransition(Duration.seconds(duration),node);
        rotateTransition.setByAngle(byAngle);
        rotateTransition.setToAngle(toAngle);
        rotateTransition.play();
        rotateTransition.setOnFinished(e->onFinished.run());
    }

    /**
     * Flip the object
     *
     */
    public static void flip(Node node,double duration,double byAngle,double toAngle){
        flip(node,duration,byAngle,toAngle,()->{});
    }
    public static void flip(Node node, double duration, double byAngle, double toAngle,Runnable onFinished){
        rotateTransition = new RotateTransition(Duration.seconds(duration),node);
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setFromAngle(byAngle);
        rotateTransition.setToAngle(toAngle);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        rotateTransition.setOnFinished(e->onFinished.run());
    }
    /**
     * reset to original state of given Node
     * @param node
     */
    public static void rescale(Node node){
        node.setScaleX(1);
        node.setScaleY(1);
    }
    /**
     * Change the visibility of objects
     */
    public static void setVisible(Boolean isVisible,Node...params){
        for(Node node:params){
            node.setVisible(isVisible);
        }
    }
    /**
     * Change the opacity of objects
     */
    public static void setOpacity(double value,Node...params){
        for (Node node:params){
            node.setOpacity(value);
        }
    }

}
