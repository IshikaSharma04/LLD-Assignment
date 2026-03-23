package com.parkinglot.service;

import com.parkinglot.model.*;
import com.parkinglot.strategy.PricingStrategy;
import com.parkinglot.strategy.SlotAllocationStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ParkingService {
    private List<Slot> slots;
    private List<Gate> gates;
    private SlotAllocationStrategy allocationStrategy;
    private PricingStrategy pricingStrategy;
    private Map<String, ParkingTicket> activeTickets;

    public ParkingService(List<Slot> slots, List<Gate> gates, 
                          SlotAllocationStrategy allocationStrategy, 
                          PricingStrategy pricingStrategy) {
        this.slots = slots;
        this.gates = gates;
        this.allocationStrategy = allocationStrategy;
        this.pricingStrategy = pricingStrategy;
        this.activeTickets = new HashMap<>();
    }

    public ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime, SlotType requestedSlotType, String entryGateID) {
        if (!isCompatible(vehicle.getVehicleType(), requestedSlotType)) {
            throw new IllegalArgumentException("Vehicle type " + vehicle.getVehicleType() + " cannot park in " + requestedSlotType + " slot.");
        }

        Gate entryGate = gates.stream()
                .filter(g -> g.getGateId().equals(entryGateID))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Gate ID: " + entryGateID));

        Slot allocatedSlot = allocationStrategy.findSlot(requestedSlotType, entryGate, slots);

        if (allocatedSlot == null) {
            throw new RuntimeException("No available slot found for " + requestedSlotType);
        }

        allocatedSlot.setAvailable(false);

        String ticketId = "TKT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, allocatedSlot, entryTime);

        activeTickets.put(ticketId, ticket);

        return ticket;
    }

    public Map<SlotType, Integer> status() {
        Map<SlotType, Integer> availability = new HashMap<>();
        for (SlotType type : SlotType.values()) {
            availability.put(type, 0);
        }

        for (Slot slot : slots) {
            if (slot.isAvailable()) {
                availability.put(slot.getSlotType(), availability.get(slot.getSlotType()) + 1);
            }
        }
        return availability;
    }

    public Bill exit(ParkingTicket parkingTicket, LocalDateTime exitTime) {
        if (parkingTicket == null || !activeTickets.containsKey(parkingTicket.getTicketId())) {
            throw new IllegalArgumentException("Invalid or already processed ticket.");
        }

        long durationInSeconds = Duration.between(parkingTicket.getEntryTime(), exitTime).getSeconds();

        if (durationInSeconds < 0) {
            throw new IllegalArgumentException("Exit time cannot be before entry time.");
        }

        double amount = pricingStrategy.calculateAmount(parkingTicket.getAllocatedSlot().getSlotType(), durationInSeconds);

        // Free the slot
        parkingTicket.getAllocatedSlot().setAvailable(true);

        activeTickets.remove(parkingTicket.getTicketId());

        String billId = "BILL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return new Bill(billId, parkingTicket, exitTime, amount);
    }

    private boolean isCompatible(VehicleType vType, SlotType sType) {
        switch (vType) {
            case TWO_WHEELER:
                return true; // Can park anywhere
            case CAR:
                return sType == SlotType.MEDIUM || sType == SlotType.LARGE;
            case BUS:
                return sType == SlotType.LARGE;
            default:
                return false;
        }
    }
}
