package com.laafiexpress.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentResolver;
import android.os.Bundle;

import com.laafiexpress.Model.CentreDeSante;
import com.laafiexpress.Model.Consult;
import com.laafiexpress.Model.Doctor;
import com.laafiexpress.Model.LaafiUser;
import com.laafiexpress.R;
import com.laafiexpress.View.Utils.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CentreDeSante> mCentreDeSanteList = new ArrayList<>();
    private List<Doctor> mDoctorList = new ArrayList<>();
    private List mConsults = new ArrayList<>();
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);

    }


}
