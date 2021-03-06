package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteWindow implements Initializable {

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label latitude;

    @FXML
    private Label longitude;

    @FXML
    private Label owner;

    @FXML
    private ImageView image;

    Connection connection = Main.connection;






    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Place place = MainWindow.chosenPlace;

       id.setText(Integer.toString(place.getId()));
       name.setText(place.getName());
       latitude.setText(Double.toString(place.getLatitude()));
       longitude.setText(Double.toString(place.getLongitude()));
        owner.setText(Integer.toString(place.getOwner()));
        image.setImage((Image)place.getImage());

    }

    public void delete() throws IOException {
        Place place = MainWindow.chosenPlace;
        connection.deletePlace(place);
        Stage stage = (Stage)id.getScene().getWindow();
        MainWindow.refresh();
        stage.close();

    }

    public void cancel()
    {
        Stage stage = (Stage)id.getScene().getWindow();
        stage.close();
    }
}
