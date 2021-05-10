package com.quizearn.rishap.quiz20;

/**
 * Created by RISHAP on 05-05-2020.
 */

public class Profile {
    private String email;
    private String score;

    public Profile() {
    }

    public Profile(String email, String score) {
        this.email = email;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
