package org.example.model;

import java.util.UUID;

public final class AnimalId {
    private final String value;
    public AnimalId(){
        this.value = UUID.randomUUID().toString();
    }

    public AnimalId(String value){
        if(value==null||value.isBlank()){
            throw new IllegalArgumentException("Animal id must not empty");
        }
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
