package org.example.model;

public final class Rabbit  extends Animal{


    public  Rabbit(AnimalId id,String name,int age){
        super(id,name,age);
    }

    //overlaoded constroctor:the id is generated automatically
    public Rabbit (String name,int age){
         this(new AnimalId(),name,age);
    }

    @Override
    public String getSpecies(){
        return "Rabbit";
    }
}
