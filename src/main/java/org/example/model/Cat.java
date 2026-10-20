package org.example.model;

public final class Cat extends Animal{
    public Cat(AnimalId id, String name, int age){
        super(id,name,age);
    }
    // overloaded constructor: the id is generated automatically

    public  Cat (String name,int age){
        this(new AnimalId(),name,age);
    }
    @Override
    public String getSpecies(){
        return "Cat";
    }
}
