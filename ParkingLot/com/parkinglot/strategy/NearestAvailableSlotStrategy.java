package com.parkinglot.strategy;

import com.parkinglot.model.Gate;
import com.parkinglot.model.Slot;
import com.parkinglot.model.SlotType;

import java.util.List;

public class NearestAvailableSlotStrategy implements SlotAllocationStrategy {

    @Override
    public Slot findSlot(SlotType requestedType, Gate entryGate, List<Slot> allSlots) {
        Slot nearestSlot = null;
        int minDistance = Integer.MAX_VALUE;

        for (Slot slot : allSlots) {
            if (slot.isAvailable() && slot.getSlotType() == requestedType) {
                // Determine distance (Simple Manhattan distance for simulation)
                int distance = Math.abs(slot.getFloor() - entryGate.getFloor()) * 10 
                               + Math.abs(slot.getxCoord() - entryGate.getxCoord())
                               + Math.abs(slot.getyCoord() - entryGate.getyCoord());
                               
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestSlot = slot;
                }
            }
        }
        return nearestSlot;
    }
}
