package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteWindowUser implements Initializable {

    Connection connection = Main.connection;

    @FXML
    private Label name;

    @FXML
    private Label id;

    @FXML
    private Label mail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        User user= MainWindow.chosenUser;

        id.setText(Integer.toString(user.getId()));
        name.setText(user.getNick());
        mail.setText(user.getNick());

    }

    public void delete() throws IOException {
        User user= MainWindow.chosenUser;
        connection.deleteUser(user);
        MainWindow.refresh();
        Stage stage = (Stage)id.getScene().getWindow();
        stage.close();

    }

    public void cancel()
    {
        Stage stage = (Stage)id.getScene().getWindow();
        stage.close();
    }
}
