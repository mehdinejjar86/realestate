package com.nightstalker.interaction;

import com.nightstalker.people.Profile;

import java.io.Serializable;


/**
 * The type Property rating.
 */
public class PropertyRating implements Serializable {
    private Profile profile;
    private int rate;
    private String comment;

    /**
     * Instantiates a new Property rating.
     *
     * @param profile the profile
     * @param rate    the rate
     * @param comment the comment
     */
    public PropertyRating(Profile profile, int rate, String comment) {
        this.profile = profile;
        this.rate = rate;
        this.comment = comment;
    }

    /**
     * Gets profile.
     *
     * @return the profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sets profile.
     *
     * @param profile the profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ProfileRating{" +
                "property=" + profile +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
