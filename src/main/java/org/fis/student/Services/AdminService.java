package org.fis.student.Services;

import org.fis.student.Models.Admin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class AdminService {
    private static ArrayList<Admin> a = new ArrayList<>();

    public static void loadAdmins(){
        try{
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader("src/main/resources/Admins.json");
            Object obj = jp.parse(fr);
            JSONArray ja = (JSONArray)obj;

            for(Object admin:ja){
                JSONObject o = (JSONObject) admin;
                String id = o.get("username").toString();
                String pass = o.get("password").toString();
                a.add(new Admin(id,pass));
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static boolean checkCredentials(String id, String pass){
        boolean found = false;

        for(Admin i:a){
            if(i.getUsername().equals(id)&&i.getPassword().equals(pass)){
                found = true;break;
            }
        }

        return found;
    }
}
