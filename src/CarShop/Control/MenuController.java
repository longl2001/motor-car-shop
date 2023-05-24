package CarShop.Control;

import CarShop.Model.Model;
import CarShop.Constants.MenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private HBox store, dashboard, garage;
    @FXML
    private Button storeBtn, dashboardBtn, garageBtn,logoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
        clickedAction();
        logout();
    }
    private void logout(){
        logoutBtn.setOnAction(e->{
            Model.getInstance().getView().hideLogin((Stage) store.getScene().getWindow());
        });
    }
    private void addListener() {
        Model.getInstance().getMenuAnimation().setListener(dashboardBtn, storeBtn, garageBtn,store,dashboard,garage);
        Model.getInstance().getView().getSelection().set(MenuOptions.DASHBOARD);
    }
    private void clickedAction(){
        dashboardBtn.setOnAction(e -> onDashboard());
        storeBtn.setOnAction(e -> onStore());
        garageBtn.setOnAction(e -> onGarage());
        dashboard.setOnMouseClicked(e -> onDashboard());
        store.setOnMouseClicked(e -> onStore());
        garage.setOnMouseClicked(e -> onGarage());
    }

    private void onDashboard() {
        Model.getInstance().getView().getSelection().set(MenuOptions.DASHBOARD);
    }

    private void onStore() {
        Model.getInstance().getView().getSelection().set(MenuOptions.STORE);
    }

    private void onGarage() {
        Model.getInstance().getView().getSelection().set(MenuOptions.GARAGE);
    }


}
