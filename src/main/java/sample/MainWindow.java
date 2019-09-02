package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindow implements Initializable,MapComponentInitializedListener {

    private Connection connection = Main.connection;


    public static Place choosenPlace;

    @FXML
    ComboBox comboBox;

    @FXML
    AnchorPane mapAnchorPane;

    @FXML
    GoogleMapView mapView;

    @FXML
    TableView<Place> table;

    @FXML
    TextField searchField;

    private GoogleMap map;


    private ObservableList<Place> dataPlaces =
            FXCollections.observableArrayList(
                    new Place("Jacob", 4, 45, 56.4),
                    new Place("Isabella", 2, 45, 56.4),
                    new Place("Ethan", 2, 45, 56.4),
                    new Place("Emma", 1, 45, 56.4),
                    new Place("Michael", 3, 45, 56.4)
            );

    private ObservableList<User> dataUsers =
            FXCollections.observableArrayList();

    public void mapInitialized() {


        MapOptions mapOptions = new MapOptions();
        Place place = table.getItems().get(0);

        mapOptions.center(new LatLong(place.getLatitude(), place.getLongitude()))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);


        map = mapView.createMap(mapOptions);


        /*
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(place.getLatitude(), place.getLongitude()) )
                .visible(Boolean.TRUE)
                .title(place.getName());

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);

         */

    }


    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Places",
                        "Users",
                        "Rating",
                        "Visit"
                );
        comboBox.getItems().setAll(options);
        comboBox.setValue(options.get(0));

        try {
            //data = (ObservableList<Place>) connection.getPlaces(53.0,18.0);
            dataPlaces.setAll(connection.getPlaces(53, 18.0));

        } catch (IOException e) {
            e.printStackTrace();
        }


        places();
        //mapView = new GoogleMapView("en-EU", "AIzaSyDadrh9UvRxeRx_0qGbNGB8KfbEzIiQqC0");


        mapView.addMapInializedListener(this);

    }

    public void editClicked() {
        int index = table.getSelectionModel().selectedIndexProperty().get();
        if (index < 0)
            return;
        choosenPlace = table.getItems().get(index);

        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("EditWindow.fxml"));

            stage.setTitle("AdminNearUp Edit");
            stage.setScene(new Scene(root, 900, 620));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectPlace() {
        int index = table.getSelectionModel().selectedIndexProperty().get();
        if (index < 0)
            return;

        Place place = table.getItems().get(index);
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


        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(new LatLong(place.getLatitude(), place.getLongitude()))
                .visible(Boolean.TRUE)
                .title(place.getName());

        Marker marker = new Marker(markerOptions);

        map.addMarker(marker);


    }

    public void search() {

        final String s = searchField.getText();
        List<Place> list= table.getItems().stream().filter((p) -> Integer.toString(p.getId()).equals(s)).collect(Collectors.toList());
        ObservableList<Place> listP = FXCollections.observableArrayList(list);
        table.setItems(listP);
    }


    public void places() {
        TableColumn idCol = new TableColumn();
        idCol.setText("id");
        idCol.setPrefWidth(20);
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn firstNameCol = new TableColumn();
        firstNameCol.setText("Name");
        firstNameCol.setMinWidth(200);
        firstNameCol.setPrefWidth(400);
        firstNameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        TableColumn lastNameCol = new TableColumn();
        lastNameCol.setText("User");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory("owner"));
        TableColumn latCol = new TableColumn();
        latCol.setText("latitude");
        latCol.setMinWidth(100);
        latCol.setCellValueFactory(new PropertyValueFactory("latitude"));
        TableColumn lonCol = new TableColumn();
        lonCol.setText("longitude");
        lonCol.setMinWidth(100);
        lonCol.setCellValueFactory(new PropertyValueFactory("longitude"));
        table.setItems(dataPlaces);
        table.getColumns().setAll(idCol, firstNameCol, lastNameCol, latCol, lonCol);
    }

    public void comboBoxSelected()
    {
        String string = (String)comboBox.getValue();
        if(string.equals("Places"))
            places();
        //else if(string.equals("Users"))


    }

}