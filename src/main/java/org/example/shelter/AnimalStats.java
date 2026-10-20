package org.example.shelter;

import org.example.model.Animal;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.TreeMap;
import java.util.stream.Collectors;


public final class AnimalStats {

    private AnimalStats() {
        // nobody should create an instance of this class
    }

    // empty if the collection is empty, so we never divide by zero
    public static <T extends Animal> OptionalDouble averageAge(Collection<T> animals) {
        return animals.stream()
                .mapToInt(Animal::getAge)
                .average();
    }

    public static <T extends Animal> Optional<T> oldest(Collection<T> animals) {
        return animals.stream()
                .max(Comparator.comparingInt(Animal::getAge));
    }

    // species name -> number of animals, sorted by species name
    public static <T extends Animal> Map<String, Long> countBySpecies(Collection<T> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::getSpecies, TreeMap::new, Collectors.counting()));
    }
}
