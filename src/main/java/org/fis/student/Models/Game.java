package org.fis.student.Models;

import java.util.Objects;

public class Game {
    private String name;
    private int price;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;

        if(!game.name.equals(name)) return false;
        return price==game.price;
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
                '}';
    }
}
