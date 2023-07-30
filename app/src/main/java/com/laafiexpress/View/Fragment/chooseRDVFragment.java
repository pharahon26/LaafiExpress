package com.laafiexpress.View.Fragment;


import android.content.ContentResolver;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alamkanak.weekview.EmptyViewClickListener;
import com.alamkanak.weekview.EventClickListener;
import com.alamkanak.weekview.MonthChangeListener;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewDisplayable;
import com.laafiexpress.Model.Consult;
import com.laafiexpress.Model.LaafiUser;
import com.laafiexpress.R;
import com.laafiexpress.View.Utils.MainActivityViewModel;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class chooseRDVFragment extends Fragment {

    private Button mConfirme;
    private WeekView mCalendarView;
    private MainActivityViewModel mViewModel;
    private LaafiUser mUser = new LaafiUser("sanouelvis@lafiiexpress.com", "Sanou Elvis", 26,
            "male", "Ouagadougou", "62585323" );
    private List<Consult> mConsultList = new ArrayList<>();
    private Calendar mCalendar = Calendar.getInstance();
    private int duree;

    public chooseRDVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_rdv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mConfirme = view.findViewById(R.id.choose_frag_confirme_button);
        mCalendarView = (WeekView) view.findViewById(R.id.choose_frag_calendar);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        mCalendarView.goToToday();
        mCalendarView.goToCurrentTime();
        mCalendar.add(Calendar.DAY_OF_MONTH,-1);
//        setDates();
        mCalendarView.setMinDate(mCalendar);
        final Calendar c2 = ChrenoHoraireFragment.getDate(mViewModel.getDate().getValue().get(0), mViewModel.getDate().getValue().get(1),
                mViewModel.getDate().getValue().get(2));
        mCalendarView.setMaxDate(c2);
        duree = mViewModel.getSearchDoctorList().get(0).getDureeConsulte();

        mCalendarView.setMonthChangeListener(new MonthChangeListener() {
            @Override
            public List<WeekViewDisplayable> onMonthChange(Calendar calendar, Calendar calendar1) {
                List<WeekViewDisplayable> displayableList = new ArrayList<>();
//                if (mViewModel.getSearchDoctorList().size()==1){
//                    for (Consult consult : mViewModel.getConsultList()){
//                        displayableList.add(consult.toWeekViewEvent());
//                    }
//                }
//                else {
//                    for (Consult consult : mConsultList) {
//                        int doublon = 0;
//                        for (Consult consult1 : mConsultList){
//                            if(consult.getStartTime().compareTo(consult1.getStartTime()) == 0){
//                                doublon++;
//                            }
//                        }
//                        if (doublon==mViewModel.getSearchDoctorList().size()){
//                            displayableList.add(consult.toWeekViewEvent());
//                        }
//
//                    }
//                }
                for (Consult consult : mViewModel.getConsultList()){
                    if (consult.getStartTime().compareTo(calendar)>=0 && consult.getEndTime().compareTo(calendar1)<=0){
                        displayableList.add(consult.toWeekViewEvent());
                        mConsultList.add(consult);
                    }
                }
                return displayableList;
            }
        });

        mConfirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_chooseRDVFragment_to_consultListFragment);
            }
        });

        mCalendarView.setOnEventClickListener(new EventClickListener() {
            @Override
            public void onEventClick(Object o, RectF rectF) {
                Consult event = (Consult)o;
                if (event.getUserId() == mUser.getMail()){
                    Toast.makeText(getContext(), "Consultation en "+ event.getName()
                            + " à la " + event.getLocation()
                            + " le " + DateFormat.getInstance().format(event.getEndTime().getTime()), Toast.LENGTH_LONG).show();
                }

            }
        });

        mCalendarView.setEmptyViewClickListener(new EmptyViewClickListener() {
            @Override
            public void onEmptyViewClicked(Calendar calendar) {
                boolean good = true;
                Calendar calendar3 = Calendar.getInstance();
                for (Consult consult : mConsultList){
                    if (calendar.compareTo(consult.getStartTime())>=0 && calendar.compareTo(consult.getEndTime())<0){
                        Toast.makeText(getContext(), R.string.event_overlap, Toast.LENGTH_SHORT).show();
                        good=false;
                    }

                }
                if (calendar.compareTo(calendar3)<=0){
                    Toast.makeText(getContext(), R.string.event_early, Toast.LENGTH_SHORT).show();
                    good=false;
                }
                if (good){
                    calendar.set(Calendar.MINUTE,0);
                    Calendar calendar1 = (Calendar) calendar.clone();
                    calendar1.add(Calendar.HOUR_OF_DAY, duree);
                    calendar1.add(Calendar.MINUTE,-1);
                    Consult consult = new  Consult(System.currentTimeMillis()
                            , mViewModel.getSpeciality()
                            ,mViewModel.getCentreDeSante().getName(), calendar,calendar1,mUser.getMail(), mViewModel.getSearchDoctorList().get(0).getName());
                    consult.setColor(getResources().getColor(R.color.colorPrimaryLight));
                    System.out.println("consult : "+consult.getLocation()+ ""+ consult.getEndTime().toString());
                    mViewModel.addConsultList(consult);
//                mCalendarView.notifyDataSetChanged();
                    Toast.makeText(getContext(), "new Consult add", Toast.LENGTH_SHORT).show();

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Navigation.findNavController(mCalendarView).navigate(R.id.action_chooseRDVFragment_to_consultListFragment);
                        }
                    };
                    new Handler().postDelayed(runnable, 500);
                }
            }
        });

    }

    private void setDates(){
        Calendar date = Calendar.getInstance();
        date.set(mViewModel.getDate().getValue().get(0),mViewModel.getDate().getValue().get(1),
                mViewModel.getDate().getValue().get(2));

    }
}
