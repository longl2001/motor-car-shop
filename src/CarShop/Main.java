package CarShop;

import CarShop.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.SQLException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {

        Model.getInstance().getView().load();
        Model.getInstance().getView().showLoginWindow();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
