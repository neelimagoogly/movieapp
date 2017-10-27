package com.android.popmoviestwo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    /**
     * w185 is the size of the image getting retrieved
     */
    String IMAGE_MOVIE_URL = "http://image.tmdb.org/t/p/w185//";

    private List<Movie> mMoviesList;
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param moviesList A List of AndroidFlavor objects to display in a list
     */
    public MoviesAdapter(Activity context, List<Movie> moviesList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, moviesList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the Movie object from the ArrayAdapter at the appropriate position
        Movie movie = getItem(position);

        if (convertView == null) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.movie_item, parent, false);
            }
        }

        /**
         * Displays the imageView moviePoster
         */
        ImageView movie_thumbnail = (ImageView) convertView.findViewById(R.id.movie_image);
        Picasso.with(getContext()).load(IMAGE_MOVIE_URL + movie.getMovieImgPath()).into(movie_thumbnail);

        /**
         * Displays the textView movieTitle
         */
        TextView movieTitle = (TextView) convertView.findViewById(R.id.movie_title);
        movieTitle.setText(movie.movieTitle);

        return convertView;
    }

    public void setMoviesData(List<Movie> moviesList) {
        mMoviesList = moviesList;
        notifyDataSetChanged();
    }
}