package CarShop.Control;

import CarShop.Model.Model;
import CarShop.Constants.MenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class combinController implements Initializable {
    @FXML
    private BorderPane combinePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getView().getSelection().addListener((observableValue,oldVal,newVal)-> {
            if(newVal== MenuOptions.STORE){
                combinePane.setCenter(MenuOptions.STORE.getNode());
            } else if(newVal==MenuOptions.GARAGE){
                combinePane.setCenter(MenuOptions.GARAGE.getNode());
            }
            else{
                combinePane.setCenter(MenuOptions.DASHBOARD.getNode());
            }
        });

    }

}
