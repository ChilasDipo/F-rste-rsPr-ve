package com.example.newyorkerapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerapp.model.data.Wallimpl;
import com.example.newyorkerapp.model.exceptions.InputMangler;
import com.example.newyorkerapp.persistence.FireBaseDAOimpl;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Double> price;
    private MutableLiveData<Wallimpl> wallObject;
    private Wallimpl wall;
    FireBaseDAOimpl fireBaseDAOimpl;
    ArrayList<Wallimpl> listOfWalls = new ArrayList<Wallimpl>();
    public MainActivityViewModel() {
        wallObject = new MutableLiveData<>();
        fireBaseDAOimpl = new FireBaseDAOimpl();
        addWall();
        selectWall();
    }
    //https://medium.com/@atifmukhtar/mvvm-java-model-view-view-model-livedata-148475d7f383
    public LiveData<Wallimpl> getWall() {
        if (wallObject == null) {
            wallObject = new MutableLiveData<>();
        }
        return wallObject;
    }

    void selectWall(){
        wall = listOfWalls.get(0);
    }
        // disse 2 funktioner kan ændres så man kan vælge et væg obejct
    void addWall(){
        listOfWalls.add(new Wallimpl());
    }

    public void setheight(int height) {
        try {
            wall.setHeightOfTheWall(height);
        } catch (InputMangler inputMangler) {
            inputMangler.printStackTrace();
        }
        wallObject.setValue(wall);

    }
    public ArrayList<String> getListOfDoors(){
        ArrayList<String> nameListForDoors  = new ArrayList<String>(fireBaseDAOimpl.getListOfDoors().keySet());
        ArrayList<Integer> priceListForDoors  = new ArrayList<Integer>(fireBaseDAOimpl.getListOfDoors().values());
        //price.setPriceListForDoors();
        return nameListForDoors;

    }
    public ArrayList<String> getListOfGlas(){
        ArrayList<String> nameListForGlas  = new ArrayList<String>(fireBaseDAOimpl.getListOfGlas().keySet());
        ArrayList<Integer> priceListForglas = new ArrayList<Integer>(fireBaseDAOimpl.getListOfGlas().values());
        //Price.setPriceLsitForglas();
        return nameListForGlas;
    }

    public void setlenght(int lenght) {
        try {
            wall.setLengthOfTheWall(lenght);
        } catch (InputMangler inputMangler) {
            inputMangler.printStackTrace();
        }
        wallObject.setValue(wall);
    }
    public void heightPicedFromDropDown(int position){
        wall.setFinalHeightOfGlas(position);
        wallObject.setValue(wall);
    }
    public void widthPicedFromDropDown(int position){
        wall.setFinalLengthOfGlas(position);
        wallObject.setValue(wall);
    }

    public ArrayList<Integer> getAdapterForFag(){
        return wall.getFagliste();
    }
    public ArrayList<Integer> getAdapterForGlas(){
        return wall.getGlasliste();
    }

    public String getInfoAboutWall(){
        StringBuilder string = new StringBuilder();
        string.append("Glases højde er  ");
        string.append(wall.getFinalHeightOfGlas()).append("\n");
        string.append("glasses brede er ");
        string.append(wall.getFinalLengthOfGlas()).append("\n");
        string.append("Væggens pris er ");
        string.append(wall.getPriceOfWall());
        return string.toString();
    }
}
