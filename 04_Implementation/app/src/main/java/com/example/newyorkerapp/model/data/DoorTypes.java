package com.example.newyorkerapp.model.data;

import java.util.ArrayList;
import java.util.HashMap;

public class DoorTypes {

    final ArrayList<String> doorTypes;
    final ArrayList<Integer> prisceList;
    String nameOfDoor;
    private double priceForDoor;
    private HashMap<String, Integer> namesAndPrices;

    public double getPriceForDoor() {
        return priceForDoor;
    }


    public DoorTypes(HashMap<String, Integer> something) {
        namesAndPrices = something;
        doorTypes =  new ArrayList<>(namesAndPrices.keySet());
        prisceList = new ArrayList<>(namesAndPrices.values());
        priceForDoor = 0.0;
    }

    public void selscted(int possison){
       priceForDoor = prisceList.get(possison);
       nameOfDoor = doorTypes.get(possison);
    }

}
