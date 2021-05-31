package com.example.newyorkerapp.model.data;
import com.example.newyorkerapp.model.exceptions.AmountOfGlassOrFagUnableToBeCalculated;
import com.example.newyorkerapp.model.exceptions.InputMangler;
import com.example.newyorkerapp.persistence.FireBaseDAOimpl;
import java.util.ArrayList;


public class Wallimpl {
    int height, length, fag, glas, heightOfGlass, lengthOfGlass, finalLengthOfGlas, finalHeightOfGlas;
    double priceOfWall;
    Boolean wallBigEnouthForExtraFee;

    public int getFinalLengthOfGlas() {
        return finalLengthOfGlas;
    }

    public void setFinalLengthOfGlas(int finalLengthOfGlas) {
        this.finalLengthOfGlas = lenthOfFagList.get(finalLengthOfGlas);
        fag = fagliste.get(finalLengthOfGlas);
        System.out.println("LenthOfGlas" + lenthOfFagList.get(finalLengthOfGlas));
        System.out.println("Amount of fag is " + fagliste.get(finalLengthOfGlas));
    }
    void calculateWallBigEnouthForExtraFee(){
    }
    public int getFinalHeightOfGlas() {
        return finalHeightOfGlas;
    }

    public void setFinalHeightOfGlas(int finalHeightOfGlas) {
        this.finalHeightOfGlas = heightOfGlassliste.get(finalHeightOfGlas);
        glas = glasliste.get(finalHeightOfGlas);
    }
    FireBaseDAOimpl fireBaseDAOimpl;
    ArrayList<Integer> fagliste = new ArrayList<>();
    ArrayList<Integer> glasliste = new ArrayList<>();
    ArrayList<Integer> lenthOfFagList = new ArrayList<>();
    ArrayList<Integer> heightOfGlassliste = new ArrayList<>();

    public ArrayList<String> getListfromDB(){
        ArrayList<String> listOfFetures = new ArrayList<>();
           listOfFetures = fireBaseDAOimpl.getListOfExtraFeturesFromDB();
        return listOfFetures;
    }
    public Wallimpl() {
        heightOfGlass = 20;
        lengthOfGlass = 20;
        height = 0;
        length = 0;
        fireBaseDAOimpl = new FireBaseDAOimpl();
    }

    public ArrayList<Integer> getFagliste() {
        return fagliste;
    }

    public double getPriceOfWall() throws AmountOfGlassOrFagUnableToBeCalculated {
        if (fag == 0 || glas ==0){
            throw new AmountOfGlassOrFagUnableToBeCalculated(){};
        }
        double pricePerGlass = fireBaseDAOimpl.getPriceOfGlass();
        priceOfWall = (fag * glas) * pricePerGlass;
        return priceOfWall;
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

    public ArrayList<Integer> getGlasliste() {
        return glasliste;
    }

    private void calculateAmountOfFag() {
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
