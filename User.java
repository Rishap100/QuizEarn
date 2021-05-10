package com.quizearn.rishap.quiz20;

/**
 * Created by RISHAP on 16-04-2020.
 */

public class User {
    String email, score,id,img,name;


    public User(){

    }

    public User(String id,String email, String score, String img, String name) {
        this.email = email;
        this.score = score;
        this.id =id;
        this.img = img;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getScore() {
        return score;
    }

    public String getId() {
        return id;
    }
    public String getImg(){return img;}
    public String getName(){return name;}
}
