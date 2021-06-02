package com.example.newyorkerapp.model.data;
import com.example.newyorkerapp.model.exceptions.AmountOfGlassOrFagUnableToBeCalculated;
import com.example.newyorkerapp.model.exceptions.InputMangler;
import com.example.newyorkerapp.persistence.FireBaseDAOimpl;
import java.util.ArrayList;


public class Wallimpl {
   private int height, length, fag, glas, heightOfGlass, lengthOfGlass, finalLengthOfGlas, finalHeightOfGlas;
    private double priceOfWall;

    private Boolean wallBigEnouthForExtraFee, wetRoom, hasDoor, hasSpecialGlas;

    ArrayList<Integer> fagliste = new ArrayList<>();
    ArrayList<Integer> glasliste = new ArrayList<>();
    ArrayList<Integer> lenthOfFagList = new ArrayList<>();
    ArrayList<Integer> heightOfGlassliste = new ArrayList<>();

    public Wallimpl() {
        heightOfGlass = 20;
        lengthOfGlass = 20;
        height = 0;
        length = 0;
    }
    public void setLength(int length) throws InputMangler {
        if (length == 0) throw new InputMangler(){};
        this.length = length;
        calculateAmountOfFag();
    }

    //TODO Skal måske sættes sammen en en metode som sætter både height and length(Hvis det gøres skal den først kaldes fra UI når begge værdier er indputtet)
    public void setHeight(int height) throws InputMangler {
        if (height == 0) throw new InputMangler(){};
        this.height = height;
        calculateAmountOfGlas();
    }

    public Boolean getWetRoom() {
        return wetRoom;
    }

    public void setWetRoom(Boolean wetRoom) {
        this.wetRoom = wetRoom;
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
        glas = glasliste.get(finalHeightOfGlas);
    }


    public void setFinalLengthOfGlas(int finalLengthOfGlas) {
        this.finalLengthOfGlas = lenthOfFagList.get(finalLengthOfGlas);
        fag = fagliste.get(finalLengthOfGlas);
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

    public ArrayList<Integer> getFagliste() {
        return fagliste;
    }

    public double getPriceOfWall() {
        calculateWallBigEnouthForExtraFee();
        if (wallBigEnouthForExtraFee){
            priceOfWall = (fag * glas) * 1470;
            return priceOfWall;
        }
        priceOfWall = (fag * glas) * 985;
        return priceOfWall;
    }


    public ArrayList<Integer> getGlasliste() {
        return glasliste;
    }

    private void calculateAmountOfFag() {
        fagliste.clear();
        lenthOfFagList.clear();
        for (int i = 15; i < length; i++) {
            if (length % i == 0) {
                fagliste.add(length / i);
                lenthOfFagList.add(i);
            }
        }
        System.out.println("Antal fag " + fagliste.toString());
        System.out.println("Fag længder " + lenthOfFagList.toString());
    }

    private void calculateAmountOfGlas() {
        glasliste.clear();
        heightOfGlassliste.clear();
        for (int i = 15; i < height; i++) {
            if (height % i == 0) {
                glasliste.add(height / i);
                heightOfGlassliste.add(i);
            }
        }
        System.out.println("Antal glas " + glasliste.toString());
        System.out.println("glas længder " + heightOfGlassliste.toString());
    }
}
