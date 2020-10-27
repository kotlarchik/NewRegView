package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ConnectionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControllerWindRegistration {
    Connection connection;

    public ControllerWindRegistration() { connection = ConnectionUtil.connectionDB();}

    @FXML
    private TextField txtNameReg;
    @FXML
    private TextField txtLastnameReg;
    @FXML
    private TextField txtEmailReg;
    @FXML
    private TextField txtLoginReg;
    @FXML
    private PasswordField txtPassReg;
    @FXML
    private Label txtStatusReg;
    @FXML
    private Button buttonIn2ToWind1;

    @FXML
    void initialize(){
        buttonIn2ToWind1.setOnAction(event -> {
            buttonIn2ToWind1.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/FXML/WindowSignIn.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Sign In, please.");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
    @FXML
    public void pressRegBD(ActionEvent event) throws SQLException {
        String Insert = "INSERT INTO users(firstName, lastName, email, login, password) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(Insert);
        statement.setString(1, txtNameReg.getText());
        statement.setString(2, txtLastnameReg.getText());
        statement.setString(3, txtEmailReg.getText());
        statement.setString(4, txtLoginReg.getText());
        statement.setString(5, txtPassReg.getText());

        int result = statement.executeUpdate();

        if (result == 1){
            txtStatusReg.setText("Registration was successful");
        }
    }
}