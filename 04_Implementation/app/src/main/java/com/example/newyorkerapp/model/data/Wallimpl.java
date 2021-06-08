package com.example.newyorkerapp.model.data;
import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;
import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

import java.util.ArrayList;


public class Wallimpl implements Wall {

   private int heightOfTheWall, lengthOfTheWall, amountOfFag, amountOfGlas, finalLengthOfGlas, finalHeightOfGlas;

    private Boolean  wetRoom, hasDoor,hasSpecielGlas;

    FireBaseDAOimpl fireBaseDAOimpl;
    DoorTypes doorTypes;
    GlassTypes glassTypes;
    private final ArrayList<Integer> fagliste = new ArrayList<>();
    private final ArrayList<Integer> glasliste = new ArrayList<>();
    private final ArrayList<Integer> lenthOfFagList = new ArrayList<>();
    private final ArrayList<Integer> heightOfGlassliste = new ArrayList<>();

    public Wallimpl() {
        heightOfTheWall = 0;
        lengthOfTheWall = 0;
        fireBaseDAOimpl = new FireBaseDAOimpl();
        wetRoom=false;
        hasDoor= false;
        hasSpecielGlas=false;
        doorTypes=null;
        glassTypes=null;

    }
    @Override
    public void setWetRoom(Boolean clicked){
            wetRoom = clicked;
    }
    @Override
    public void setHasDoor(Boolean clicked){ hasDoor = clicked;
      if (doorTypes==null){
          doorTypes = new DoorTypes(fireBaseDAOimpl.getNamesAndPriceForDoors());}
      }
    @Override
    public void setHasSpecielGlas(Boolean clicked){ hasSpecielGlas = clicked;
        if (glassTypes==null){
            glassTypes = new GlassTypes(fireBaseDAOimpl.getNamesAndPriceForGlass());}
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
        if (heightOfTheWall < 15) throw new HeightTooSmall(){};
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
        for (int i = 15; i < heightOfTheWall; i++) {
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
        for (int i = 15; i < lengthOfTheWall; i++) {
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
        double pricePerGlass = fireBaseDAOimpl.getPriceOfGlass();
        double extraPriceFordoor = 0;
        if (calculateWallBigEnouthForExtraFee()){ pricePerGlass += fireBaseDAOimpl.getFeeForBigGlass(); }
        if (wetRoom){ pricePerGlass += fireBaseDAOimpl.getFeeForWetRoom();}
         if (hasDoor){  extraPriceFordoor = doorTypes.getPriceForDoor();}
        if (hasSpecielGlas){ pricePerGlass += glassTypes.getPriceForGlass();}


        return (amountOfGlassInWall) * pricePerGlass + extraPriceFordoor + fireBaseDAOimpl.getFeeForTransport();
    }
    @Override
    public ArrayList<String> getListOfDoors() {
        return new ArrayList<>(fireBaseDAOimpl.getNamesAndPriceForDoors().keySet());
    }
    @Override
    public ArrayList<String> getListOfGlass() {
        return new ArrayList<>(fireBaseDAOimpl.getNamesAndPriceForGlass().keySet());
    }
    @Override
    public ArrayList<String> getListOfDoorGrips() {
        return new ArrayList<>(fireBaseDAOimpl.getNamesAndPriceForDoorHandel().keySet());
    }
}
