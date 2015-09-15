package com.treadlyte.mainapp.model;

import android.media.Image;

import java.util.UUID;

/**
 * Created by richardblakeney-williams on 10/09/15.
 */
public class Product {

    private UUID mId;
    private String Name;
    private Image avatar;
    private String price;
    private String description;
    private String sellerId;
    private String location;

    public Product(UUID mId, String name, Image avatar, String price, String description, String sellerId, String location) {
        this.mId = mId;
        Name = name;
        this.avatar = avatar;
        this.price = price;
        this.description = description;
        this.sellerId = sellerId;
        this.location = location;
    }

    //Testing Contructor. Will get to this later
    public Product(String name, String price) {
        Name = name;
        this.price = price;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
