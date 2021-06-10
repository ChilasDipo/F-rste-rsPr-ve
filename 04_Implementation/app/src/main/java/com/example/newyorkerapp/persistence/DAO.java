package com.example.newyorkerapp.persistence;

import java.util.HashMap;

public interface DAO {

     HashMap<String, Integer> getNamesAndPriceForDoors();
     HashMap<String, Integer> getNamesAndPriceForGlass();
     HashMap<String, Integer> getNamesAndPriceForDoorHandel();

     double getFeeForBigGlass();

     double getFeeForWetRoom();

     double getPriceOfGlass();
     double getFeeForTransport();
}
