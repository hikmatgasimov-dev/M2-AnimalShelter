package org.example.menu;
import org.example.model.AdoptionRecord;


import org.example.model.Animal;
import org.example.model.Bird;
import org.example.model.Cat;
import org.example.model.Dog;
import org.example.shelter.Shelter;

import org.example.shelter.AnimalStats;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class ConsoleMenu {

    private static final int OPTION_ADD = 1;
    private static final int OPTION_LIST_ALL = 2;
    private static final int OPTION_FIND_BY_SPECIES = 3;
    private static final int OPTION_LIST_AVAILABLE = 4;
    private static final int OPTION_MARK_ADOPTED = 5;
    private static final int OPTION_SORT = 6;
    private static final int OPTION_STATISTICS = 7;
    private static final int OPTION_HISTORY = 8;
    private static final int OPTION_EXIT = 0;

    private final Shelter<Animal> shelter;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start() {

        boolean running = true;

        while (running) {
            printMenu();
            String input = readLine("Choose an option: ");
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }
            switch (choice) {
                case OPTION_ADD -> addAnimal();
                case OPTION_LIST_ALL -> printAnimals(shelter.getAllAnimals());
                case OPTION_FIND_BY_SPECIES -> findBySpecies();
                case OPTION_LIST_AVAILABLE -> printAnimals(shelter.findAvailableAnimals());
                case OPTION_MARK_ADOPTED -> markAsAdopted();
                case OPTION_SORT -> sortAnimals();
                case OPTION_STATISTICS -> showStatistics();
                case OPTION_HISTORY -> showAdoptionHistory();
                case OPTION_EXIT -> {
                    System.out.println("GoodBye");
                    running = false;
                }
                default -> System.out.println("Invalid option,please choose 0-8. ");
            }
        }

    }


    private void addAnimal() {
        String name = readLine("Name: ");
        String ageText = readLine("Age: ");
        String species = readLine("Species (Dog, Cat, Bird): ");

        try {
            int age = Integer.parseInt(ageText);
            Animal animal = switch (species.toLowerCase(Locale.ROOT)) {
                case "dog" -> new Dog(name, age);
                case "cat" -> new Cat(name, age);
                case "bird" -> new Bird(name, age);
                default -> throw new IllegalArgumentException("Unknown species: " + species);
            };
            shelter.addAnimal(animal);
            System.out.println("Added: " + animal);
        } catch (NumberFormatException e) {
            System.out.println("Age must be whole number");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add animal: " + e.getMessage());
        }
    }

    private void printAnimals(List<Animal> animals) {

        if (animals.isEmpty()) {
            System.out.println("No animals found.");
            return;
        }
        animals.forEach(System.out::println);
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();

    }

    private void findBySpecies() {
        String species = readLine("Species to search for: ");
        printAnimals(shelter.findBySpecies(species));
    }

    private void markAsAdopted() {
        List<Animal> available = shelter.findAvailableAnimals();

        if (available.isEmpty()) {
            System.out.println("There are no animals waiting for adoption.");
            return;
        }
        printAnimals(available);
        String id = readLine("Enter the id of the animal to adopt: ");
        String adopterName=readLine("Adopter name:");
        try {

            shelter.markAsAdopted(id,adopterName);
            System.out.println("Animal marked as adopted.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }

    private void sortAnimals() {
        String choice=readLine("Sort by 1) name or 2) age: ");

        Comparator<Animal>comparator;
        switch (choice){
            case "1"->comparator=Comparator.comparing(Animal::getName,String.CASE_INSENSITIVE_ORDER);
            case "2"->comparator=Comparator.comparingInt(Animal::getAge);

            default -> {
                System.out.println("Please choose 1 or 2.");
                return;
            }
        }
        printAnimals(shelter.getSortedAnimals(comparator));
    }
    private void showStatistics() {
        List<Animal> animals = shelter.getAllAnimals();
        if (animals.isEmpty()) {
            System.out.println("No animals found.");
            return;
        }

        double average = AnimalStats.averageAge(animals).orElse(0);
        System.out.println("Total animals: " + animals.size());
        System.out.println("Average age: " + Math.round(average * 100.0) / 100.0);
        AnimalStats.oldest(animals).ifPresent(animal ->
                System.out.println("Oldest animal: " + animal.getName() + " (" + animal.getAge() + " years old)"));

        System.out.println("Animals per species:");
        AnimalStats.countBySpecies(animals).forEach((species, count) ->
                System.out.println("  " + species + ": " + count));
    }

    private void showAdoptionHistory(){
        List<AdoptionRecord>history=shelter.getAdoptionHistory();
        if(history.isEmpty()){
            System.out.println("No adoption yet.");
            return;
        }
        history.forEach(System.out::println);
    }
    private void printMenu() {
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                6. List animals sorted by name or age
                7. Show statistics
                8. Show adaption history
                0. Exit
                """);
    }


}

