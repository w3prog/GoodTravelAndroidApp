package ru.osll.goodtravel.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import ru.osll.goodtravel.R;



public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>
{
    //public List<PlanService> planServices;

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView previewImageView;
        private TextView dateTextView;
        private TextView descriptionTextView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            previewImageView = (ImageView)itemView.findViewById(R.id.previewImageView);
            dateTextView = (TextView)itemView.findViewById(R.id.dateTextView);
            descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionTextView);
        }

        public void setPreview(String src)
        {
            if(src == null || src.isEmpty())
            {
                previewImageView.setImageResource(android.R.drawable.sym_def_app_icon);
            }
            else
            {
                Picasso
                        .with(itemView.getContext())
                        .load(src)
                        .into(previewImageView);
            }
        }

        public void setDate(Date date)
        {
            dateTextView.setText(DateFormat.getDateInstance().format(date));
        }

        public void setDescription(String description)
        {
            descriptionTextView.setText(description);
        }
    }

//    public PlanAdapter(List<PlanService> planServices)
//    {
//        this.planServices = planServices;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
//        PlanService planService = planServices.get(position);
//
//        holder.setDate(planService.getDate());
//        holder.setDescription(planService.getName());
//        holder.setPreview(planService.getSrcToImg());
    }

    @Override
    public int getItemCount()
    {
        // TODO: 28.01.17 Реализовать
        return 0;
        //return planServices.size();
    }
}
