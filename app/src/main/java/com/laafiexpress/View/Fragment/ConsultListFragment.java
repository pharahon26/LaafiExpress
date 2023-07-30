package com.laafiexpress.View.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laafiexpress.Model.Consult;
import com.laafiexpress.R;
import com.laafiexpress.View.Utils.ConsultListAdaptor;
import com.laafiexpress.View.Utils.MainActivityViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultListFragment extends Fragment {

    private RecyclerView mConsultListRecyclerView;
    private MainActivityViewModel mViewModel;
    private TextView mNumberConsultDone;
    private TextView mNumberConsultPending;
    private List<Consult> mConsultList = new ArrayList<>();

    public ConsultListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mConsultListRecyclerView = view.findViewById(R.id.consult_list_frag_list);
        mNumberConsultDone = view.findViewById(R.id.consult_list_frag_done_date);
        mNumberConsultPending = view.findViewById(R.id.consult_list_frag_wainting_date);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

        final ConsultListAdaptor centreDeSanteListAdaptator = new ConsultListAdaptor();
        mConsultList = getUserConsult(mViewModel.getConsultList());
        centreDeSanteListAdaptator.setConsultList(mConsultList);
        fillTextView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mConsultListRecyclerView.setLayoutManager(layoutManager);
        mConsultListRecyclerView.setHasFixedSize(true);

        mConsultListRecyclerView.setAdapter(centreDeSanteListAdaptator);

    }

    private void fillTextView(){
        int done = 0;
        int waiting = 0;
        for (Consult consult : mConsultList){
            if (consult.isStatut()){
                done++;
            }
            else {
                waiting++;
            }
        }
        mNumberConsultDone.setText(String.valueOf(done));
        mNumberConsultPending.setText(String.valueOf(waiting));
    }

    private List<Consult> getUserConsult(List<Consult> consults){
        List<Consult> consultList = new ArrayList<>();
        List<Consult> temp = new ArrayList<>();
//        get the user consults
        for (Consult consult : consults){
            if (consult.getUserId().equals(mViewModel.getUser().getMail())){
                temp.add(consult);
            }
        }
//        sort the list
        Collections.sort(temp, new Comparator<Consult>() {
            @Override
            public int compare(Consult o1, Consult o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });
        for (int i = 0; i<temp.size(); i++){
            consultList.add(i,temp.get(temp.size()-(i+1)));
        }
        return consultList;
    }
}
