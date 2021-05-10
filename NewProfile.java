package com.quizearn.rishap.quiz20;
/**
 * Created by RISHAP on 05-05-2020.
 */

public class NewProfile {
    private String name;
    private String score;
    private String img;

    public NewProfile() {
    }

    public NewProfile(String name, String score, String img) {
        this.name = name;
        this.score = score;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
