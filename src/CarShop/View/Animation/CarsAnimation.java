package CarShop.View.Animation;
import javafx.scene.image.ImageView;
public class CarsAnimation {
    public CarsAnimation(){
    }
    public static void moveCar(ImageView img) {
        AnimationType.moveX(img,.8,160,0);
        AnimationType.scale(img,.8,1,1.3);
        AnimationType.fade(img,.8,0,1);
    }
    /**
     * cars in store
     * @param imgView
     */
    public static void moveCarY(ImageView imgView) {
        AnimationType.moveY(imgView,.5,100,0);
    }

    /**
     * animate the horizontal cars in store
     * @param car
     */
    public static void animateStoreHCar(ImageView car){
        AnimationType.moveX(car,.8,32,0);
        AnimationType.fade(car,.8,0,1);
        AnimationType.scale(car,.8,1,1.2);
    }
}
