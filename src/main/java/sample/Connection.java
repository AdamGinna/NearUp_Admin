package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection  {

    final private Socket socket = new Socket("localhost", 6789);


    Connection() throws IOException {


            for (int i = 0; i < 10; i++) {
                send("Moder");
                if (read().equals("ready"))
                    return;
            }
            throw new IOException();

    }

    public void send(String s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(s);
    }

    public String read() throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return br.readLine();

    }

    public boolean canRread() throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return br.ready();
    }


    public List<Place> getPlaces(double lat, double lon) throws IOException {
        JSONObject json = new JSONObject();
        json.put("latitude",lat);
        json.put("longitude",lon);
        send(json.toString());


        ArrayList<Place> places = new ArrayList<Place>();
        JSONObject jsonRead;
        while (canRread()) {
            jsonRead = new JSONObject(read());
            ObjectMapper mapper = new ObjectMapper();
            Place place = mapper.readValue(jsonRead.toString(), Place.class);
            places.add(place);
        }
        return places;
    }


    



}
