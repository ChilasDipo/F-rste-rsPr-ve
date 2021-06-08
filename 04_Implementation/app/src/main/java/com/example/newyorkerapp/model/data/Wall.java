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

    /**
     * @Metode som VIewModellel kander når brugeren har valgt en dør fra drop downen
     */
     void selectDoor(int position);

    /**
     * @param position
     */
     void selectGlass(int position);

    /**
     * @param clicked Sætter Boleanen WetRoom til true hvis checkboxen i Ui er klikket på (Hvis ViewMOdellen kander metoden som ssker på checkbox click)
     */
     void setWetRoom(Boolean clicked);

    /**
     * @param clicked Sætter Boleanen HasDoor til true hvis checkboxen i Ui er klikket på (Hvis ViewMOdellen kander metoden som ssker på checkbox click)
     */
     void setHasDoor(Boolean clicked);

    /**
     * @param clicked Sætter Boleanen SpecielGlas til true hvis checkboxen i Ui er klikket på (Hvis ViewMOdellen kander metoden som ssker på checkbox click)
     */
     void setHasSpecielGlas(Boolean clicked);

    /**
     * @param finalHeightOfGlas
     */
    void setFinalHeightOfGlas(int finalHeightOfGlas);

    /**
     * @param finalLengthOfGlas
     */
    void setFinalLengthOfGlas(int finalLengthOfGlas);

    /**
     * @return
     */
    int getFinalHeightOfGlas();

    /**
     * @return
     */
    int getFinalLengthOfGlas();

    /**
     * @return Et ArrayList med navne på alle vores døre
     */
    ArrayList<String> getListOfDoors();

    /**
     * @return Et ArrayList med navne på alle vores specielglas
     */
     ArrayList<String> getListOfGlass();

    /**
     * @return Et ArrayList med navne på alle vores DoorGrips
     */
     ArrayList<String> getListOfDoorGrips();



}

