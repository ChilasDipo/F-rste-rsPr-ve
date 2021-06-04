package com.example.newyorkerapp.model.data;
import com.example.newyorkerapp.model.exceptions.InputMangler;
import com.google.api.DocumentationOrBuilder;

import java.util.ArrayList;


public class Wallimpl {
   private int heightOfTheWall, lengthOfTheWall, amountOfFag, amountOfGlas, finalLengthOfGlas, finalHeightOfGlas;


    private double priceOfWall;

    private Boolean wallBigEnouthForExtraFee, wallIsInAWetRoom, hasDoor, hasSpecialGlas;

    //SpecialGlass specialGlass = new SpecielGlass();

    

    private ArrayList<Integer> fagliste = new ArrayList<>();
    private ArrayList<Integer> glasliste = new ArrayList<>();
    private  ArrayList<Integer> lenthOfFagList = new ArrayList<>();
    private  ArrayList<Integer> heightOfGlassliste = new ArrayList<>();

    public Wallimpl() {
        heightOfTheWall = 0;
        lengthOfTheWall = 0;
    }

    public Boolean getWallIsInAWetRoom() {
        return wallIsInAWetRoom;
    }

    public void setWallIsInAWetRoom(Boolean wallIsInAWetRoom) {
        this.wallIsInAWetRoom = wallIsInAWetRoom;
    }

    public Boolean getHasDoor() {
        return hasDoor;
    }

    public void setHasDoor(Boolean hasDoor) {
        this.hasDoor = hasDoor;
    }

    public Boolean getHasSpecialGlas() {
        return hasSpecialGlas;
    }

    public void setHasSpecialGlas(Boolean hasSpecialGlas) {
        this.hasSpecialGlas = hasSpecialGlas;
    }

    public void setFinalHeightOfGlas(int finalHeightOfGlas) {
        this.finalHeightOfGlas = heightOfGlassliste.get(finalHeightOfGlas);
        amountOfGlas = glasliste.get(finalHeightOfGlas);
    }

    public void setFinalLengthOfGlas(int finalLengthOfGlas) {
        this.finalLengthOfGlas = lenthOfFagList.get(finalLengthOfGlas);
        amountOfFag = fagliste.get(finalLengthOfGlas);
    }
    void calculateWallBigEnouthForExtraFee(){
        if (finalLengthOfGlas*finalHeightOfGlas == 5000){
            wallBigEnouthForExtraFee=true;
        }
        else wallBigEnouthForExtraFee=false;
    }
    public int getFinalHeightOfGlas() {
        return finalHeightOfGlas;
    }

    public int getFinalLengthOfGlas() {
        return finalLengthOfGlas;
    }


    //TODO Skal måske sættes sammen en en metode som sætter både height and length(Hvis det gøres skal den først kaldes fra UI når begge værdier er indputtet)
    public void setHeightOfTheWall(int heightOfTheWall) throws InputMangler {
        if (heightOfTheWall == 0) throw new InputMangler(){};
        this.heightOfTheWall = heightOfTheWall;
        calculateAmountOfGlas();
    }
    public ArrayList<Integer> getGlasliste() {
        return glasliste;
    }
    private void calculateAmountOfGlas() {
        glasliste.clear();
        heightOfGlassliste.clear();
        for (int i = 15; i < heightOfTheWall; i++) {
            if (heightOfTheWall % i == 0) {
                glasliste.add(heightOfTheWall / i);
                heightOfGlassliste.add(i);
            }
        }
        System.out.println("Antal glas " + glasliste.toString());
        System.out.println("glas længder " + heightOfGlassliste.toString());
    }


    public void setLengthOfTheWall(int lengthOfTheWall) throws InputMangler {
        if (lengthOfTheWall == 0) throw new InputMangler(){};
        this.lengthOfTheWall = lengthOfTheWall;
        calculateAmountOfFag();
    }
    public ArrayList<Integer> getFagliste() { return fagliste; }
    private void calculateAmountOfFag() {
        fagliste.clear();
        lenthOfFagList.clear();
        for (int i = 15; i < lengthOfTheWall; i++) {
            if (lengthOfTheWall % i == 0) {
                fagliste.add(lengthOfTheWall / i);
                lenthOfFagList.add(i);
            }
        }
        System.out.println("Antal fag " + fagliste.toString());
        System.out.println("Fag længder " + lenthOfFagList.toString());
    }
    //TODO move to price klass
    public double getPriceOfWall() {
        calculateWallBigEnouthForExtraFee();
        if (wallBigEnouthForExtraFee){
            priceOfWall = (amountOfFag * amountOfGlas) * 1470;
            return priceOfWall;
        }
        priceOfWall = (amountOfFag * amountOfGlas) * 985;
        return priceOfWall;
    }

}
