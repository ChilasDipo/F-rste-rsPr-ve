package com.example.newyorkerapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerapp.model.data.Wallimpl;
import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Wallimpl> wallObject;
    private Wallimpl wall;

     ArrayList<Wallimpl> listOfWalls = new ArrayList<>();

    public MainActivityViewModel() {
        wallObject = new MutableLiveData<>();
        addWall();
        selectWall(0);
    }
    //https://medium.com/@atifmukhtar/mvvm-java-model-view-view-model-livedata-148475d7f383
    public LiveData<Wallimpl> getWall() {
        if (wallObject == null) {
            wallObject = new MutableLiveData<>();
        }
        return wallObject;
    }

     void selectWall(int i){
        wall = listOfWalls.get(i);
        wallObject.setValue(wall);
    }
        // disse 2 funktioner kan ændres så man kan vælge et væg obejct
    void addWall(){
        listOfWalls.add(new Wallimpl());
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
    public ArrayList<String> getListOfDoors(){
        return new ArrayList<>(wall.getListOfDoors());

    }
    public ArrayList<String> getListOfGlas(){
        return new ArrayList<>(wall.getListOfGlass());
    }
    public ArrayList<String> getListOfDoorgrips(){
        return new ArrayList<>(wall.getListOfDoorGrips());
    }

    public void selectDoor(int position){
        wall.selectDoor(position);
        wallObject.setValue(wall);
    }
    public void selectGlass(int position){
        wall.selectGlass(position);
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
    public void sethasSpecielGlass(Boolean clicked){
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
    public void heightPickedFromDropDown(int position){
        wall.setFinalHeightOfGlas(position);
        wallObject.setValue(wall);
    }
    public void widthPickedFromDropDown(int position){
        wall.setFinalLengthOfGlas(position);
        wallObject.setValue(wall);
    }

    public ArrayList<Integer> getAdapterForFag(){
        return wall.getFagliste();
    }
    public ArrayList<Integer> getAdapterForGlas(){ return wall.getGlasliste(); }


    public String getInfoAboutWall(){
        return "Glases højde er  " +
                wall.getFinalHeightOfGlas() + " cm." +"\n" +
                "glasses brede er " +
                wall.getFinalLengthOfGlas() +  " cm." +"\n" +
                "Væggens pris er " +
                wall.getPriceOfWall() + " kr.";
    }
}
