package ru.osll.goodtravel.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.DAO.Plan;


public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>
{
    public ArrayList<Plan> plans;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView descriptionTextView;

        public ViewHolder(View itemView)
        {
            super(itemView);
            descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionTextView);
        }

        public void setDescription(String description)
        {
            descriptionTextView.setText(description);
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
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }
}
