package com.example.newyorkerapp.model.data;

import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

import java.util.ArrayList;


public class Wallimpl implements Wall {
    int height, length, fag , glas;
    double  heightOfGlass, lengthOfGlass,priceOfWall;
    FireBaseDAOimpl fireBaseDAOimpl = new FireBaseDAOimpl();
    ArrayList<Integer> fagliste = new ArrayList<>();
    ArrayList<Integer> glasliste = new ArrayList<>();
    public Wallimpl() {
        fag = 0;
        glas = 0;
        heightOfGlass = 10;
        lengthOfGlass = 10;
        height=0;
        length=0;
    }

    public ArrayList<Integer> getFagliste() {
        return fagliste;
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

    public ArrayList<Integer> getGlasliste() {
        return glasliste;
    }

    private void calculateAmountOfFag(){
      //  fag =  (int) Math.floor(length/ lengthOfGlass);

      /*  if (lengthOfGlass < 150){
            if (length % lengthOfGlass == 0) {
                fagliste.add((int) length / (int) lengthOfGlass);
            }
            lengthOfGlass++;
            calculateAmountOfFag();
        }*/

       while (lengthOfGlass < 150){
           if ((length) % lengthOfGlass == 0) {
               fagliste.add((int) length / (int) lengthOfGlass);
           }
           lengthOfGlass++;
       }
        for (int i = 0; i <fagliste.size() ; i++) {
            System.out.println("fagliste" + fagliste.get(i));
        }
        fag = fagliste.get(fagliste.size()/2);
        lengthOfGlass = length/fag;
        return;
      //  lengthOfGlass = length / (double)fag;
    }
    private void calculateAmountOfGlas(){
    //    glas = (int) (height/ heightOfGlass);

     /*   if (heightOfGlass < 150){
            if (height % heightOfGlass == 0) {
                glasliste.add((int) height / (int) heightOfGlass);
            }
            heightOfGlass++;
            calculateAmountOfGlas();
        }*/

        while (heightOfGlass < 150){
            if (height % heightOfGlass == 0) {
                glasliste.add((int) height / (int) heightOfGlass);
            }
            heightOfGlass++;
        }

        for (int i = 0; i <glasliste.size() ; i++) {
            System.out.println("glasliste" + glasliste.get(i));
        }
        glas = glasliste.get(glasliste.size()/2);
        heightOfGlass = height/glas;


      /* if (height % heightOfGlass != 0){
            heightOfGlass++;
            calculateAmountOfGlas();
        }
        glas = (int) height / (int) heightOfGlass;*/
   //   heightOfGlass = height / (double)glas;
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
