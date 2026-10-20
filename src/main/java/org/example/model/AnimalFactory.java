package org.example.model;

import java.util.Locale;


public final class AnimalFactory {
    public  static  final String SUPPORTED_SPECIES="Dog,Cat,Bird,Rabbit";
    private  AnimalFactory(){

    }
    public  static Animal create(String species,String name,int age){
         if(species==null){
             throw  new IllegalArgumentException("Unknown spcies"+species);
         }
         return  switch (species.trim().toLowerCase(Locale.ROOT)){
             case "dog"-> new Dog(name,age);
             case "cat"-> new Cat(name,age);
             case "bird"-> new Bird(name,age);
             case "rabbit"-> new Rabbit(name,age);
             default -> throw new IllegalArgumentException("Unknown species:"+species);
         };
    }

}
