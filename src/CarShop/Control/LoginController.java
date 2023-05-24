package CarShop.Control;

import CarShop.Constants.MenuOptions;
import CarShop.DBConnection.DBHandler;
import CarShop.Model.Model;
import CarShop.Constants.Hawks;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ImageView logoImg;
    @FXML
    private HBox img1,img2,img3,img4,img5,img6,imgPane,hBox;
    //Login
    @FXML
    private TextField username,password;
    @FXML
    private Button loginBtn;
    @FXML
    private Label emailLbl,passwordLbl, heading,text1,text2;
    @FXML
    private VBox loginPane;
    @FXML
    private Label enrollLbl;
    @FXML
    private Label warningLbl;
    //SignUp
    @FXML
    private TextField fname,lname;
    @FXML
    private TextField signupEmail,signupPass;
    @FXML
    private Button signupBtn;
    @FXML
    private VBox signupPane;
    @FXML
    private Label loginLbl;
    @FXML
    private Label fnameLbl,lnameLbl, emailLbl2,passwordLbl2;
    //Admin section

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement st;
    private String passwordStr,temp;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createHawks();
        handler= new DBHandler();
        startSignup();
        startLogin();
    }
    private void allowLogin(){
        int accountID= getUserIdByUsername(username.getText());
        Model.getInstance().getAccount().setAccountID(accountID);
        if(!isAccountIdExisted(accountID)){
            insertAccountDB(accountID);
        }else{
            Model.getInstance().getAccount().setBalance(getBalanceByUserID(accountID));
        }
        Model.getInstance().getView().showCombin();
    }
    private void startLogin(){
        animateLoginFields();
        //loginBtn.setOnAction(e-> showLogin());
        loginBtn.setOnAction(e->{
            if(isValid(username.getText(),password.getText())){
                allowLogin();
            }
            else{
                warningLbl.setVisible(true);
                Model.getInstance().getLoginAnimation().vibingImg(warningLbl);
            }
        });
        loginLbl.setOnMouseClicked(e->{
            signupPane.setVisible(false);
            Model.getInstance().getLoginAnimation().vibingImg(logoImg);
        });
    }
    private boolean isValid(String username,String password){
        System.out.println(2);
        String select = "SELECT * FROM users WHERE username = ? AND password = ?";
        connection=handler.getConnection();
        try {
            st = connection.prepareStatement(select);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet resultSet = st.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void insertAccountDB(int id){
        String insert="INSERT INTO Account(user_id,balance,portfolio_val)"+"VALUES(?,0,0)";
        connection = handler.getConnection();
        try {
            st=connection.prepareStatement(insert);
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void insert(){
        String insert="INSERT INTO Users(first_name,last_name,username,password)"+"VALUES(?,?,?,?)";
        connection = handler.getConnection();
        try {
            st=connection.prepareStatement(insert);
            st.setString(1,fname.getText());
            st.setString(2,lname.getText());
            st.setString(3,signupEmail.getText());
            st.setString(4,signupPass.getText());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    private boolean isAccountIdExisted(int accountId) {
        connection=handler.getConnection();
        try {
            String query = "SELECT COUNT(*) FROM Account WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public double getBalanceByUserID(int id) {
        double balance =0;
        String select = "SELECT balance FROM Account WHERE user_id = ?";
        connection=handler.getConnection();
        try  {
            st = connection.prepareStatement(select);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }

    public int getUserIdByUsername(String username) {
        int userId =-1;
        String select = "SELECT user_id FROM Users WHERE username = ?";
        connection=handler.getConnection();
        try  {
            st = connection.prepareStatement(select);
            st.setString(1, username);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    private void startSignup(){
        animateSignupFields();
        enrollLbl.setOnMouseClicked(e->{
            signupPane.setVisible(true);
            Model.getInstance().getLoginAnimation().vibingImg(logoImg);
        });
        signupBtn.setOnAction(e->{
            signupPane.setVisible(false);
            insert();
        });
    }
    private void animateSignupFields(){
        fname.setOnMouseClicked(e->Model.getInstance().getLoginAnimation().moveUpLbl(fnameLbl));
        lname.setOnMouseClicked(e->Model.getInstance().getLoginAnimation().moveUpLbl(lnameLbl));
        signupEmail.setOnMouseClicked(e->Model.getInstance().getLoginAnimation().moveUpLbl(emailLbl2));
        signupPass.setOnMouseClicked(e->Model.getInstance().getLoginAnimation().moveUpLbl(passwordLbl2));
        hBox.setOnMouseClicked(e-> {
            Model.getInstance().getLoginAnimation().textUnFocused(hBox,emailLbl2,passwordLbl2);
        });
    }
    private void animateLoginFields(){
        username.setOnMouseClicked(e->Model.getInstance().getLoginAnimation().moveUpLbl(emailLbl));
        password.setOnMouseClicked(e->Model.getInstance().getLoginAnimation().moveUpLbl(passwordLbl));
        hBox.setOnMouseClicked(e-> Model.getInstance().getLoginAnimation().textUnFocused(hBox,emailLbl,passwordLbl));
    }
    private void createHawks(){
         Hawks.createHawks(img1,img2,img3,img4,img5,img6,heading,text1,text2);
    }
    private void showLogin(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,e->{
                    Model.getInstance().getTransition().loginSuccessful(imgPane);
                }),
                new KeyFrame(Duration.seconds(1),e->{
                    Model.getInstance().getLoginAnimation().animateHawk();
                }),
                new KeyFrame(Duration.seconds(7.5),e->{
                    Model.getInstance().getView().hideLogin((Stage) loginBtn.getScene().getWindow());
                    Model.getInstance().getView().showCombin();
                })
        );
        timeline.play();
    }



}
