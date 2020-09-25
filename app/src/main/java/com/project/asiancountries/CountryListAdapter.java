package com.project.asiancountries;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.project.asiancountries.util.Country;
import com.project.asiancountries.util.Language;

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
        holder.countryNameTextView.setText("Name: "+currentCountry.getName());
        holder.countryCapitalTextView.setText("Capital: "+currentCountry.getCapital());
        holder.countryRegionTextView.setText("Region: "+currentCountry.getRegion());
        holder.countrySubRegionTextView.setText("SubRegion: "+currentCountry.getSubRegion());
        holder.countryPopulationTextView.setText("Population: "+currentCountry.getPopulation().toString());
        List<String> borders=currentCountry.getBorders();
        String border="Borders:\n";
        for(int i=0;i<borders.size();i++){
            border=border+i+1+"."+borders.get(i)+"\n";
        }
        holder.countryBordersTextView.setText(border);
        List<Language> languages=currentCountry.getLanguageList();
        String language="Languages:\n";
        for(int i=0;i<languages.size();i++){
            language=language+i+1+". Name:"+languages.get(i).getName();
            language=language+"\n   Native Name: "+languages.get(i).getNativeName()+"\n";
        }
        holder.countryLanguagesTextView.setText(language);
        RequestBuilder<PictureDrawable> requestBuilder = GlideToVectorYou
                .init()
                .with(context)
                .getRequestBuilder();
        requestBuilder
                .load(Uri.parse(currentCountry.getFlagUrl()))
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                .fitCenter())
                .into(holder.flagImageView);
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
