package org.example.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public sealed abstract class Animal permits Dog,Cat,Bird {
    private final AnimalId id;
    private String name;
    private int age;
    private AdoptionStatus adoptionStatus;

    protected Animal(AnimalId id, String name, int age) {
        Objects.requireNonNull(id,"Animal must not be null");

        if(name==null||name.isBlank()){
            throw new IllegalArgumentException("Name must not be empty");
        }
        if(age<0){
            throw new IllegalArgumentException("Age must not be negative");
        }
        this.id = id;
        this.name = name.trim();
        this.age = age;
        this.adoptionStatus = AdoptionStatus.AVAILABLE;
    }

    public void markAsAdopted(){
        this.adoptionStatus = AdoptionStatus.ADOPTED;
    }

    public abstract String getSpecies();

    @Override
    public String toString(){
        return id + " | " + name + " | " + age + " years old | " + getSpecies() + " | " + adoptionStatus;
    }
}
