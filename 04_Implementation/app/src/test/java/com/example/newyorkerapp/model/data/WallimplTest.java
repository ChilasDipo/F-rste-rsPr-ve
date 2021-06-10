package com.example.newyorkerapp.model.data;

import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallimplTest {

    @Test
    void EC02(){
        TestableWallimpl wall = new TestableWallimpl();
        //First argument - specifies the expected exception.
        //Here it expects that code block will throw NumberFormatException
        //Second argument - is used to pass an executable code block or lambda expression
        Assertions.assertThrows(InputMangler.class, () -> wall.setHeightOfTheWall(0));
    }
    @Test
    void EC03(){
        TestableWallimpl wall = new TestableWallimpl();
        Assertions.assertThrows(HeightTooSmall.class, () -> wall.setHeightOfTheWall(9));
    }
    @Test
    void EC04(){
        TestableWallimpl wall = new TestableWallimpl();
        Assertions.assertThrows(HeightTooBig.class, () -> wall.setHeightOfTheWall(251));
    }
}