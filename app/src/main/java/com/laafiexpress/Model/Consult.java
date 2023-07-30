package com.laafiexpress.Model;

import com.alamkanak.weekview.WeekViewDisplayable;
import com.alamkanak.weekview.WeekViewEvent;
import com.laafiexpress.R;

import java.util.Calendar;

/**
 * Created by Laty 26 PHARAHON entertainment on 06/06/2019.
 */
public class Consult implements WeekViewDisplayable<Consult> {

    private String mUserId;
    private String mDoctorID;
    private boolean mStatut;
    private boolean mContinue;
    private String mNextConsultId;
    private int color;

    private long mId;
    private String mName;
    private String mLocation;
    private Calendar mStartTime;
    private Calendar mEndTime;

    public Consult() {
    }

    public Consult(long id, String name, String location, Calendar startTime, Calendar endTime,String userId, String doctorID) {
        mId = id;
        mName = name;
        mLocation = location;
        mStartTime = startTime;
        mEndTime = endTime;

        mUserId = userId;
        mDoctorID = doctorID;
        mStatut = false;
        mContinue = false;
    }



    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getDoctorID() {
        return mDoctorID;
    }

    public void setDoctorID(String doctorID) {
        mDoctorID = doctorID;
    }

    public boolean isStatut() {
        return mStatut;
    }

    public void setStatut(boolean statut) {
        mStatut = statut;
    }

    public boolean isContinue() {
        return mContinue;
    }

    public void setContinue(boolean aContinue) {
        mContinue = aContinue;
    }

    public String getNextConsultId() {
        return mNextConsultId;
    }

    public void setNextConsultId(String nextConsultId) {
        mNextConsultId = nextConsultId;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getLocation() {
        return mLocation;
    }

    public Calendar getStartTime() {
        return mStartTime;
    }

    public Calendar getEndTime() {
        return mEndTime;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public WeekViewEvent<Consult> toWeekViewEvent() {
        WeekViewEvent.Style style = new WeekViewEvent.Style.Builder().setBackgroundColor(color).setTextStrikeThrough(false).build();


        return new WeekViewEvent.Builder<Consult>().setId(mId).
                setStartTime(mStartTime).
                setTitle(mName).
                setEndTime(mEndTime).
                setAllDay(false).
                setLocation(mLocation).
                setStyle(style).
                setData(this).build();
    }
}

