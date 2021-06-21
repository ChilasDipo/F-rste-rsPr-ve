package com.example.newyorkerapp.model.data;

import java.util.ArrayList;
import java.util.HashMap;

public class DoorTypes {

    final ArrayList<String> doorTypesNames;
    final ArrayList<Integer> prisceList;
    private String nameOfDoor;
    private double priceForDoor;
    private final HashMap<String, Integer> namesAndPrices;

    public double getPriceForDoor() {
        return priceForDoor;
    }
    public ArrayList<String> getNamesForDoor() {
        return doorTypesNames;
    }

    public DoorTypes(HashMap<String, Integer> something) {
        namesAndPrices = something;
        doorTypesNames =  new ArrayList<>(namesAndPrices.keySet());
        prisceList = new ArrayList<>(namesAndPrices.values());
        priceForDoor = 0.0;
    }

    public void selected(int possison){
       priceForDoor = prisceList.get(possison);
       nameOfDoor = doorTypesNames.get(possison);
    }

}
