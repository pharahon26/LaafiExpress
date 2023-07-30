package com.laafiexpress.View.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.laafiexpress.Model.CentreDeSante;
import com.laafiexpress.Model.Doctor;
import com.laafiexpress.R;
import com.laafiexpress.View.Utils.CentreDeSanteListAdaptator;
import com.laafiexpress.View.Utils.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewConsultFragment extends Fragment {

    private Button mNext;
    private Spinner mSpecialitySpinner;
    private Group mCentreSanteGroup;
    private Group mprefGroup;
    private ChipGroup mChipGroup;
    private CentreDeSante mCentreDeSante;
    private MainActivityViewModel mViewModel;
    private RecyclerView mCentreDeSanteRecyclerView;
    private List<CentreDeSante> mCentreDeSanteList = new ArrayList<>();
    private int checked = 0;

    public NewConsultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_consult, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCentreSanteGroup = new Group(getContext());
        mNext = view.findViewById(R.id.new_consult_frag_next_button);
        mSpecialitySpinner = view.findViewById(R.id.new_consult_frag_specialite_spinner);
        mCentreSanteGroup = view.findViewById(R.id.new_consult_fragment_centre_de_sante_group);
        mprefGroup = view.findViewById(R.id.new_consult_frag_pref_group);
        mCentreDeSanteRecyclerView = view.findViewById(R.id.new_consult_frag_clinic_recycler_view);

        mChipGroup = view.findViewById(R.id.new_consult_frag_preference_chip_group);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

//        spinner adapter
        final ArrayAdapter<CharSequence> specialitySpinnerAdapter = ArrayAdapter
                .createFromResource(getContext(), R.array.speciality_list,
                        android.R.layout.simple_spinner_item);
        specialitySpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpecialitySpinner.setAdapter(specialitySpinnerAdapter);

        final CentreDeSanteListAdaptator centreDeSanteListAdaptator = new CentreDeSanteListAdaptator();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mCentreDeSanteRecyclerView.setLayoutManager(layoutManager);
        mCentreDeSanteRecyclerView.setHasFixedSize(true);

        mCentreDeSanteRecyclerView.setAdapter(centreDeSanteListAdaptator);


//        spinner listener
        mSpecialitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (specialitySpinnerAdapter.getItem(position).toString().equals("none")) {
                    mCentreSanteGroup.setVisibility(View.GONE);

                } else {
                    mViewModel.setSpeciality(specialitySpinnerAdapter.getItem(position).toString());
                    mCentreDeSanteList = mViewModel.getCentreDeSanteList(mViewModel.getSpeciality());
                    centreDeSanteListAdaptator.setCentreDeSantes(mCentreDeSanteList);
                    mCentreSanteGroup.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCentreSanteGroup.setVisibility(View.GONE);
            }
        });

        centreDeSanteListAdaptator.setOnCenterClickListener(new CentreDeSanteListAdaptator.OnCenterClickListener() {
            @Override
            public void onCenterClick(CentreDeSante centreDeSante, boolean check) {
                if (check){
                    checked++;
                }
                else {
                    checked--;
                }
                if (checked == 1){
                    mprefGroup.setVisibility(View.VISIBLE);
                    mCentreDeSante = centreDeSante;
                    mViewModel.setCentreDeSante(mCentreDeSante);
                }
                else {
                    mprefGroup.setVisibility(View.GONE);
                }
            }
        });

        mChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                List<Doctor> temp = new ArrayList<>();
                for (int i = 0; i<mCentreDeSante.getListDoctor().size();i++){
                    Doctor doctor = mViewModel.findDoc(mCentreDeSante.getListDoctor().get(i));
                    if (doctor.getSpeciality().equals(mViewModel.getSpeciality()) &&
                            doctor.getGender().contentEquals(((Chip)view.findViewById(checkedId)).getText())){
                        temp.add(doctor);
                    }
                }
                if (temp.size()==0){
                    Toast.makeText(getContext(),
                            "Cette clinique n'a pas de médecin "+((Chip)view.findViewById(checkedId)).getText()+
                            " pour cette specialité veuillez en sélectionner un autre genre ou un autre centre de santé",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    mViewModel.setSearchDoctorList(temp);
                    mNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) {
                            for (Doctor doctor:mViewModel.getSearchDoctorList()){
                                mViewModel.populateDocConsult(doctor,mViewModel.getSpeciality(),mViewModel.getCentreDeSante().getName());
                            }
                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    nextClick(v);
                                }
                            };
                            new Handler().postDelayed(runnable,200);
                        }
                    });
                }

            }
        });
    }

    private void nextClick(View v){
        Navigation.findNavController(v).navigate(R.id.action_newConsultFragment_to_chooseRDVFragment);
    }

}
