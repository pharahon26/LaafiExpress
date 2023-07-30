package com.laafiexpress.View.Dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.laafiexpress.View.Utils.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Laty 26 PHARAHON entertainment on 06/06/2019.
 */
public class DatePickerDialog extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener {

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        List<Integer> date = new ArrayList<>();
        date.add(year);
        date.add(month);
        date.add(dayOfMonth);
        ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class).setDate(date);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        return new android.app.DatePickerDialog(getActivity(), this,year,month,day);
    }


}
