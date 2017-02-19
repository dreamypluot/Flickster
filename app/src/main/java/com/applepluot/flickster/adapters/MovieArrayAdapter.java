package com.applepluot.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.Log;
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

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static com.applepluot.flickster.R.id.ivMovieImage;
import static com.applepluot.flickster.R.id.tvOverview;
import static com.applepluot.flickster.R.id.tvTitle;

/**
 * Created by achow on 2/6/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView posterImage;
        ImageView backdropImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get the data object for the position
        Movie movie = getItem(position);
        ViewHolder viewHolder; // view lookup cache stored in tag

        // convertView == sad recycled view
        // do I have a reincarnated view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            // If there's no view to re-use, inflate a brand new view for row
            LayoutInflater inflater = LayoutInflater.from(getContext());
            // inflate is converting xml to object in memory
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(tvTitle);
            viewHolder.overview= (TextView) convertView.findViewById(tvOverview);
            viewHolder.posterImage = (ImageView) convertView.findViewById(ivMovieImage);
            viewHolder.backdropImage = (ImageView) convertView.findViewById(ivMovieImage);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //clear out image from convertView
        viewHolder.posterImage.setImageResource(0);
        viewHolder.backdropImage.setImageResource(0);
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        assert movie != null;
        // Populate the data into the template view
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        // Use drawable as placeholder image
        Log.d("Movie Rating:", movie.getOriginalTitle() + ":" + movie.getRating());
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath())
                    .placeholder(R.drawable.movieimage)
                    .error(R.drawable.oops)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .fit().centerInside()
                    .into(viewHolder.posterImage);
        } else {
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .placeholder(R.drawable.movieimage)
                    .error(R.drawable.oops)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .resize(0, 560)
                    .into(viewHolder.backdropImage);
        }
        return convertView;
    }
}
