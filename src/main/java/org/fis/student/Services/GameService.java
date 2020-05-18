package org.fis.student.Services;

import org.fis.student.Models.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class GameService {
    private static ArrayList<Game> g = new ArrayList<>();

    public static void loadGames() {
        try {
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader("src/main/resources/Games.json");
            Object obj = jp.parse(fr);
            JSONArray ja = (JSONArray) obj;

            for(Object game:ja){
                JSONObject jo = (JSONObject) game;
                g.add(new Game(jo.get("name").toString(), Integer.parseInt(jo.get("price").toString())));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static ArrayList<Game> getG() {
        return g;
    }
}
