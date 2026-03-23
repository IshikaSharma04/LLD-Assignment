package com.parkinglot.model;

public class Gate {
    private String gateId;
    private int floor;    // Used for distance calculation
    private int xCoord;   // Used for distance calculation
    private int yCoord;   // Used for distance calculation

    public Gate(String gateId, int floor, int xCoord, int yCoord) {
        this.gateId = gateId;
        this.floor = floor;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
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
