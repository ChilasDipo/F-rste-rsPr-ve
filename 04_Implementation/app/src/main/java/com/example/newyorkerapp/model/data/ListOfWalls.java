package com.example.newyorkerapp.model.data;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ListOfWalls {

   // private ArrayList<Wallimpl> listofwalls = new ArrayList<>();
    private MutableLiveData<List<Wall>> ListOfWalls;

    private static ArrayList<Wallimpl> content = new ArrayList<>();

    public static ArrayList<Wallimpl> getContent(){
        return content;
    }

    public static void addToList(){
        content.add(new Wallimpl());
    }


}
