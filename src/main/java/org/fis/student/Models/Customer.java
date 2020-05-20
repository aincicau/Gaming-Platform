package org.fis.student.Models;

import java.util.ArrayList;

public class Customer {

    private String ID;
    private String password;
    private ArrayList<Game> games;
    private int credit;

    public Customer(String ID, String password, ArrayList<Game> games, int credit) {
        this.ID = ID;
        this.password = password;
        this.games = games;
        this.credit = credit;
    }

    public Customer() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        if(!ID.equals(customer.ID)) return false;
        if(!password.equals(customer.password)) return false;
        if(!games.equals(customer.games)) return false;
        return credit==customer.credit;
    }

    @Override
    public int hashCode() {
        int result=ID.hashCode();
        result = 31*result + password.hashCode();
        result = 31*result + games.hashCode();
        result = 31*result + credit;
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", games=" + games +
                ", credit=" + credit +
                '}';
    }
}
