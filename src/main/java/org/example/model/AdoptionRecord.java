package org.example.model;

import java.time.LocalDate;

public record AdoptionRecord(Animal animal, LocalDate adoptionDate, String adopterName) {

    // compact constructor: validation runs before the fields are set
    public AdoptionRecord {
        if (animal == null) {
            throw new IllegalArgumentException("Animal must not be null");
        }
        if (adoptionDate == null) {
            throw new IllegalArgumentException("Adoption date must not be null");
        }
        if (adopterName == null || adopterName.isBlank()) {
            throw new IllegalArgumentException("Adopter name must not be empty");
        }
        adopterName = adopterName.trim();
    }

    @Override
    public String toString() {
        return adoptionDate + " | " + animal.getName() + " (" + animal.getSpecies() + ") adopted by " + adopterName;
    }
}