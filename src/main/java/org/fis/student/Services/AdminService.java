package org.fis.student.Services;

import org.fis.student.Models.Admin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Base64;

public class AdminService {
    private static String path = null;
    private static ArrayList<Admin> a = new ArrayList<>();

    public static void loadAdmins(){
        try{
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader(path);
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

    public static String encodePassword(String password){
        String result = Base64.getEncoder().encodeToString(password.getBytes());

        return result;
    }

    public static void writeAdmins() {
        FileWriter fw = null;
        try{
            fw = new FileWriter(path);

            JSONArray ja = new JSONArray();

            for(Admin i:a){
                JSONObject jo = new JSONObject();
                jo.put("username",i.getUsername());
                jo.put("password",i.getPassword());

                ja.add(jo);
            }

            fw.write(ja.toJSONString());

        }catch(Exception e){

        }finally {
            try{
                fw.flush();
                fw.close();
            }catch (Exception e){
                System.out.println(e);
            }

        }
    }

    public static ArrayList<Admin> getA() {
        return a;
    }

    public static void setPath(String path) {
        AdminService.path = path;
    }
}
