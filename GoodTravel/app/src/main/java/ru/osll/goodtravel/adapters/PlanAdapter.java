package ru.osll.goodtravel.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.DAO.Plan;


public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>
{
    public ArrayList<Plan> plans;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private AdapterView.OnItemClickListener onItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView descriptionTextView;
        private AdapterView.OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public ViewHolder(View itemView)
        {
            super(itemView);
            descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionTextView);
            itemView.setOnClickListener(this);
        }

        public void setDescription(String description)
        {
            descriptionTextView.setText(description);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null)
                onItemClickListener.onItemClick(null, v, getAdapterPosition(),
                        getAdapterPosition());
        }
    }

    public PlanAdapter(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Plan planService = plans.get(position);
        holder.setDescription(planService.getName());
        holder.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }
}
