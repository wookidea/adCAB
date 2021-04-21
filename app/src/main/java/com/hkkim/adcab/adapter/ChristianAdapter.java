package com.hkkim.adcab.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hkkim.adcab.R;
import com.hkkim.adcab.model.Christian;

import java.util.ArrayList;

public class ChristianAdapter extends RecyclerView.Adapter<ChristianAdapter.CustomViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private int mSelectedPos = -1;
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    private ArrayList<Christian> mChristians;

    public ChristianAdapter(ArrayList<Christian> list) {
        mChristians = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView division;
        protected TextView parish;
        protected TextView area;
        protected TextView tel;

        public CustomViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.name_textView);
            this.division = view.findViewById(R.id.division_textView);
            this.parish = view.findViewById(R.id.parish_textView);
            this.area = view.findViewById(R.id.area_textView);
            this.tel = view.findViewById(R.id.tel_textView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

                    notifyItemChanged(mSelectedPos);
                    mSelectedPos = getAdapterPosition();
                    if (mSelectedPos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, mSelectedPos);
                        }
                    }
                    notifyItemChanged(mSelectedPos);
                }
            });
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.christian_row_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        holder.itemView.setSelected(mSelectedPos == position);
        holder.itemView.setBackgroundColor(mSelectedPos == position ? Color.argb(100, 200,200,200) : Color.TRANSPARENT);

        holder.name.setText(mChristians.get(position).getName());
        holder.division.setText(mChristians.get(position).getDivision());
        holder.parish.setText(mChristians.get(position).getParish());
        holder.area.setText(mChristians.get(position).getArea());
        holder.tel.setText(mChristians.get(position).getTel());
    }

    @Override
    public int getItemCount() {
        return null != mChristians ? mChristians.size() : 0;
    }
}