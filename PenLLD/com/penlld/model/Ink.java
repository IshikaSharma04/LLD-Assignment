package com.penlld.model;

public class Ink {
    private String color;
    private int level;

    public Ink(String color, int level) {
        this.color = color;
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

    public void reduceLevel(int amount) {
        this.level = Math.max(0, this.level - amount);
    }

    public boolean isEmpty() {
        return level == 0;
    }
}
