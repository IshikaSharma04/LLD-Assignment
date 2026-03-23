package com.penlld.model;

public abstract class Pen {
    protected String brand;
    protected String name;
    protected boolean isOpen;

    public Pen(String brand, String name) {
        this.brand = brand;
        this.name = name;
        this.isOpen = false;
    }

    public void start() { // equivalent to open/uncap
        if (isOpen) {
            System.out.println(name + " is already started/opened.");
        } else {
            isOpen = true;
            System.out.println(name + " starts (opened).");
        }
    }

    public void close() { // equivalent to cap
        if (!isOpen) {
            System.out.println(name + " is already closed.");
        } else {
            isOpen = false;
            System.out.println(name + " closed.");
        }
    }

    public abstract void write(String text);
    
    public abstract void refill(Refill refill);
    
    public boolean isOpen() {
        return isOpen;
    }
}
