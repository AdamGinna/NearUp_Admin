package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditWindow implements Initializable, MapComponentInitializedListener {
    private Connection connection = Main.connection;

    Place place;

    @FXML
    GoogleMapView mapView;
    GoogleMap map;

    @FXML
    AnchorPane editWindow;

    @FXML
    ImageView image;

    @FXML
    TextField name;

    @FXML
    TextField owner;

    @FXML
    TextField latitude;

    @FXML
    TextField longitude;

    @FXML
    TextArea description;



    public void initialize(URL location, ResourceBundle resources) {

        place = MainWindow.chosenPlace;



        image.setImage((Image) place.getImage());

        name.setText(place.getName());

        owner.setText(Integer.toString(place.getOwner()));

        latitude.setText(Double.toString(place.getLatitude()));

        longitude.setText(Double.toString(place.getLongitude()));

        description.setText(place.getDescription());



        longitude.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*.\\d*")) {
                    longitude.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });

        latitude.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*.\\d*")) {
                    latitude.setText(newValue.replaceAll("[^\\d.]", ""));
                }
            }
        });
    }

    public void cancel()
    {

        Stage stage = (Stage)editWindow.getScene().getWindow();
        stage.close();
    }


    public void edit()
    {
        Place place1 = this.place;
        place1.setName(name.getText());
        place1.setLatitude(Double.parseDouble(latitude.getText()));
        place1.setLongitude(Double.parseDouble(longitude.getText()));
        place1.setDescription(description.getText());


        try {
            connection.editPlace(place1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainWindow.refresh();
        Stage stage = (Stage)editWindow.getScene().getWindow();
        stage.close();
    }


    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(place.getLatitude(), place.getLongitude()))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(16);

        map = mapView.createMap(mapOptions);
    }



    public void mapAction()
    {
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText())))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(16);


        map = mapView.createMap(mapOptions);



        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText())) )
                .visible(Boolean.TRUE)
                .title(place.getName());

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);



    }



}
