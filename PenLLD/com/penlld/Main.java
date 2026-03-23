package com.penlld;

import com.penlld.model.BallPen;
import com.penlld.model.Ink;
import com.penlld.model.Nib;
import com.penlld.model.Refill;

public class Main {
    public static void main(String[] args) {
        // Create components for a Blue Refill
        Ink blueInk = new Ink("Blue", 100);
        Nib mediumNib = new Nib(0.5, "MEDIUM");
        Refill blueRefill = new Refill(blueInk, mediumNib);

        // Create a Pen
        BallPen myPen = new BallPen("Parker", "Vector Standard", blueRefill);

        // Attempt to write without starting
        myPen.write("Hello World!");

        System.out.println("\n--- Starting Pen ---");
        myPen.start();
        myPen.start(); // Try to start again

        System.out.println("\n--- Writing Text ---");
        myPen.write("Hello World! This is an LLD specific to Pen.");

        System.out.println("\n--- Closing Pen ---");
        myPen.close();
        myPen.write("Trying to write when closed...");

        System.out.println("\n--- Emptying Ink and Refilling ---");
        myPen.start();
        blueInk.reduceLevel(100); // Empty ink manually to test edge case
        myPen.write("Need ink!");

        Ink redInk = new Ink("Red", 100);
        Nib fineNib = new Nib(0.3, "FINE");
        Refill redRefill = new Refill(redInk, fineNib);
        
        myPen.refill(redRefill);
        myPen.write("This is a new red refill!");

        myPen.close();
    }
}
