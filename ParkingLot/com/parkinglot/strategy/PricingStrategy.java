package com.parkinglot.strategy;

import com.parkinglot.model.SlotType;

public class PricingStrategy {
    
    // Hourly rates in whatever currency
    private static final double SMALL_RATE = 20.0;
    private static final double MEDIUM_RATE = 50.0;
    private static final double LARGE_RATE = 100.0;

    public double calculateAmount(SlotType slotType, long durationInSeconds) {
        // We use seconds here for easy testing simulation. In reality it would be durationInHours.
        // For simulation purposes: 1 second = 1 hour rate for demo, or actual calculation.
        // Let's assume the rate is applied per hour (duration in hours ceiling).
        double hours = Math.ceil(durationInSeconds / 3600.0);
        if (hours == 0) {
            hours = 1; // minimum 1 hour charge
        }
        
        return switch (slotType) {
            case SMALL -> hours * SMALL_RATE;
            case MEDIUM -> hours * MEDIUM_RATE;
            case LARGE -> hours * LARGE_RATE;
        };
    }
}
