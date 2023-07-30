package com.laafiexpress.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laty 26 PHARAHON entertainment on 05/06/2019.
 */
public class Doctor {

    private String mId;
    private String mName;
    private String mSpeciality;
    private String mGender;
    private int mDureeConsulte;
    private List<Long> mConsultList = new ArrayList<>();

    public Doctor(String id, String name, String speciality, String gender, int dureeConsulte) {
        mId = id;
        mName = name;
        mSpeciality = speciality;
        mGender = gender;
        mDureeConsulte = dureeConsulte;
    }

    public Doctor() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSpeciality() {
        return mSpeciality;
    }

    public void setSpeciality(String speciality) {
        mSpeciality = speciality;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public int getDureeConsulte() {
        return mDureeConsulte;
    }

    public void setDureeConsulte(int dureeConsulte) {
        mDureeConsulte = dureeConsulte;
    }

    public String getId() {
        return mId;
    }

    public List<Long> getConsultList() {
        return mConsultList;
    }

    public void addConsult(Long consultID){
        mConsultList.add(consultID);
    }
}
