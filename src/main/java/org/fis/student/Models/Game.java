package org.fis.student.Models;

public class Game {
    private String name;
    private int price;
    private boolean bought = false;

    public Game(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Game() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;

        if(!game.name.equals(name)) return false;
        if(price!=game.price) return false;
        return bought==game.bought;
    }

    @Override
    public int hashCode() {
        return 31*name.hashCode() + price;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", bought=" + bought +
                '}';
    }
}
