package com.example.newyorkerapp.model.data;

import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

import java.util.ArrayList;


public class Wallimpl {
    int height, length, fag , glas,heightOfGlass, lengthOfGlass, finalLengthOfGlas;
    double  priceOfWall;

    public int getFinalLengthOfGlas() {
        return finalLengthOfGlas;
    }

    public void setFinalLengthOfGlas(int finalLengthOfGlas) {
        this.finalLengthOfGlas = lenthOfFagList.get(finalLengthOfGlas + 1);;
    }

    FireBaseDAOimpl fireBaseDAOimpl = new FireBaseDAOimpl();
    ArrayList<Integer> fagliste = new ArrayList<>();
    ArrayList<Integer> glasliste = new ArrayList<>();
    ArrayList<Integer> lenthOfFagList = new ArrayList<>();
    ArrayList<Integer> heightOfGlassliste = new ArrayList<>();
    public Wallimpl() {
        fag = 0;
        glas = 0;
        heightOfGlass = 20;
        lengthOfGlass = 20;
        height=0;
        length=0;
    }
    public ArrayList<Integer> getFagliste() {
        return fagliste;
    }
    public double getPriceOfWall() {
        calculateAmountOfFag();
        calculateAmountOfGlas();
        int numberOfGlasInWall = fag * glas;
        priceOfWall = numberOfGlasInWall*fireBaseDAOimpl.getPriceOfGlass();
       return priceOfWall;
    }
    public void setLength(int length) {
        this.length = length;
        calculateAmountOfFag();
    }
    //TODO Skal måske sættes sammen en en metode som sætter både height and length(Hvis det gøres skal den først kaldes fra UI når begge værdier er indputtet)
    public void setHeight(int height) {
        this.height = height;
        calculateAmountOfGlas();
    }
    public ArrayList<Integer> getGlasliste() {
        return glasliste;
    }
    private void calculateAmountOfFag(){
        for (int i = 15; i < length ; i++) {
            if (length % i == 0){
                fagliste.add(length/i);
                lenthOfFagList.add(i);
            }
        }
        System.out.println("Antal fag " + fagliste.toString());
        System.out.println("Fag længder " + lenthOfFagList.toString());
    }
    private void calculateAmountOfGlas(){
        for (int i = 15; i < height ; i++) {
            if (height % i == 0){
                glasliste.add(height/i);
                heightOfGlassliste.add(i);
            }
        }
        System.out.println("Antal glas " + glasliste.toString());
        System.out.println("glas længder " + heightOfGlassliste.toString());
    }
}
