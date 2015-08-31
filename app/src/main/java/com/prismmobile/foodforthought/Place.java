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


    Place(String name, String address, String type, boolean icon) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIcon() {
        return icon;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }
}
