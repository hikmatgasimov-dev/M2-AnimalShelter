package org.example.shelter;

import org.example.model.Animal;

import java.util.*;
import java.util.stream.Collectors;


public final class AnimalStats {

    private AnimalStats() {
            }

    // empty if the collection is empty, so we never divide by zero
    public static <T extends Animal> double averageAge(List<T> animals) {
       if(animals.isEmpty()){
           return 0;
       }
       int total=0;
       for(T animal:animals){
           total+=animal.getAge();

       }
       return (double) total/animals.size();
    }

    public static <T extends Animal> T oldest(List<T> animals) {
        T oldest=null;
        for (T animal :animals){
            if(oldest==null||animal.getAge()>oldest.getAge()){
                oldest=animal;
            }
        }
        return  oldest;
    }

    // species name -> number of animals, sorted by species name
    public static <T extends Animal> int countBySpecies(List<T>animals,String species) {
       int count=0;
       for (T animal:animals){
           if(animal.getSpecies().equalsIgnoreCase(species)){
             count++;
           }
       }
       return  count;
    }
}
