package ru.osll.goodtravel.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.DAO.Day;

public class DaysInPlanAdapter extends RecyclerView.Adapter<DaysInPlanAdapter.ViewHolder> {

    ArrayList<Day> days;
    private AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public DaysInPlanAdapter(ArrayList<Day> days) {
        this.days = days;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plan, parent, false);
        return new DaysInPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Day day = days.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy");
        holder.setText(sdf.format(day.getDate()));
        holder.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        String text;
        private TextView textView;
        private AdapterView.OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
            textView.setText(text);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.descriptionTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null)
                onItemClickListener.onItemClick(null, v, getAdapterPosition(),
                        getAdapterPosition());
        }
    }
}
