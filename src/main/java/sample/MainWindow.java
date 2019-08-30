package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable,MapComponentInitializedListener {

    Connection connection;

    @FXML
    AnchorPane mapAnchorPane;

    @FXML
    GoogleMapView mapView;

    @FXML
    TableView<Place> places;

    private GoogleMap map;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();

    private final ObservableList<Place> data =
            FXCollections.observableArrayList(
                    new Place("Jacob", "Smith", 45,56.4),
                    new Place("Isabella", "Johnson", 45,56.4),
                    new Place("Ethan", "Williams", 45,56.4),
                    new Place("Emma", "Jones", 45,56.4),
                    new Place("Michael", "Brown", 45,56.4)
            );


    public void initialize() throws IOException {
        //MapOptions gm = new MapOptions();
        //gm.center(new LatLong(53.009994, 18.594020));


    }



    public void mapInitialized() {


        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);



        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);

    }


    public void initialize(URL location, ResourceBundle resources) {

        TableColumn firstNameCol = new TableColumn();
        firstNameCol.setText("Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        TableColumn lastNameCol = new TableColumn();
        lastNameCol.setText("User");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory("User"));
        TableColumn latCol = new TableColumn();
        latCol.setText("lat");
        latCol.setMinWidth(100);
        latCol.setCellValueFactory(new PropertyValueFactory("latitude"));
        TableColumn lonCol = new TableColumn();
        lonCol.setText("lon");
        lonCol.setMinWidth(100);
        lonCol.setCellValueFactory(new PropertyValueFactory("longitude"));
        places.setItems(data);
        places.getColumns().setAll(firstNameCol,lastNameCol,latCol,lonCol);
        //mapView = new GoogleMapView("en-EU", "AIzaSyDadrh9UvRxeRx_0qGbNGB8KfbEzIiQqC0");

        mapView.addMapInializedListener(this);

    }


}
