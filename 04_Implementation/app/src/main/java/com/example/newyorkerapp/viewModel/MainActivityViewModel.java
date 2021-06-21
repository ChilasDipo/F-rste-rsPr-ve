package com.example.newyorkerapp.viewModel;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerapp.model.data.ListOfWallsFromCatalog;
import com.example.newyorkerapp.model.data.Wallimpl;
import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;
import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Wallimpl> wallObject;
    private Wallimpl wall;
    int selectedWallFromCatalog;


    public void buildingNeedListForApplicationToWork(){
        FireBaseDAOimpl.buildHashMapsForFetureNameAndPrice();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ListOfWallsFromCatalog.buildListOfWallsFromCatalog();
            }
        }, 2000);
    }



    //Bliver sat udfra det ImageView som blev valgt i kataloget
    public void setSelectedWallFromCatalog(int selectedWallFromCatalog) {
        this.selectedWallFromCatalog = selectedWallFromCatalog;
    }

    public LiveData<Wallimpl> getWall() {
        if (wallObject == null) {
            wallObject = new MutableLiveData<>();
            wall = ListOfWallsFromCatalog.getWallFromList(selectedWallFromCatalog);
            wallObject.setValue(wall);
        }
        return wallObject;
    }
    //https://medium.com/@atifmukhtar/mvvm-java-model-view-view-model-livedata-148475d7f383

    public ArrayList<String> getListOfDoors(){ return new ArrayList<>(wall.getListOfDoors()); }

    public ArrayList<String> getListOfGlas(){ return new ArrayList<>(wall.getListOfGlass()); }

    public ArrayList<String> getListOfDoorgrips(){ return new ArrayList<>(wall.getListOfDoorGrips()); }

    public ArrayList<Integer> getAdapterForFag(){ return wall.getFagliste(); }

    public ArrayList<Integer> getAdapterForGlas(){ return wall.getGlasliste(); }

    public boolean getHasDoor(){ return wall.getHasDoor(); }

    public int getHeightOfTheWall(){
        return wall.getHeightOfTheWall();
    }
    public int getLengthOfTheWall(){
        return wall.getLengthOfTheWall();
    }

    //Når døren bliver valgt i dropdown bliver denne metode kaldt og forsøller væggen hvilken dør brugeren har valgt
    public void selectDoor(int position){
        wall.selectDoor(position);
        wallObject.setValue(wall);
    }
    public void selectGlass(int position){
        wall.selectGlass(position);
        wallObject.setValue(wall);
    }

    public void selectAmountOfGlassPerFag(int position){
        wall.setFinalHeightOfGlas(position);
        wallObject.setValue(wall);
    }
    public void selectAmountOfFag(int position){
        wall.setFinalLengthOfGlas(position);
        wallObject.setValue(wall);
    }


    public void setWetRoom(Boolean clicked){
        wall.setWetRoom(clicked);
        wallObject.setValue(wall);
    }
    public void setHasDoor(Boolean clicked){
        wall.setHasDoor(clicked);
        wallObject.setValue(wall);
    }
    public void setHasSpecielGlass(Boolean clicked){
        wall.setHasSpecielGlas(clicked);
        wallObject.setValue(wall);
    }

    public void setlenght(int lenght) {
        try {
            wall.setLengthOfTheWall(lenght);
        } catch (InputMangler inputMangler) {
            inputMangler.printStackTrace();
        }
        wallObject.setValue(wall);
    }

    public void setHeight(int height) {
        try {
            wall.setHeightOfTheWall(height);
        } catch (InputMangler inputMangler) {
            inputMangler.printStackTrace();
        } catch (HeightTooSmall heightTooSmall) {
            heightTooSmall.printStackTrace();
        } catch (HeightTooBig heightTooBig) {
            heightTooBig.printStackTrace();
        }
        wallObject.setValue(wall);
    }
    //Opbygger en String til vores textfelt som indenholder information om væggen
    public String getInfoAboutWall(){
        return "Glases højde er  " +
                wall.getFinalHeightOfGlas() + " cm." +"\n" +
                "glasses brede er " +
                wall.getFinalLengthOfGlas() +  " cm." +"\n" +
                "Væggens pris er " +
                wall.getPriceOfWall() + " kr.";
    }
}
