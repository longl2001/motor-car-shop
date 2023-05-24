package CarShop.View.Animation;

import CarShop.Model.Model;
import CarShop.Constants.MenuOptions;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class MenuAnimation {
    public MenuAnimation(){

    }
    public void setListener(Button dashBoardBtn, Button storeBtn, Button garageBtn, HBox store, HBox dashBoard, HBox garage){
        Model.getInstance().getView().getSelection().addListener((observableValue, oldVal, newVal) -> {
            if(newVal== MenuOptions.DASHBOARD){
                setBtnClicked(dashBoardBtn);
                setContainerClicked(dashBoard);
            }else if(newVal==MenuOptions.STORE){
                setBtnClicked(storeBtn);
                setContainerClicked(store);
            } else if(newVal==MenuOptions.GARAGE){
                setBtnClicked(garageBtn);
                setContainerClicked(garage);
            }
            if(oldVal==MenuOptions.DASHBOARD){
                reverseBtn(dashBoardBtn);
                reverseContainer(dashBoard);
            }else if(oldVal== MenuOptions.STORE){
                reverseBtn(storeBtn);
                reverseContainer(store);
            } else if(oldVal==MenuOptions.GARAGE){
                reverseBtn(garageBtn);
                reverseContainer(garage);
            }
        });
    }
    private void setBtnClicked(Button btn){
        btn.setStyle("-fx-background-color: black;");
    }
    private void reverseBtn(Button btn){
        btn.setStyle("-fx-text-fill: #b4b8c2;");
    }
    private void setContainerClicked(HBox container){
        container.setStyle("-fx-border-color: black;"+"-fx-border-width:0 0 0 3;");
    }
    private void reverseContainer(HBox container){
        container.setStyle("-fx-border-color: white;"+"-fx-border-width:0 0 0 0;");
    }
}
