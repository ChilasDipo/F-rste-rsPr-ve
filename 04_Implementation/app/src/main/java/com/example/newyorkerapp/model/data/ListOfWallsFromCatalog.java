package com.example.newyorkerapp.model.data;

import android.util.Log;

import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;

import java.util.ArrayList;
import java.util.List;

public class ListOfWallsFromCatalog {
    static List<Wallimpl> wallList = new ArrayList<Wallimpl>();

    public static Wallimpl getWallFromList(int index){
        return wallList.get(index);
    }


    static Integer[] lengthList = {40,75,75,240,200,75,150};
    static Integer[] heightList = {180,180,180,180,180,180,180};
    static Boolean[] doorList = {false,false,false,true,true,true,true};



    public static void buildListOfWallsFromCatalog(){
        for (int i = 0; i < lengthList.length ; i++) {
            try {
                wallList.add(new Wallimpl(lengthList[i],heightList[i],doorList[i]));
                Log.d("building", "I am building" + i);
            } catch (HeightTooSmall heightTooSmall) {
                heightTooSmall.printStackTrace();
                Log.d("building", "I am heightTooSmall" );
            } catch (InputMangler inputMangler) {
                inputMangler.printStackTrace();
                Log.d("building", "I am inputMangler" );
            } catch (HeightTooBig heightTooBig) {
                heightTooBig.printStackTrace();
                Log.d("building", "I am HeightTooBig" );
            }
        }
        Log.d("building", "I am buildingDone" );
    }
}
