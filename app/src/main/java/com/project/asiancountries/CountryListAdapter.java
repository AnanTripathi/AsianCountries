package com.project.asiancountries;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {
    private static final String TAG = "CountryListAdapter";
    private List<Country> countryList;
    private Context context;
    //private RequestBuilder<PictureDrawable> requestBuilder;


    public CountryListAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country currentCountry=countryList.get(position);
        holder.countryNameTextView.setText(currentCountry.getName());
        holder.countryCapitalTextView.setText(currentCountry.getCapital());
        holder.countryRegionTextView.setText(currentCountry.getRegion());
        holder.countrySubRegionTextView.setText(currentCountry.getSubRegion());
        holder.countryPopulationTextView.setText(currentCountry.getPopulation().toString());
        List<String> borders=currentCountry.getBorders();
        String border="Borders:\n";
        for(int i=0;i<borders.size();i++){
            border=border+i+"."+borders.get(i)+"\n";
        }
        holder.countryBordersTextView.setText(border);
        List<Language> languages=currentCountry.getLanguageList();
        String language="Languages:\n";
        for(int i=0;i<languages.size();i++){
            language=language+i+". Name:"+languages.get(i).getName();
            language=language+"\n   Native Name: "+languages.get(i).getNativeName()+"\n";
        }
        holder.countryLanguagesTextView.setText(language);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flagImageView;
        TextView countryNameTextView;
        TextView countryCapitalTextView;
        TextView countryRegionTextView;
        TextView countrySubRegionTextView;
        TextView countryPopulationTextView;
        TextView countryBordersTextView;
        TextView countryLanguagesTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagImageView= itemView.findViewById(R.id.flagImageView);
            countryNameTextView=itemView.findViewById(R.id.countryNameTv);
            countryCapitalTextView=itemView.findViewById(R.id.countryCapitalTv);
            countryRegionTextView=itemView.findViewById(R.id.countryRegionTv);
            countrySubRegionTextView=itemView.findViewById(R.id.countrysubRegionTv);
            countryPopulationTextView=itemView.findViewById(R.id.countryPopulationTv);
            countryBordersTextView=itemView.findViewById(R.id.countryBordersTv);
            countryLanguagesTextView=itemView.findViewById(R.id.countryLanguagesTv);
        }
    }
}