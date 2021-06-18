package com.example.newyorkerapp.model.data;
import android.util.Log;

import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;
import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

import java.util.ArrayList;


public class Wallimpl implements Wall {

   private int heightOfTheWall, lengthOfTheWall, amountOfFag, amountOfGlas, finalLengthOfGlas, finalHeightOfGlas;
    private String name;
    private Boolean  wetRoom, hasDoor,hasSpecielGlas;

    DoorTypes doorTypes;
    GlassTypes glassTypes;
    private final ArrayList<Integer> fagliste = new ArrayList<>();
    private final ArrayList<Integer> glasliste = new ArrayList<>();
    private final ArrayList<Integer> lenthOfFagList = new ArrayList<>();
    private final ArrayList<Integer> heightOfGlassliste = new ArrayList<>();

    public Wallimpl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeightOfTheWall() {
        return heightOfTheWall;
    }

    public int getLengthOfTheWall() {
        return lengthOfTheWall;
    }

    public Wallimpl(int length, int height, boolean door) throws HeightTooSmall, InputMangler, HeightTooBig {
        wetRoom=false;
        hasSpecielGlas=false;

        doorTypes = new DoorTypes(FireBaseDAOimpl.getNamesAndPriceForDoors());
        Log.d("building", "Wallimpl: " + FireBaseDAOimpl.getNamesAndPriceForDoors() + " " + FireBaseDAOimpl.getNamesAndPriceForDoors());
        glassTypes= new GlassTypes(FireBaseDAOimpl.getNamesAndPriceForGlass());;

       setHeightOfTheWall(height);
        setLengthOfTheWall(length);
        setHasDoor(door);

    }
    @Override
    public void setWetRoom(Boolean clicked){
            wetRoom = clicked;
    }
    @Override
    public void setHasDoor(Boolean clicked){ hasDoor = clicked; }

      public boolean getHasDoor(){
        return hasDoor;
      }
    @Override
    public void setHasSpecielGlas(Boolean clicked){ hasSpecielGlas = clicked;
    }
    @Override
    public void selectDoor(int position){
        doorTypes.selscted(position);
    }
    @Override
    public void selectGlass(int position){
        glassTypes.selected(position);
    }
    @Override
    public void setFinalHeightOfGlas(int finalHeightOfGlas) {
        this.finalHeightOfGlas = heightOfGlassliste.get(finalHeightOfGlas);
        amountOfGlas = glasliste.get(finalHeightOfGlas);
    }
    @Override
    public void setFinalLengthOfGlas(int finalLengthOfGlas) {
        this.finalLengthOfGlas = lenthOfFagList.get(finalLengthOfGlas);
        amountOfFag = fagliste.get(finalLengthOfGlas);
    }
    @Override
    public int getFinalHeightOfGlas() {
        return finalHeightOfGlas;
    }
    @Override
    public int getFinalLengthOfGlas() {
        return finalLengthOfGlas;
    }


    //TODO Skal måske sættes sammen en en metode som sætter både height and length(Hvis det gøres skal den først kaldes fra UI når begge værdier er indputtet)
    @Override
    public void setHeightOfTheWall(int heightOfTheWall) throws InputMangler, HeightTooSmall, HeightTooBig {
        if (heightOfTheWall == 0) throw new InputMangler(){};
        if (heightOfTheWall < 10) throw new HeightTooSmall(){};
        if (heightOfTheWall > 250) throw new HeightTooBig(){};
        this.heightOfTheWall = heightOfTheWall;
        calculateAmountOfGlas();
    }
    @Override
    public ArrayList<Integer> getGlasliste() {
        return glasliste;
    }

    private void calculateAmountOfGlas() {
        glasliste.clear();
        heightOfGlassliste.clear();
        for (int i = 10; i < heightOfTheWall; i++) {
            if (heightOfTheWall % i == 0) {
                glasliste.add(heightOfTheWall / i);
                heightOfGlassliste.add(i);
            }
        }
        System.out.println("Antal glas " + glasliste.toString());
        System.out.println("glas længder " + heightOfGlassliste.toString());
    }

    @Override
    public void setLengthOfTheWall(int lengthOfTheWall) throws InputMangler {
        if (lengthOfTheWall == 0) throw new InputMangler(){};
        this.lengthOfTheWall = lengthOfTheWall;
        calculateAmountOfFag();
    }
    @Override
    public ArrayList<Integer> getFagliste() { return fagliste; }
    private void calculateAmountOfFag() {
        fagliste.clear();
        lenthOfFagList.clear();
        for (int i = 10; i < lengthOfTheWall; i++) {
            if (lengthOfTheWall % i == 0) {
                fagliste.add(lengthOfTheWall / i);
                lenthOfFagList.add(i);
            }
        }
        System.out.println("Antal fag " + fagliste.toString());
        System.out.println("Fag længder " + lenthOfFagList.toString());
    }
    private Boolean calculateWallBigEnouthForExtraFee(){
        if (finalLengthOfGlas*finalHeightOfGlas == 5000){
            return true;
        }
        else return false;
    }
    @Override
    public double getPriceOfWall() {
        int amountOfGlassInWall = amountOfFag * amountOfGlas;

        //setter prisen for glas og lægger tillæget til for hver condition som er true
        double pricePerGlass = FireBaseDAOimpl.getPriceOfGlass();
        double extraPriceFordoor = 0;
        if (calculateWallBigEnouthForExtraFee()){ pricePerGlass += FireBaseDAOimpl.getFeeForBigGlass(); }
        if (wetRoom){ pricePerGlass += FireBaseDAOimpl.getFeeForWetRoom();}
         if (hasDoor){  extraPriceFordoor = doorTypes.getPriceForDoor();}
        if (hasSpecielGlas){ pricePerGlass += glassTypes.getPriceForGlass();}
        return (amountOfGlassInWall) * pricePerGlass + extraPriceFordoor + FireBaseDAOimpl.getFeeForTransport();
    }
    @Override
    public ArrayList<String> getListOfDoors() {
        return new ArrayList<>(FireBaseDAOimpl.getNamesAndPriceForDoors().keySet());
    }
    @Override
    public ArrayList<String> getListOfGlass() {
        return new ArrayList<>(FireBaseDAOimpl.getNamesAndPriceForGlass().keySet());
    }
    @Override
    public ArrayList<String> getListOfDoorGrips() {
        return new ArrayList<>(FireBaseDAOimpl.getNamesAndPriceForDoorHandel().keySet());
    }
}
