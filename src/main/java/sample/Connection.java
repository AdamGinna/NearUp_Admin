package sample;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection  {

    final private Socket socket = new Socket("localhost", 6789);

    private BufferedReader br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    Connection() throws IOException {


            for (int i = 0; i < 10; i++) {
                send("awd&&3");
                String string = read();
                if (string.equals("ready")) {
                    System.out.println("connected");
                    return;
                }
            }
            throw new IOException();


    }

    private void send(String s) throws IOException {

        bw.write(s);
        bw.newLine();
        bw.flush();
    }

    private String read() throws IOException {

             return br.readLine();

    }



    public List<Place> getPlaces(double lat, double lon) throws IOException {
        /*
        JSONObject json = new JSONObject();
        json.put("latitude",lat);
        json.put("longitude",lon);
        send("places");
        send( json.toString());
        */
        send("places");
        send(lat +" "+ lon );


        ArrayList<Place> places = new ArrayList<Place>();
        String line;
        while (!(line = read()).equals("*End*")) {
            ObjectMapper mapper = new ObjectMapper();
            Place place = mapper.readValue(line, Place.class);
            places.add(place);
        }
        return places;
    }

    public void editPlace(Place place) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(place);
        send("editPlace");
        send(s);

    }

    public List<User> getUsers() throws IOException {

            send("users");


        ArrayList<User> users = new ArrayList<User>();
        String line;
        while (!(line = read()).equals("*End*")) {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(line, User.class);
            users.add(user);
        }
        return users;
    }

    public void deletePlace(Place place) throws IOException {
        send("deletePlace");
        ObjectMapper mapper = new ObjectMapper();
        String  s = mapper.writeValueAsString(place);
        send(s);
    }


    public void deleteUser(User user) throws IOException {
        send("deleteUser");
        ObjectMapper mapper = new ObjectMapper();
        String  s = mapper.writeValueAsString(user);
        send(s);
    }
}
