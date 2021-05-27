package com.example.newyorkerapp.model.data;

import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wallimpl implements Wall {
    int fag , glas;
    double height, length, heightOfGlass, lengthOfGlass,priceOfWall;
    FireBaseDAOimpl fireBaseDAOimpl = new FireBaseDAOimpl();
    public Wallimpl() {
        fag = 0;
        glas = 0;
        heightOfGlass = 60;
        lengthOfGlass = 45;
        height=0;
        length=0;
    }
    @Override
    public double getPriceOfWall() {
       calculatePriceOfWall();
       return priceOfWall;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
       /* if (height!=0 & this.length !=0){
            calculateAmountOfFag();
            calculateAmountOfGlas();
        }*/
    }
    //TODO Skal måske sættes sammen en en metode som sætter både height and length(Hvis det gøres skal den først kaldes fra UI når begge værdier er indputtet)
    @Override
    public void setHeight(int height) {
        this.height = height;
     /*   if (this.height!=0 & length !=0){
            calculateAmountOfFag();
            calculateAmountOfGlas();
        }*/
    }
    @Override
    public double getHeightOfGlass() {
        return heightOfGlass;
    }
    @Override
    public double getLengthOfGlass() {
        return lengthOfGlass;
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

        fag =  (int) Math.floor(length/ lengthOfGlass);


        if (length % lengthOfGlass != 0){
            lengthOfGlass++;
            calculateAmountOfFag();
        }

        lengthOfGlass = length / (double)fag;
    }
    private void calculateAmountOfGlas(){
        glas = (int) (height/ heightOfGlass);



       if (height % heightOfGlass != 0){
            heightOfGlass++;
            calculateAmountOfGlas();
        }

       heightOfGlass = height / (double)glas;
    }

   private void calculatePriceOfWall(){
       calculateAmountOfFag();
       calculateAmountOfGlas();
       int numberOfGlasInWall = fag * glas;
        priceOfWall = numberOfGlasInWall*fireBaseDAOimpl.getPriceOfGlass();
    }
    private void buildOutputString(){

    }
}
