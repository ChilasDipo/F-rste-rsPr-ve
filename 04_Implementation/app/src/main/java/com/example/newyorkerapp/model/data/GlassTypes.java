package com.example.newyorkerapp.model.data;

import java.util.ArrayList;
import java.util.HashMap;

public class GlassTypes {

    ArrayList<String> glassTypes;
    ArrayList<Integer> priceList;
    String nameOfGlass;
    private double priceForGlass;
    private HashMap<String, Integer> nameAndPrice;

    public double getPriceForGlass(){return priceForGlass;}

    public GlassTypes(HashMap<String, Integer> glassTypeList){
        nameAndPrice = glassTypeList;
        glassTypes = new ArrayList<>(nameAndPrice.keySet());
        priceList = new ArrayList<>(nameAndPrice.values());
        priceForGlass =0.0;
    }

    public void selected(int possison){
        priceForGlass = priceList.get(possison);
        nameOfGlass = glassTypes.get(possison);

    }
}
