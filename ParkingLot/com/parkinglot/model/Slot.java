package com.parkinglot.model;

public class Slot {
    private String slotId;
    private SlotType slotType;
    private boolean isAvailable;
    private int floor;    // Used for distance calculation
    private int xCoord;   // Used for distance calculation
    private int yCoord;   // Used for distance calculation

    public Slot(String slotId, SlotType slotType, int floor, int xCoord, int yCoord) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isAvailable = true;
        this.floor = floor;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
