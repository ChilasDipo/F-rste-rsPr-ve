package com.example.newyorkerapp.model.data;

import com.example.newyorkerapp.model.exceptions.HeightTooBig;
import com.example.newyorkerapp.model.exceptions.HeightTooSmall;
import com.example.newyorkerapp.model.exceptions.InputMangler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WallimplTest {

    @Test
    void setHeightOfTheWall() throws HeightTooSmall, InputMangler, HeightTooBig {
        Wallimpl wall = new Wallimpl();
        //First argument - specifies the expected exception.
        //Here it expects that code block will throw NumberFormatException
        //Second argument - is used to pass an executable code block or lambda expression
        Assertions.assertThrows(InputMangler.class, () -> {
            wall.setHeightOfTheWall(0);
        });
    }
}