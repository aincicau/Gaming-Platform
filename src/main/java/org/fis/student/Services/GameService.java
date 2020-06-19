package org.fis.student.Services;

import org.fis.student.Models.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class GameService {
    private static String path = null;
    private static ArrayList<Game> g = new ArrayList<>();

    public static void loadGames() {
        try {
            g=new ArrayList<>();
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader(path);
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

    public static void writeGames(){
        FileWriter fw = null;
        try{
            fw = new FileWriter(path);

            JSONArray ja = new JSONArray();

            for(Game i:g){
                JSONObject jo = new JSONObject();
                jo.put("name",i.getName());
                jo.put("price",String.valueOf(i.getPrice()));

                ja.add(jo);
            }

            fw.write(ja.toJSONString());
        }catch(Exception e){
            System.out.println(e);
        }finally {
            try{
                fw.flush();
                fw.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void setPath(String path) {
        GameService.path = path;
    }
}
