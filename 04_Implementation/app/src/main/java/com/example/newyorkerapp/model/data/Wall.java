package com.example.newyorkerapp.model.data;

import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;

import java.util.ArrayList;

public interface Wall {

    /**
     * Sætter længden af den væg om skal designes
     */
    void setLengthOfTheWall(int lengthOfTheWall) throws InputMangler;

    /**
     * Sætter højden af den væg om skal designes
     */
    void setHeightOfTheWall(int heightOfTheWall) throws InputMangler, HeightTooSmall, HeightTooBig;

    /**
     * @return den tilladte mængde af fag som væggen kan bygges op af uden at det bliver en speciel ordre
     */
    ArrayList<Integer> getFagliste();

    /**
     * @return den tilladte mænge af glas som væggen kan bygges op af uden at det bliver en speciel ordre
     */
    ArrayList<Integer> getGlasliste();

    double getPriceOfWall();

     void selectDoor(int position);
     void selectGlass(int position);
     void setWetRoom(Boolean clicked);
     void setHasDoor(Boolean clicked);
     void setHasSpecielGlas(Boolean clicked);

    void setFinalHeightOfGlas(int finalHeightOfGlas);

    void setFinalLengthOfGlas(int finalLengthOfGlas);

    int getFinalHeightOfGlas();

    int getFinalLengthOfGlas();

    ArrayList<String> getListOfDoors();

     ArrayList<String> getListOfGlass();

     ArrayList<String> getListOfDoorGrips();



}

