package com.laafiexpress.View.Utils;

import android.content.ContentResolver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laafiexpress.Model.CentreDeSante;
import com.laafiexpress.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laty 26 PHARAHON entertainment on 06/06/2019.
 */
public class CentreDeSanteListAdaptator extends RecyclerView.Adapter<CentreDeSanteListAdaptator.CentreDeSanteListHolder> {

    List<CentreDeSante> mCentreDeSantes = new ArrayList<>();
    private OnCenterClickListener mListener;


    @NonNull
    @Override
    public CentreDeSanteListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.centre_de_sante_list_item_view, parent,false);
        return new CentreDeSanteListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CentreDeSanteListHolder holder, int position) {
        CentreDeSante centreDeSante = mCentreDeSantes.get(position);
        holder.mName.setText(centreDeSante.getName());
        holder.mRatingBar.setRating((int)centreDeSante.getRate());
    }

    @Override
    public int getItemCount() {
        return mCentreDeSantes.size();
    }

    public void setCentreDeSantes(List<CentreDeSante> centreDeSantes){
        this.mCentreDeSantes = centreDeSantes;
    }

    public class CentreDeSanteListHolder extends RecyclerView.ViewHolder{

        private TextView mName;
        private RatingBar mRatingBar;
        private boolean checked = false;

        public CentreDeSanteListHolder(@NonNull final View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.clinic_item_name);
            mRatingBar = itemView.findViewById(R.id.clinic_item_rate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mListener!=null && position != RecyclerView.NO_POSITION){
                        if (!checked){
                            itemView.findViewById(R.id.clinic_item_check).setVisibility(View.VISIBLE);
                            checked = true;
                        }
                        else {
                            itemView.findViewById(R.id.clinic_item_check).setVisibility(View.GONE);
                            checked = false;
                        }
                        mListener.onCenterClick(mCentreDeSantes.get(position), checked);
                    }
                }
            });
        }

    }

    public interface OnCenterClickListener {
        void onCenterClick(CentreDeSante centreDeSante, boolean check);
    }

    public void setOnCenterClickListener(OnCenterClickListener listener){
        mListener = listener;
    }
}
