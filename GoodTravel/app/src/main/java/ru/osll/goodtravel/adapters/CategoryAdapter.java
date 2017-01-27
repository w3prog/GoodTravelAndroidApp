package ru.osll.goodtravel.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.osll.goodtravel.R;
import ru.osll.goodtravel.models.CategoryOfService;

/**
 * Created by mycrfotkai on 26.11.16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView imageView;
        private TextView textView;
        private Context context;
        private AdapterView.OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView, Context context)
        {
            super(itemView);

            this.context = context;

            imageView = (ImageView)itemView.findViewById(R.id.categoryImageView);
            textView = (TextView)itemView.findViewById(R.id.categoryTextView);
            itemView.setOnClickListener(this);
        }

        public void setText(String text)
        {
            textView.setText(text);
        }

        public void setImage(String src)
        {
            if(src != null && !src.isEmpty())
            {
                Picasso
                        .with(context)
                        .load(src)
                        .resize(80,80)// TODO: 27.01.17 найти более универсальное решение
                        .into(imageView);
            }
            else
            {
                imageView.setBackgroundResource(R.mipmap.route_maker_museum);
            }
        }

        @Override
        public void onClick(View view)
        {
            if(onItemClickListener != null)
                onItemClickListener.onItemClick(null, view, getAdapterPosition(), getAdapterPosition());
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
        {
            this.onItemClickListener = onItemClickListener;
        }
    }

    private List<CategoryOfService> categoryOfServiceList;
    private AdapterView.OnItemClickListener onItemClickListener;

    public CategoryAdapter(List<CategoryOfService> categoryOfServiceList)
    {
        this.categoryOfServiceList = categoryOfServiceList;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maker_travel_category_item, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position)
    {
        CategoryOfService categoryOfService = categoryOfServiceList.get(position);

        holder.setImage(categoryOfService.getStrImg());
        holder.setText(categoryOfService.getName());
        holder.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getItemCount()
    {
        return categoryOfServiceList.size();
    }
}
