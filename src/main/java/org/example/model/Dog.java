package org.example.model;

public final class Dog extends Animal{
    public Dog(AnimalId id, String name, int age){
        super(id,name,age);
    }
    // overloaded constructor: the id is generated automatically
    public  Dog(String name,int age){
        this(new AnimalId(),name,age);
    }


    @Override
    public String getSpecies(){
        return "Dog";
    }
}
