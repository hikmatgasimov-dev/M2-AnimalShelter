package org.example.model;

public final class Bird extends Animal{
    public Bird(AnimalId id, String name, int age){
        super(id,name,age);
    }


    // overloaded constructor: the id is generated automatically
    public  Bird (String name,int age){
        this(new AnimalId(),name,age);
    }

    @Override
    public String getSpecies(){
        return "Bird";
    }
}
