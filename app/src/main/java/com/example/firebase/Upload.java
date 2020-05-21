package com.example.firebase;

import com.google.firebase.storage.StorageReference;

public class Upload {
    private String mName;
    private String mImageUrl;
    public Upload(){

    }
    public Upload(String name,String imageurl){
        if(name.trim().equals("")){
            mName="No Name";
            mImageUrl=imageurl;
        }
        else {
            mName = name;
            mImageUrl = imageurl;
        }
    }
    public String getName(){
        return mName;
    }
    public void  setName(String name){
        mName=name;
    }
    public String getImageUrl(){
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl){
        mImageUrl=imageUrl;
    }
}
