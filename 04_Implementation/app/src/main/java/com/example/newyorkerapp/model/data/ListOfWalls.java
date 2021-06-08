package com.example.newyorkerapp.model.data;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ListOfWalls {

    private ArrayList<Wallimpl> listofwalls = new ArrayList<>();
    private MutableLiveData<List<Wall>> ListOfWalls;

    public void addWall (Wallimpl wall){
        listofwalls.add(wall);
    }

    public Wallimpl getwall (int i){
       return listofwalls.get(i);
    }

}
