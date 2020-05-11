package com.example.vinspector.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinspector.R;
import com.example.vinspector.model.VinDetailInfo;

import java.util.ArrayList;
import java.util.List;

public class VinAdapter extends RecyclerView.Adapter<VinAdapter.VinViewHolder> {

    List<VinDetailInfo> decode;

    public VinAdapter(List<VinDetailInfo> decode) {
        this.decode = decode;
    }

    @NonNull
    @Override
    public VinAdapter.VinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);

        return new VinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VinAdapter.VinViewHolder holder, int position) {

        VinDetailInfo vinDetailInfo = decode.get(holder.getAdapterPosition());

        holder.label.setText(vinDetailInfo.getLabel() + " :");

        holder.value.setText(vinDetailInfo.getValue());

    }

    @Override
    public int getItemCount() {
        return decode.size();
    }

    public class VinViewHolder extends RecyclerView.ViewHolder {

        TextView label, value;

        public VinViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.tvLabel);
            value = itemView.findViewById(R.id.tvValue);

        }
    }
}
