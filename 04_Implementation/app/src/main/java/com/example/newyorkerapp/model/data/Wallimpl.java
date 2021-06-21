package com.example.newyorkerapp.model.data;

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
    private final ArrayList<Integer> heightOfGlassListe = new ArrayList<>();

    public Wallimpl() { }

    public Wallimpl(int length, int height, boolean door) throws HeightTooSmall, InputMangler, HeightTooBig {
        wetRoom=false;
        hasSpecielGlas=false;
        doorTypes = new DoorTypes(FireBaseDAOimpl.getNamesAndPriceForDoors());
        glassTypes= new GlassTypes(FireBaseDAOimpl.getNamesAndPriceForGlass());;
        setHeightOfTheWall(height);
        setLengthOfTheWall(length);
        setHasDoor(door);
    }

    public String getName() {
        return name;
    }

    public boolean getHasDoor(){ return hasDoor; }

    public int getHeightOfTheWall() {
        return heightOfTheWall;
    }

    public int getLengthOfTheWall() {
        return lengthOfTheWall;
    }
    @Override
    public int getFinalHeightOfGlas() {
        return finalHeightOfGlas;
    }
    @Override
    public int getFinalLengthOfGlas() {
        return finalLengthOfGlas;
    }
    @Override
    public ArrayList<Integer> getGlasliste() { return glasliste; }
    @Override
    public ArrayList<Integer> getFagliste() { return fagliste; }
    @Override
    public ArrayList<String> getListOfDoors() { return doorTypes.getNamesForDoor(); }
    @Override
    public ArrayList<String> getListOfGlass() { return glassTypes.getNamesForGlass(); }
    @Override
    public ArrayList<String> getListOfDoorGrips() { return new ArrayList<>(FireBaseDAOimpl.getNamesAndPriceForDoorHandel().keySet());}
    @Override
    public double getPriceOfWall() {
        int amountOfGlassInWall = amountOfFag * amountOfGlas;
        //setter prisen for glas og lægger tillæget til for hver condition som er true
        double pricePerGlass = FireBaseDAOimpl.getPriceOfGlass();
        double extraPriceFordoor = 0;
        if (finalLengthOfGlas*finalHeightOfGlas == 5000){ pricePerGlass += FireBaseDAOimpl.getFeeForBigGlass(); }
        if (wetRoom){ pricePerGlass += FireBaseDAOimpl.getFeeForWetRoom();}
        if (hasSpecielGlas){ pricePerGlass += glassTypes.getPriceForGlass();}
        if (hasDoor){  extraPriceFordoor = doorTypes.getPriceForDoor();}
        return (amountOfGlassInWall) * pricePerGlass + extraPriceFordoor + FireBaseDAOimpl.getFeeForTransport();
    }

    public void setName(String name) { this.name = name; }
    @Override
    public void setWetRoom(Boolean clicked){
            wetRoom = clicked;
    }
    @Override
    public void setHasDoor(Boolean clicked){ hasDoor = clicked; }
    @Override
    public void setHasSpecielGlas(Boolean clicked){ hasSpecielGlas = clicked; }
    //SetFinal bliver kaldt når et antal fag eller glas bliver valgt i deres dropdown
    @Override
    public void setFinalHeightOfGlas(int finalHeightOfGlas) {
        this.finalHeightOfGlas = heightOfGlassListe.get(finalHeightOfGlas);
        amountOfGlas = glasliste.get(finalHeightOfGlas);
    }
    @Override
    public void setFinalLengthOfGlas(int finalLengthOfGlas) {
        this.finalLengthOfGlas = lenthOfFagList.get(finalLengthOfGlas);
        amountOfFag = fagliste.get(finalLengthOfGlas);
    }
    //TODO Skal måske sættes sammen en en metode som sætter både height and length(Hvis det gøres skal den først kaldes fra UI når begge værdier er indputtet)
    @Override
    public void setHeightOfTheWall(int heightOfTheWall) throws InputMangler, HeightTooSmall, HeightTooBig {
        if (heightOfTheWall == 0) throw new InputMangler(){};
        if (heightOfTheWall < 10) throw new HeightTooSmall(){};
        if (heightOfTheWall > 250) throw new HeightTooBig(){};
        this.heightOfTheWall = heightOfTheWall;
        calculateAmountOfGlass();
    }
    @Override
    public void setLengthOfTheWall(int lengthOfTheWall) throws InputMangler {
        if (lengthOfTheWall == 0) throw new InputMangler(){};
        this.lengthOfTheWall = lengthOfTheWall;
        calculateAmountOfFag();
    }
    @Override
    public void selectDoor(int position){
        doorTypes.selected(position);
    }
    @Override
    public void selectGlass(int position){
        glassTypes.selected(position);
    }

    private void calculateAmountOfGlass() {
        glasliste.clear();
        heightOfGlassListe.clear();
        for (int i = 10; i < heightOfTheWall; i++) {
            if (heightOfTheWall % i == 0) {
                glasliste.add(heightOfTheWall / i);
                heightOfGlassListe.add(i);
            }
        }
    }
    private void calculateAmountOfFag() {
        fagliste.clear();
        lenthOfFagList.clear();
        for (int i = 10; i < lengthOfTheWall; i++) {
            if (lengthOfTheWall % i == 0) {
                fagliste.add(lengthOfTheWall / i);
                lenthOfFagList.add(i);
            }
        }
    }
}
