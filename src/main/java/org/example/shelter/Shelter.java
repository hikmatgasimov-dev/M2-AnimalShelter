package org.example.shelter;


import org.example.model.AdoptionRecord;
import java.time.LocalDate;
import org.example.model.AdoptionStatus;
import org.example.model.Animal;
import org.example.model.AnimalId;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();
    private final List<AdoptionRecord> adoptionHistory = new ArrayList<>();
    public void addAnimal(T animal){
        animals.add(animal);
    }

    // returns a copy, so outside code cannot change the shelter's own list
    public List<T> getAllAnimals(){

        return List.copyOf(animals);
    }

    public List<T> findBySpecies(String species){
        if(species==null){
            return  List.of();
        }
        String wanted=species.trim();
        return  animals.stream()
                .filter(animal->animal.getSpecies().equalsIgnoreCase(wanted))
                .toList();
    }

    public List<T> findAvailableAnimals(){

        return animals.stream()
                .filter(animal->animal.getAdoptionStatus()== AdoptionStatus.AVAILABLE)
                .toList();
    }

    public List<T>getSortedAnimals(Comparator<? super T>comparator){

        return  animals.stream()
                .sorted(comparator)
                .toList();
    }

    public void markAsAdopted(String id,String adopterName){
        AnimalId wantedId=new AnimalId(id);
        T animal=animals.stream()
                .filter(a->a.getId().equals(wantedId))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("No animal with id "+id));

        if(animal.getAdoptionStatus()==AdoptionStatus.ADOPTED){
            throw new IllegalArgumentException(animal.getName()+" is already adaopted");
        }

 // create the record first: if the adopter name is invalid, the animal stays available
        AdoptionRecord record = new AdoptionRecord(animal, LocalDate.now(), adopterName);
        animal.markAsAdopted();
        adoptionHistory.add(record);
    }
    public List<AdoptionRecord>getAdoptionHistory(){
        return List.copyOf(adoptionHistory);
    }
}
