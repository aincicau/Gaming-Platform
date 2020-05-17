package org.fis.student.Services;

import org.fis.student.Models.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class CustomerService {
    private static ArrayList<Customer> c = new ArrayList<>();

    public static void loadCustomers(){
        try{
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader("src/main/resources/Customers.json");
            Object obj = jp.parse(fr);
            JSONArray ja = (JSONArray)obj;

            for(Object customer:ja){
                JSONObject o = (JSONObject) customer;
                String id = o.get("username").toString();
                String pass = o.get("password").toString();
                c.add(new Customer(id,pass));
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static boolean checkCredentials(String id, String pass){
        boolean found = false;

        for(Customer i:c){
            if(i.getID().equals(id)&&i.getPassword().equals(pass)){
                found = true;break;
            }
        }

        return found;
    }
}
