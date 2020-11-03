package com.example.fatima.assignment.UI.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatima.assignment.R;
import com.example.fatima.assignment.pojo.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ArViewHolder> {
    public static final String TAG = "NewsRVAdapter";

    public List<Article> myList = new ArrayList<>();


    //TODO 3- New variable from the interface
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ArViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ArViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArViewHolder holder, int position) {
        /*
         * Case:
         * the name of the source will be included in the title string.
         *
         * Solution:
         * split the string with '-' char and then take the 0 index(the first index)
         * */
        String modifiedTitle = myList.get(position).getTitle().split("-")[0].trim();

        holder.Title.setText(modifiedTitle);
        holder.Author.setText(myList.get(position).getAuthor());

        //in case the news does not have a image url load the image from the drawable
        if (myList.get(position).urlToImage == "null") {
            Log.d(TAG, "onBindViewHolder:" + myList.get(position).toString());
            Picasso.get().load(R.drawable.ic_newsplaceholder).placeholder(R.drawable.ic_newsplaceholder).into(holder.Image);
        } else {
            Picasso.get().load(myList.get(position).urlToImage).placeholder(R.drawable.ic_newsplaceholder).into(holder.Image);
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setMyList(List<Article> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class ArViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Author;
        CircleImageView Image;

        public ArViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.newsTitleTv);
            Author = itemView.findViewById(R.id.newsSourceTv);
            Image = itemView.findViewById(R.id.newsImageIV);

            //TODO : 4- make the itemView listen to the clicks
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get the current position
                    int position = getAdapterPosition();

                    //check if the listener not null
                    //check if the clicked position is not a -1 or null
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        //TODO : 5- Send the current article
                        listener.onItemClick(myList.get(position));
                    }
                }
            });
        }
    }

    //TODO : 1- Make an interface to handle the OnItemClick
    public interface OnItemClickListener {
        void onItemClick(Article article);
    }

    //TODO : 2- Make one function that will connect be connected to the interface from the activity
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
