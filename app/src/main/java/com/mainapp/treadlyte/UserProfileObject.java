package com.mainapp.treadlyte;

import android.media.Image;

import java.util.Date;
import java.util.UUID;

/**
 * User Object that stores all of the users data collected from facebook
 */
public class UserProfileObject {

    private String mFirstName;
    private String mLastName;
    private Image mProfileImage;
    private UUID userCode;

    public UUID getUserCode() {
        return userCode;
    }


    public void setUserCode(UUID userCode) {
        this.userCode = userCode;
    }

    public UserProfileObject(String mFirstName, String mLastName, Image mProfileImage) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mProfileImage = mProfileImage;
        userCode = UUID.randomUUID();
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public Image getmProfileImage() {
        return mProfileImage;
    }

    public void setmProfileImage(Image mProfileImage) {
        this.mProfileImage = mProfileImage;
    }
}






