package com.example.newyorkerapp.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newyorkerapp.model.data.Wall;
import com.example.newyorkerapp.model.data.Wallimpl;
import com.example.newyorkerapp.model.exceptions.AmountOfGlassOrFagUnableToBeCalculated;
import com.example.newyorkerapp.model.exceptions.InputMangler;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Double> price;
    private MutableLiveData<Wallimpl> wallObject;
    private Wallimpl wall;

    public MainActivityViewModel() {
        wallObject = new MutableLiveData<>();
        wall = new Wallimpl();
    }
    //https://medium.com/@atifmukhtar/mvvm-java-model-view-view-model-livedata-148475d7f383


    public void setheight(int height) {
        try {
            wall.setHeight(height);
        } catch (InputMangler inputMangler) {
            inputMangler.printStackTrace();
        }
        wallObject.setValue(wall);
    }

    public void setlenght(int lenght) {
        try {
            wall.setLength(lenght);
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
    public LiveData<Wallimpl> getWall() {
        if (wallObject == null) {
            wallObject = new MutableLiveData<>();
        }
        return wallObject;
    }

    public double getPrice() {
        double price = 0;
        try {
             price = wall.getPriceOfWall();
        } catch (AmountOfGlassOrFagUnableToBeCalculated amountOfGlassOrFagUnableToBeCalculated) {
            amountOfGlassOrFagUnableToBeCalculated.printStackTrace();
        }
        return price;
    }


}
