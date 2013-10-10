package com.example.dressup;

/**
 * Created by Maria on 9/8/13.
 */
public class Dress {

    String name;
    int image;

    Dress(String _name, int _image) {
        name = _name;
        image = _image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}