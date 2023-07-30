package com.laafiexpress.View.Utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.laafiexpress.Model.CentreDeSante;
import com.laafiexpress.Model.Consult;
import com.laafiexpress.Model.Doctor;
import com.laafiexpress.Model.LaafiUser;
import com.laafiexpress.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




/**
 * Created by Laty 26 PHARAHON entertainment on 06/06/2019.
 */
public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Integer>> mDate = new MutableLiveData<>();
//    private MutableLiveData<List<Integer>> mDatefin = new MutableLiveData<>();
    private List<CentreDeSante> mCentreDeSanteList = new ArrayList<>();
    private List<Doctor> mDoctorList = new ArrayList<>();
    private List<Doctor> mSearchDoctorList = new ArrayList<>();
    private LaafiUser mUser ;
    private List<Consult> mConsultList = new ArrayList<>();
    private boolean debut = true;
    private String mSpeciality;
    private CentreDeSante mCentreDeSante;

//    constructor
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mUser = new LaafiUser("sanouelvis@lafiiexpress.com", "Sanou Elvis", 26,
                "male", "Ouagadougou", "62585323" );
        populate();
    }

    public MutableLiveData<List<Integer>> getDate() {
        return mDate;
    }

//    public MutableLiveData<List<Integer>> getDatefin() {
//        return mDatefin;
//    }

    public void setDate(List<Integer> date) {
        if (debut) {
            mDate.setValue(date);
        }
    }

    public void setDebut(boolean debut) {
        this.debut = debut;
    }

    public void setConsultList(List<Consult> consultList) {
        mConsultList = consultList;
    }

    public String getSpeciality() {
        return mSpeciality;
    }

    public void setSpeciality(String speciality) {
        mSpeciality = speciality;
    }

    public List<Doctor> getSearchDoctorList() {
        return mSearchDoctorList;
    }

    public void setSearchDoctorList(List<Doctor> searchDoctorList) {
        mSearchDoctorList = searchDoctorList;
    }

    public CentreDeSante getCentreDeSante() {
        return mCentreDeSante;
    }

    public void setCentreDeSante(CentreDeSante centreDeSante) {
        mCentreDeSante = centreDeSante;
    }

    //    public CentreDeSante getCentreDeCente(String name){
