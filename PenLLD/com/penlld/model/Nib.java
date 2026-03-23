package com.penlld.model;

public class Nib {
    private double radius;
    private String type; // e.g., FINE, MEDIUM, BOLD

    public Nib(double radius, String type) {
        this.radius = radius;
        this.type = type;
    }

    public double getRadius() {
        return radius;
    }

    public String getType() {
        return type;
    }
}
