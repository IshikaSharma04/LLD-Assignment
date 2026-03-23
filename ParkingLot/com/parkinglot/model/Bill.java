package com.parkinglot.model;

import java.time.LocalDateTime;

public class Bill {
    private String billId;
    private ParkingTicket ticket;
    private LocalDateTime exitTime;
    private double amount;

    public Bill(String billId, ParkingTicket ticket, LocalDateTime exitTime, double amount) {
        this.billId = billId;
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public String getBillId() {
        return billId;
    }

    public ParkingTicket getTicket() {
        return ticket;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getAmount() {
        return amount;
    }
}