//        CentreDeSante centreDeSantef = new CentreDeSante();
//        for(CentreDeSante centreDeSante:mCentreDeSanteList){
//            if (centreDeSante.getName().equals(name)){
//                centreDeSantef = centreDeSante;
//                break;
//            }
//        }
//        return centreDeSantef;
//    }

    public List<CentreDeSante> getCentreDeSanteList(String speciality) {
        List<CentreDeSante> centreDeSantes = new ArrayList<>();
        for (CentreDeSante centreDeSante:mCentreDeSanteList){
            if (centreDeSante.getSpecialiteList().contains(speciality)){
                centreDeSantes.add(centreDeSante);
            }
        }
        return centreDeSantes;
    }

    public List<CentreDeSante> getCentreDeSanteList() {
        return mCentreDeSanteList;
    }

    public List<Doctor> getDoctorList() {
        return mDoctorList;
    }

    public LaafiUser getUser() {
        return mUser;
    }

    public List<Consult> getConsultList() {
        return mConsultList;
    }

    public void addConsultList(Consult consult) {
//        Calendar now = Calendar.getInstance();
//        if (now.compareTo(consult.getEndTime())>=0){
//            consult.setStatut(true);
//            if (consult.getUserId().equals(mUser.getMail())){
//                if (!consult.isStatut()){
//                    consult.setColor(ContextCompat.getColor(getApplication(),R.color.coloCustomLPrimaryLight));
//                }
//                else {
//                    consult.setColor(ContextCompat.getColor(getApplication(),R.color.colorAccentLight));
//                }
//            }
//            else {
//
//                consult.setColor(ContextCompat.getColor(getApplication(),R.color.colorOtherConsult));
//            }
//        }
        Doctor doc = findDoc(consult.getDoctorID());
//        List<Consult> t;
//        t = mConsultList;
        this.mConsultList.add(consult);
        System.out.println("add_consult t!=null: "+consult.getLocation()+ ""+ consult.getName());
//        System.out.println("add_consult t!=null: "+mConsultList.get(mConsultList.size()-1).getLocation()+ ""+ mConsultList.get(mConsultList.size()-1).getEndTime());
        int index = mDoctorList.indexOf(doc);
        doc.addConsult(consult.getId());
        mDoctorList.set(index,doc);
        if (consult.getUserId().equals(mUser.getMail())){
            mUser.addConsult(consult.getId());
        }

//        mConsultList = t;
//        for (int i=0; i<mConsultList.size();i++){
//            System.out.println("add_consult view_model: "+mConsultList.get(i).getEndTime().get(Calendar.DAY_OF_MONTH));
//            System.out.println("add_consult view_model : "+mConsultList.get(i).getEndTime().get(Calendar.HOUR_OF_DAY));
//        }

    }

    private void populate() {

//        list doc
        Doctor doc = new Doctor("Doc_1", "Ouedraogo Stephane", "ORL"
        , getApplication().getString(R.string.homme), 1);
        mDoctorList.add(doc);

        doc = new Doctor("Doc_2", "Boly Mariam", "Chirurgie"
                , getApplication().getString(R.string.femme), 5);
        mDoctorList.add(doc);

        doc = new Doctor("Doc_3", "Borro Kevin", "Psycologie"
                , getApplication().getString(R.string.homme), 2);
        mDoctorList.add(doc);

        doc = new Doctor("Doc_4", "Diallo Alida", "Pédiatrie"
                , getApplication().getString(R.string.femme), 1);
        mDoctorList.add(doc);
        doc = new Doctor("Doc_5", "Compaore Lucien", "Medecine generale"
                , getApplication().getString(R.string.homme), 1);
        mDoctorList.add(doc);

        doc = new Doctor("Doc_6", "Ildoudo Anita", "Psycologie"
                , getApplication().getString(R.string.femme), 2);
        mDoctorList.add(doc);

        doc = new Doctor("Doc_7", "Ilboudo harold", "Pédiatrie"
                , getApplication().getString(R.string.homme), 1);
        mDoctorList.add(doc);
        doc = new Doctor("Doc_8", "Bouda Aida", "Medecine generale"
                , getApplication().getString(R.string.femme), 1);
        mDoctorList.add(doc);

        doc = new Doctor("Doc_9", "Zampe Mohamed", "Medecine generale"
                , getApplication().getString(R.string.femme), 1);
        mDoctorList.add(doc);

        doc = new Doctor("Doc_10", "Dobenon Valerie", "Medecine generale"
                , getApplication().getString(R.string.femme), 1);
        mDoctorList.add(doc);

//        liste centre de  sante
        CentreDeSante tempC = new CentreDeSante(getApplication().getString(R.string.clinique1)
                , getApplication().getString(R.string.city), 4.5f, "");
        tempC.setSpecialiteList(getRandomSpeciality());
        tempC.setListDoctor(putDoctor(tempC.getSpecialiteList()));
        mCentreDeSanteList.add(tempC);

        tempC = new CentreDeSante(getApplication().getString(R.string.clinique2)
                , getApplication().getString(R.string.city), 3.5f, "");
        tempC.setSpecialiteList(getRandomSpeciality());
        tempC.setListDoctor(putDoctor(tempC.getSpecialiteList()));
        mCentreDeSanteList.add(tempC);

        tempC = new CentreDeSante(getApplication().getString(R.string.clinique3)
                , getApplication().getString(R.string.city), 3, "");
        tempC.setSpecialiteList(getRandomSpeciality());
        tempC.setListDoctor(putDoctor(tempC.getSpecialiteList()));
        mCentreDeSanteList.add(tempC);

        tempC = new CentreDeSante(getApplication().getString(R.string.clinique4)
                , getApplication().getString(R.string.city), 2.5f, "");
        tempC.setSpecialiteList(getRandomSpeciality());
        tempC.setListDoctor(putDoctor(tempC.getSpecialiteList()));
        mCentreDeSanteList.add(tempC);

        tempC = new CentreDeSante(getApplication().getString(R.string.clinique5)
                , getApplication().getString(R.string.city), 3.25f, "");
        tempC.setSpecialiteList(getRandomSpeciality());
        tempC.setListDoctor(putDoctor(tempC.getSpecialiteList()));
        mCentreDeSanteList.add(tempC);

        tempC = new CentreDeSante(getApplication().getString(R.string.clinique6)
                , getApplication().getString(R.string.city), 4.1f, "");
        tempC.setSpecialiteList(getRandomSpeciality());
        tempC.setListDoctor(putDoctor(tempC.getSpecialiteList()));
        mCentreDeSanteList.add(tempC);


//        consults

        Calendar calendar = Calendar.getInstance();
        Calendar calendar1;
//        System.out.println(calendar.getTime().toString());
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE,0);
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        calendar1 = (Calendar) calendar.clone();
        calendar1.add(Calendar.HOUR_OF_DAY, 1);
        calendar1.add(Calendar.MINUTE,-1);
        Consult consult = new  Consult(255
                , mCentreDeSanteList.get(1).getSpecialiteList().get(2)
                ,mCentreDeSanteList.get(1).getName(), (Calendar) calendar.clone(),(Calendar)calendar1.clone(),mUser.getMail(), mDoctorList.get(7).getName());
        consult.setColor(getApplication().getResources().getColor(R.color.colorPrimaryLight));
        consult.setStatut(false);
        System.out.println("consult : "+consult.getLocation()+ ""+ consult.getEndTime().toString());
        addConsultList(consult);

        calendar = (Calendar)calendar.clone();
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        calendar1 = (Calendar) calendar.clone();
        calendar1.add(Calendar.HOUR_OF_DAY, 2);
        calendar1.add(Calendar.MINUTE,-1);
        Consult consult1 = new Consult(247
                ,  mCentreDeSanteList.get(3).getSpecialiteList().get(2)
                ,mCentreDeSanteList.get(3).getName(), (Calendar) calendar.clone(),(Calendar)calendar1.clone(),mUser.getMail(), mDoctorList.get(6).getName());
        consult1.setColor(getApplication().getResources().getColor(R.color.colorAccentLight));
        consult1.setStatut(true);
        System.out.println("consult1 : "+consult1.getLocation()+ ""+ consult1.getEndTime().toString());
        addConsultList(consult1);

        calendar = (Calendar)calendar.clone();
        calendar.add(Calendar.DAY_OF_MONTH, 4);
        calendar1 = (Calendar) calendar.clone();
        calendar1.add(Calendar.HOUR_OF_DAY, 5);
        calendar1.add(Calendar.MINUTE,-1);
        Consult consult2 = new Consult(260
                ,  mCentreDeSanteList.get(2).getSpecialiteList().get(1)
                ,mCentreDeSanteList.get(2).getName(), (Calendar) calendar.clone(),(Calendar)calendar1.clone(),mUser.getMail(), mDoctorList.get(2).getName());
        consult2.setColor(getApplication().getResources().getColor(R.color.colorPrimaryLight));
        consult2.setStatut(false);
        System.out.println("consult2 : "+consult2.getLocation()+ ""+ consult2.getEndTime().toString());
        addConsultList(consult2);
    }

    private List<String> getRandomSpeciality() {
        List<String> list = new ArrayList<>();
        List<String> tempS = Arrays.asList(getApplication().getResources().getStringArray(R.array.speciality_list));
        int oldI1=55;
        int oldI2=66;
        for (int j = 0; j <3 ; j++) {
            int i = (int) (Math.random() * tempS.size());
            while (i==oldI1 || i==oldI2 || i==0){
                i = (int) (Math.random() * tempS.size());
            }
            if (oldI1==55){
                oldI1=i;
            }
            if (oldI2==66){
                oldI2=i;
            }
            list.add(tempS.get(i));
        }
        return list;
    }

    public Doctor findDoc(String id){
        Doctor doctor = new Doctor();
        for (int i = 0; i <mDoctorList.size(); i++){
            doctor = mDoctorList.get(i);
            if (doctor.getId().equals(id)){
                break;
            }
        }
        return doctor;
    }

    private List<String> findDocBySpecialaty(String speciality){
        Doctor doctor;
        List<String> docs = new ArrayList<>();
        for (int i = 0; i < mDoctorList.size(); i++){
            doctor = mDoctorList.get(i);
            if (doctor.getSpeciality().equals(speciality)){
                docs.add(doctor.getId());
            }
        }
        return docs;
    }

    private List<String> putDoctor(List<String> specialite){
        List<String> docs = new ArrayList<>();
        for (int i = 0; i < specialite.size(); i++){
            List<String> result = findDocBySpecialaty(specialite.get(i));
            if (result.size()==1){
                docs.add(result.get(0));
            }
            else {
                docs.addAll(result);
            }

        }
        return docs;
    }

    private int getRandomInt(int limit){
        int t = (int) (Math.random() * limit);
        while (t==0){
            t = (int) (Math.random() * limit);
        }
        return t;
    }

    public void populateDocConsult(Doctor doctor, String speciality, String centreDeSante){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE,0);
        for (int i=0; i<10; i++){
            Calendar calendar1;
//        System.out.println(calendar.getTime().toString());
            int numConsultJour = 24/ doctor.getDureeConsulte();
            int break1 = getRandomInt(numConsultJour);
            int break2 = getRandomInt(numConsultJour);
            while (break1==break2){
                break1 = getRandomInt(numConsultJour);
            }
            if (i==0){
                numConsultJour = (24-calendar.get(Calendar.HOUR_OF_DAY))/ doctor.getDureeConsulte();
                if (numConsultJour>=2){
                    break1 = getRandomInt(numConsultJour);
                    for (int j =0; j<numConsultJour-1;j++){
                        if (j!=break1){
                            calendar1 = (Calendar) calendar.clone();
                            calendar1.add(Calendar.HOUR_OF_DAY, doctor.getDureeConsulte());
                            calendar1.add(Calendar.MINUTE,-1);
                            Consult consult = new  Consult(System.currentTimeMillis(), "","",
                                    (Calendar) calendar.clone(),(Calendar)calendar1.clone(),"other", doctor.getName());
                            consult.setColor(getApplication().getResources().getColor(R.color.colorOtherConsult));
                            System.out.println("consult : "+consult.getLocation()+ ""+ consult.getEndTime().toString());
                            addConsultList(consult);
                        }
                        calendar.add(Calendar.HOUR_OF_DAY, doctor.getDureeConsulte());
                    }
//                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
            else {
                for (int j =0; j<numConsultJour-2;j++){
                    if (j!=break1 && j!=break2){
                        calendar1 = (Calendar) calendar.clone();
                        calendar1.add(Calendar.HOUR_OF_DAY, doctor.getDureeConsulte());
                        calendar1.add(Calendar.MINUTE,-1);
                        Consult consult = new  Consult(System.currentTimeMillis(), "","",
                                (Calendar) calendar.clone(),(Calendar)calendar1.clone(),"other", doctor.getName());
                        consult.setColor(getApplication().getResources().getColor(R.color.colorOtherConsult));
                        System.out.println("consult : "+consult.getLocation()+ ""+ consult.getEndTime().toString());
                        addConsultList(consult);
                    }
                    calendar.add(Calendar.HOUR_OF_DAY, doctor.getDureeConsulte());
                }
//                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        Calendar t = Calendar.getInstance();
        Calendar d = Calendar.getInstance();
        d.add(Calendar.DAY_OF_MONTH,-1);
        Consult consult = new  Consult(System.currentTimeMillis(), "","",
                (Calendar) d.clone(),(Calendar)t.clone(),"other", doctor.getName());
        consult.setColor(getApplication().getResources().getColor(R.color.colorOtherConsult));
        System.out.println("consult : "+consult.getLocation()+ ""+ consult.getEndTime().toString());
        addConsultList(consult);
    }
}
