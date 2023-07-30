package com.laafiexpress.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laty 26 PHARAHON entertainment on 05/06/2019.
 */
public class CentreDeSante {

    private String mName;
    private String mCity;
    private float mRate;
    private String mPicture;
    private List<String> mSpecialiteList = new ArrayList<>();
    private List<String> mListDoctor = new ArrayList<>();

    public CentreDeSante() {
    }

    public CentreDeSante(String name, String city, float rate, String picture) {
        mName = name;
        mCity = city;
        mRate = rate;
        mPicture = picture;

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public float getRate() {
        return mRate;
    }

    public void setRate(float rate) {
        mRate = rate;
    }

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String picture) {
        mPicture = picture;
    }

    public List<String> getListDoctor() {
        return mListDoctor;
    }

    public void setListDoctor(List<String> listDoctor) {
        mListDoctor = listDoctor;
    }

    public List<String> getSpecialiteList() {
        return mSpecialiteList;
    }

    public void setSpecialiteList(List<String> specialiteList) {
        mSpecialiteList = specialiteList;
    }
}
