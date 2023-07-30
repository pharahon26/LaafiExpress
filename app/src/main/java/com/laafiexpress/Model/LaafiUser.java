package com.laafiexpress.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laty 26 PHARAHON entertainment on 06/06/2019.
 */
public class LaafiUser {

    private String mMail;
    private String mName;
    private int mAge;
    private String mGender;
    private String mCity;
    private String mNumber;
    private List<Long> mListConsult = new ArrayList<>();

    public LaafiUser() {
    }

    public LaafiUser(String mail, String name, int age, String gender, String city, String number) {
        mMail = mail;
        mName = name;
        mAge = age;
        mGender = gender;
        mCity = city;
        mNumber = number;
    }

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        mMail = mail;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public List<Long> getListConsult() {
        return mListConsult;
    }

    public void setListConsult(List<Long> listConsult) {
        mListConsult = listConsult;
    }

    public void addConsult(Long consultID){
        mListConsult.add(consultID);
    }
}
