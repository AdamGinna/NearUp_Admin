package sample;

import java.sql.Blob;


public class Place{

    private int id;
    private String Name;
    private int owner;
    private double longitude;
    private double latitude;
    private Blob image;
    private String Description;

    Place()
    {}


    Place(String name, int user, double lat, double lon)
    {
        Name = name;
        owner = user;
        latitude = lat;
        longitude = lon;

    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", User='" + owner + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", image=" + image +
                ", Description='" + Description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static int compare(Place p1,Place p2,double x,double y)
    {
        return (int)(Math.pow(p1.latitude-x,2)-Math.pow(p1.longitude-y,2)) - (int)(Math.pow(p2.latitude-x,2)-Math.pow(p2.longitude-y,2));
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int user) {
        owner = user;
    }
}
