package com.applepluot.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by achow on 2/6/17.
 */

public class Movie {
    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;
    Double vote;

    static String BASE_PATH = "https://image.tmdb.org/t/p";

    public String getPosterPath() {
        return String.format("%s/w342/%s", BASE_PATH, posterPath);
    }

    public String getBackdropPath() {
        return String.format("%s/w780/%s", BASE_PATH, backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.vote = jsonObject.getDouble("vote_average");
        this.backdropPath = this.vote > 5? jsonObject.getString("backdrop_path") : "";
    }

    public static ArrayList<Movie> fromJsonArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();
        for (int x=0; x < array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return results;
    }
}
