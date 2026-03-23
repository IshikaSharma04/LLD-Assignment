package com.parkinglot.strategy;

import com.parkinglot.model.Gate;
import com.parkinglot.model.Slot;
import com.parkinglot.model.SlotType;

import java.util.List;

public interface SlotAllocationStrategy {
    Slot findSlot(SlotType requestedType, Gate entryGate, List<Slot> allSlots);
}
