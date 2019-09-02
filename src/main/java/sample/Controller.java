package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;



public class Controller {



    @FXML
    AnchorPane login;

    @FXML
    Label waring;

    @FXML
    PasswordField Password;

    @FXML
    TextField Login;

    @FXML
    void Login() throws IOException {

        if(Login.getText().equals("admin") && Password.getText().equals("admin"))
        {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainWindow.fxml"));
        stage.setTitle("AdminNearUp");
        stage.setScene(new Scene(root, 1200, 600));
        stage.show();
        stage = (Stage) login.getScene().getWindow();
        stage.close();

        }
        else
        {
            waring.setText("bad password or login");
        }
    }

}
