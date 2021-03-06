package com.immymemine.kevin.skillshare.model.m_class;

/**
 * Created by quf93 on 2017-12-04.
 */

public class Tutor {
    String _id;
    String name;
    String followers;
    String pictureUrl;

    // for test
    public Tutor(String _id,
            String name,
            String followers,
            String pictureUrl) {
        this._id = _id;
        this.name = name;
        this.followers = followers;
        this.pictureUrl = pictureUrl;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
