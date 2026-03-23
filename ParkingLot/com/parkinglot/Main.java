package com.parkinglot;

import com.parkinglot.model.*;
import com.parkinglot.service.ParkingService;
import com.parkinglot.strategy.NearestAvailableSlotStrategy;
import com.parkinglot.strategy.PricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Setup
        List<Gate> gates = new ArrayList<>();
        gates.add(new Gate("Gate-1", 1, 0, 0));   // Coordinate 0,0
        gates.add(new Gate("Gate-2", 1, 100, 0)); // Coordinate 100,0

        List<Slot> slots = new ArrayList<>();
        slots.add(new Slot("S1", SlotType.SMALL, 1, 10, 0)); // nearest to Gate-1
        slots.add(new Slot("S2", SlotType.SMALL, 1, 90, 0)); // nearest to Gate-2
        slots.add(new Slot("M1", SlotType.MEDIUM, 1, 20, 0)); // nearest to Gate-1
        slots.add(new Slot("L1", SlotType.LARGE, 1, 30, 0)); // nearest to Gate-1

        ParkingService parkingService = new ParkingService(
                slots, gates, new NearestAvailableSlotStrategy(), new PricingStrategy()
        );

        System.out.println("--- Initial Parking Lot Status ---");
        printStatus(parkingService.status());

        // Test 1: Park 2-Wheeler via Gate-1 in SMALL slot
        System.out.println("\n--- Park a 2-Wheeler in SMALL slot via Gate-1 ---");
        Vehicle bike1 = new Vehicle("BH-1111", VehicleType.TWO_WHEELER);
        ParkingTicket ticket1 = parkingService.park(bike1, LocalDateTime.now().minusHours(2), SlotType.SMALL, "Gate-1");
        System.out.println("Parked Vehicle: " + bike1.getLicensePlate() + " at Slot: " + ticket1.getAllocatedSlot().getSlotId());

        // Test 2: Park 2-Wheeler via Gate-2 in MEDIUM slot (Testing flexible allocation)
        System.out.println("\n--- Park a 2-Wheeler in MEDIUM slot via Gate-2 ---");
        Vehicle bike2 = new Vehicle("BH-2222", VehicleType.TWO_WHEELER);
        ParkingTicket ticket2 = parkingService.park(bike2, LocalDateTime.now().minusHours(1), SlotType.MEDIUM, "Gate-2");
        System.out.println("Parked Vehicle: " + bike2.getLicensePlate() + " at Slot: " + ticket2.getAllocatedSlot().getSlotId());

        System.out.println("\n--- Parking Lot Status After Entries ---");
        printStatus(parkingService.status());

        // Test 3: Exit 2-Wheeler parked in SMALL slot
        System.out.println("\n--- Exit Vehicle " + bike1.getLicensePlate() + " ---");
        Bill bill1 = parkingService.exit(ticket1, LocalDateTime.now());
        System.out.println("Bill Amount for SMALL slot (approx 2 hrs): " + bill1.getAmount());

        // Test 4: Exit 2-Wheeler parked in MEDIUM slot (Should be billed as MEDIUM)
        System.out.println("\n--- Exit Vehicle " + bike2.getLicensePlate() + " ---");
        Bill bill2 = parkingService.exit(ticket2, LocalDateTime.now());
        System.out.println("Bill Amount for MEDIUM slot (approx 1 hr): " + bill2.getAmount());

        System.out.println("\n--- Parking Lot Status After Exits ---");
        printStatus(parkingService.status());
    }

    private static void printStatus(Map<SlotType, Integer> statusMap) {
        for (Map.Entry<SlotType, Integer> entry : statusMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
    }
}
