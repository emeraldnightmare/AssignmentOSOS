package com.example.assignmentosos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {


    List<post> postList;
    Context context;

    public PostAdapter(Context context , List<post> posts ) {
        this.context = context;
        postList = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new PostViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        post post = postList.get(position);
        holder.id.setText(post.getId());
        holder.name.setText(post.getName());
        holder.username.setText(post.getUsername());
        holder.email.setText(post.getEmail());
        holder.street.setText(post.getMoreDetails().getStreet());
        holder.city.setText(post.getMoreDetails().getCity());
        holder.zipcode.setText(post.getMoreDetails().getZipcode());


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView name , username , email , street , city , zipcode , id ;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.API_id);
            name = itemView.findViewById(R.id.API_name);
            username = itemView.findViewById(R.id.API_username);
            email = itemView.findViewById(R.id.API_email);
            street = itemView.findViewById(R.id.API_street);
            city = itemView.findViewById(R.id.API_city);
            zipcode = itemView.findViewById(R.id.API_zipcode);


        }
    }
}
