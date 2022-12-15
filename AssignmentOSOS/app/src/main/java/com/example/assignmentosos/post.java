package com.example.assignmentosos;

import com.google.gson.annotations.SerializedName;

public class post {

    String name , username , email , id ;

    @SerializedName("address")
    public MoreDetails moreDetails;

    public post(String name, String username, String email, String id, MoreDetails moreDetails) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.id = id;
        this.moreDetails = moreDetails;
    }

    public post() {
    }

    public MoreDetails getMoreDetails() {
        return moreDetails;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public String getId() {
        return id;
    }

}
