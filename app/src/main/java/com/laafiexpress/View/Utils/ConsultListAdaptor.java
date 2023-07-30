package com.laafiexpress.View.Utils;

import android.content.ContentResolver;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.laafiexpress.Model.Consult;
import com.laafiexpress.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Laty 26 PHARAHON entertainment on 08/06/2019.
 */
public class ConsultListAdaptor extends RecyclerView.Adapter<ConsultListAdaptor.ConsulteListHolder> {
    private List<Consult> mConsultList = new ArrayList<>();
    private static final String Waiting = "A venir";
    private static final String DONE = "Terminé";
    private static final String DOC = "Docteur :";

    @NonNull
    @Override
    public ConsulteListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consult_list_item_view, parent,false);
        return new ConsulteListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsulteListHolder holder, int position) {
        holder.mSpecialite.setText(mConsultList.get(position).getName());
        holder.mCentre.setText(mConsultList.get(position).getLocation());
        String d = DOC + mConsultList.get(position).getDoctorID();
        holder.mDoctor.setText(d);
        if (!mConsultList.get(position).isStatut()){
            holder.mStatus.setText(Waiting);
        }
        else {
            holder.mStatus.setText(DONE);
        }
        Calendar calendar = mConsultList.get(position).getStartTime();
        Calendar calendar2 = mConsultList.get(position).getEndTime();
        String s = calendar.get(Calendar.DAY_OF_MONTH )+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
        holder.mDate.setText(s);
        String s1 = calendar.get(Calendar.HOUR_OF_DAY)+" h "+calendar.get(Calendar.MINUTE)+"\"  ";
        holder.mHourStart.setText(s1);
        String s2 = calendar2.get(Calendar.HOUR_OF_DAY)+" h "+calendar2.get(Calendar.MINUTE)+" \"" ;
        holder.mHourEnd.setText(s2);
        if (mConsultList.get(position).isStatut()){
            holder.mStatus.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return mConsultList.size();
    }

    public void setConsultList(List<Consult> consultList){
        mConsultList = consultList;
    }

    public class ConsulteListHolder extends RecyclerView.ViewHolder {

        private TextView mSpecialite;
        private TextView mCentre;
        private TextView mDoctor;
        private TextView mStatus;
        private TextView mDate;
        private TextView mHourStart;
        private TextView mHourEnd;

        public ConsulteListHolder(@NonNull View itemView) {
            super(itemView);

            mSpecialite = itemView.findViewById(R.id.consult_list_item_specialite);
            mCentre = itemView.findViewById(R.id.consult_list_item_centre);
            mDoctor = itemView.findViewById(R.id.consult_list_item_doctor);
            mStatus = itemView.findViewById(R.id.consult_list_item_statut);
            mDate = itemView.findViewById(R.id.consult_list_item_date);
            mHourStart = itemView.findViewById(R.id.consult_list_item_hour_start);
            mHourEnd = itemView.findViewById(R.id.consult_list_item_hour_end);

        }
    }
}
