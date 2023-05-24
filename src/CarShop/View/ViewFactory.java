package CarShop.View;
import CarShop.Constants.MenuOptions;
import CarShop.Constants.StoreCarCollections;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewFactory {
    private final ObjectProperty<MenuOptions> menuSelection;
    private final ObjectProperty<StoreCarCollections> storeSelection;
    public ViewFactory(){
        this.menuSelection=new SimpleObjectProperty<>();
        this.storeSelection=new SimpleObjectProperty<>();
    }
    public void load()  {
        loadFXML(MenuOptions.LOGIN,"/Fxml/login.fxml");
        loadFXML(MenuOptions.COMBIN,"/Fxml/combin.fxml");
        loadFXML(MenuOptions.STORE,"/Fxml/store.fxml");
        loadFXML(MenuOptions.DASHBOARD,"/Fxml/Dashboard.fxml");
        loadFXML(MenuOptions.GARAGE,"/Fxml/garage.fxml");
    }
    public ObjectProperty<MenuOptions> getSelection(){
        return menuSelection;
    }
    public ObjectProperty<StoreCarCollections> getStoreSelection(){return storeSelection;}

    public static void loadFXML(MenuOptions name, String fxml) {
        try {
                Node node = new FXMLLoader(ViewFactory.class.getResource(fxml)).load();
                name.setNode(node);
            }
         catch (IOException e) {
            
        }
    }
    public void showCombin(){
        Scene scene=new Scene(new AnchorPane(MenuOptions.COMBIN.getNode()));
        createStage(scene);
    }

    public void showLoginWindow(){
        Scene scene=new Scene(new AnchorPane(MenuOptions.LOGIN.getNode()));
        createStage(scene);
    }
    public void hideLogin(Stage login){
        login.hide();
    }
    public void createStage(Scene scene){
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}
