package com.example.newyorkerapp.model.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wallimpl implements Wall {
    int fag , glas;
    double height, length;          //TODO sætte alla variabler sammen en en eller 2 linjer
    double heightOfGlass, lengthOfGlass;
    double priceOfGlass = 985; //TODO skal  laves en sætter så pricen kan sættet ud fra databsen
    double priceOfWall;

    public Wallimpl() {
        fag = 0;
        glas = 0;
        heightOfGlass = 60;
        lengthOfGlass = 45;
    }

    public double getPriceOfWall() {
        calculatePriceOfWall();
        return priceOfWall;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
        /*if (height!=0 & length !=0){
            calculateAmountOfFag();
            calculateAmountOfGlas();
        }*/
    }
    //TODO Skal måske sættes sammen en en metode som sætter både height and length(Hvis det gøres skal den først kaldes fra UI når begge værdier er indputtet)
    @Override
    public void setHeight(int height) {
        this.height = height;
       /* if (height!=0 & length !=0){
            calculateAmountOfFag();
            calculateAmountOfGlas();
        }*/
    }

    @Override
    public double getAllowedAmountOfFag() {
        return fag;
    }

    @Override
    public double getAllowedAmountOfGlas() {
        return glas;
    }

    private void calculateAmountOfFag(){
       fag = (int) (length/ lengthOfGlass);
        lengthOfGlass = length / fag;
    }

    private void calculateAmountOfGlas(){
        glas = (int) (height/ heightOfGlass);
        heightOfGlass = height / glas;
    }

   private void calculatePriceOfWall(){
       calculateAmountOfFag();
       calculateAmountOfGlas();
        int numberOfGlasInWall = fag * glas;
        priceOfWall = numberOfGlasInWall*priceOfGlass;
    }

}
