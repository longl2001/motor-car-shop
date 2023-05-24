package CarShop.View.Circle_progress;

import CarShop.Constants.GarageCarCollections;
import javafx.application.Platform;

public class ProgressThread extends Thread {
    RingProgressIndicator rpi;
    GarageCarCollections car;
    int progress;
    public ProgressThread(RingProgressIndicator rpi, GarageCarCollections car){
        this.rpi=rpi;
        this.car=car;
        progress=0;
    }
    public void run(){
        while(true){
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->{
                rpi.setProgress(progress);
            });
            progress++;
            if(progress>=car.getRating()) break;
        }
    }
}
