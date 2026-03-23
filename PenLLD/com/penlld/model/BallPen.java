package com.penlld.model;

public class BallPen extends Pen {
    private Refill defaultRefill;

    public BallPen(String brand, String name, Refill startingRefill) {
        super(brand, name);
        this.defaultRefill = startingRefill;
    }

    @Override
    public void write(String text) {
        if (!isOpen) {
            System.out.println(name + " is closed! You must start() it before writing.");
            return;
        }

        if (defaultRefill == null || defaultRefill.getInk().isEmpty()) {
            System.out.println("Cannot write. The pen " + name + " runs out of ink or has no refill.");
            return;
        }

        // Simulating ink reduction
        int inkRequired = text.length() / 2; // Arbitrary logic
        if (inkRequired == 0) inkRequired = 1;

        if (defaultRefill.getInk().getLevel() < inkRequired) {
            System.out.println("Not enough ink to finish writing the text. Please replace the refill.");
        } else {
            defaultRefill.getInk().reduceLevel(inkRequired);
            System.out.println("Writing: '" + text + "' (Ink remaining: " + defaultRefill.getInk().getLevel() + " units, Color: " + defaultRefill.getInk().getColor() + ")");
        }
    }

    @Override
    public void refill(Refill newRefill) {
        System.out.println("Replacing the refill of " + name + "...");
        this.defaultRefill = newRefill;
        System.out.println("Refill replaced successfully with color: " + newRefill.getInk().getColor());
    }
}
