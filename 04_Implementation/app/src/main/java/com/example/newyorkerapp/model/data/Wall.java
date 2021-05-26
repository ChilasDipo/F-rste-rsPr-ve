package com.example.newyorkerapp.model.data;

public interface Wall {

    /**
     * Sætter længden af den væg om skal designes
     */
    void setLength(int length);

    /**
     * Sætter højden af den væg om skal designes
     */
    void setHeight(int height);

    /**
     * @return den tilladte mænge af fag som væggen kan bygges op af uden at det bliver en speciel ordre
     */
    double getAllowedAmountOfFag();

    /**
     * @return den tilladte mænge af glas som væggen kan bygges op af uden at det bliver en speciel ordre
     */
    double getAllowedAmountOfGlas();

    double getHeightOfGlass();

    double getLengthOfGlass();

    double getPriceOfWall();



}
