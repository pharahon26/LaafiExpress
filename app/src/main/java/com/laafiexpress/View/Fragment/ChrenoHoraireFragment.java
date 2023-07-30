package com.laafiexpress.View.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.laafiexpress.R;
import com.laafiexpress.View.Utils.MainActivityViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChrenoHoraireFragment extends Fragment {

    private Button mNext;
    private Button mDateBtn;
    private TextView mDate;
    private Calendar mCalendar;
    private MainActivityViewModel mViewModel;

    public ChrenoHoraireFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chreno_horaire, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNext = view.findViewById(R.id.chreno_horaire_frag_next_button);
        mDateBtn = view.findViewById(R.id.chreno_horaire_frag_date_btn);
        mDate = view.findViewById(R.id.chreno_horaire_frag_date_text);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

        mDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Calendar date = Calendar.getInstance();

                Calendar date1 = Calendar.getInstance();
                date1.set(mViewModel.getDate().getValue().get(0), mViewModel.getDate().getValue().get(1),
                        mViewModel.getDate().getValue().get(2));

                if (s != getString(R.string.date_sample) && date.compareTo(date1) < 0) {
                    mNext.setEnabled(true);
                    mNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Navigation.findNavController(v).navigate(R.id.action_chrenoHoraireFragment_to_newConsultFragment);
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "choose an other date", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setDebut(true);
                Navigation.findNavController(v).navigate(R.id.dialog_date_picker);
                mViewModel.getDate().observe(getActivity(), new Observer<List<Integer>>() {
                    @Override
                    public void onChanged(List<Integer> integers) {
                        mDate.setText(DateFormat.getDateInstance()
                                .format(getDate(integers.get(0), integers.get(1),
                                        integers.get(2)).getTime()));
                        mCalendar = getDate(integers.get(0), integers.get(1),
                                integers.get(2));
                    }
                });
            }
        });
    }


    public static Calendar getDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return calendar;

    }
}
