package com.applepluot.flickster.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applepluot.flickster.R;
import com.applepluot.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by achow on 2/6/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get the data object for the position
        Movie movie = getItem(position);
        // convertView == sad recycled view
        // do I have a reincarnated view
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            LayoutInflater inflater = LayoutInflater.from(getContext());
            // inflate is converting xml to object in memory
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }
        // look up data for population
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        //clear out image from convertView
        imageView.setImageResource(0);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
        assert movie != null;
        // Populate the data into the template view
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(imageView);
        return convertView;
    }
}
