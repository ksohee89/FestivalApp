package com.sample.festival_app;

public class ReviewItem {
    private String rid;
    private String user;
    private String festival;
    private String contents;
    private String date;

    public ReviewItem() {
    }

    public ReviewItem(String rid, String user, String festival, String contents, String date) {
        this.rid = rid;
        this.user = user;
        this.festival = festival;
        this.contents = contents;
        this.date = date;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
