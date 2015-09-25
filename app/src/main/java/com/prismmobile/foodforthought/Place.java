package com.prismmobile.foodforthought;

/**
 * A custom Place object
 * Created by benjunya on 8/25/15.
 */
public class Place {
    private String name;
    private String address;
    private String type;
    private boolean icon;


    Place(String name, String address, boolean icon) {
        this.name = name;
        this.address = address;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean getIcon() {
        return icon;
    }

}
